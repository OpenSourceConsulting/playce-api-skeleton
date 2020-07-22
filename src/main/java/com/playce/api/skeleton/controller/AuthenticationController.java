package com.playce.api.skeleton.controller;

import com.playce.api.skeleton.dto.PlayceMessage;
import com.playce.api.skeleton.dto.Status;
import com.playce.api.skeleton.exception.PasswordIncorrectLimitException;
import com.playce.api.skeleton.exception.PlayceException;
import com.playce.api.skeleton.security.JwtAuthenticationRequest;
import com.playce.api.skeleton.security.JwtAuthenticationResponse;
import com.playce.api.skeleton.security.JwtTokenUtil;
import com.playce.api.skeleton.security.JwtUser;
import com.playce.api.skeleton.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

//	@Autowired
//	@Qualifier("jwtUserDetailsServiceImpl")
//	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private LocaleResolver localeResolver;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private MemberService memberService;

	@Value("${info.app.version}")
	private String applicationVersion;

	/**
	 * <pre>
	 * 로그인 Process
	 * </pre>
	 * @param authenticationRequest
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "로그인 인증", notes = "로그인 과정을 통해 인증 토큰을 생성한다.")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public PlayceMessage createAuthenticationTokenByPassword(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletRequest request) throws AuthenticationException {

		PlayceMessage message = new PlayceMessage();
		Locale locale = localeResolver.resolveLocale(request);

		try {
			// UserDetails can get from authentication
			//final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserId());

			// Perform the security
			final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							authenticationRequest.getUsername(),
							authenticationRequest.getPassword()
					)
			);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Reload password post-security so we can generate token
			final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

			final String token = jwtTokenUtil.generateToken(userDetails);
			final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);

			updateLastLoginDate(userDetails.getUsername());

			message.setStatus(Status.success);
			message.setData(new JwtAuthenticationResponse(((JwtUser) userDetails).getId(), ((JwtUser) userDetails).getUserId(),
					((JwtUser) userDetails).getDisplayName(), token, refreshToken));

			// reset failLimitCount
			memberService.updateFailLimitCount(userDetails.getUsername(), 0L);

		} catch (UsernameNotFoundException e) {
			logger.error("Unhandled exception occurred while get token.", e);

			message.setStatus(Status.fail);
			message.setCode(1005);
			message.setMessage(messageSource.getMessage("auth.login.failed", new String[] {e.getMessage()}, locale));
		} catch (PasswordIncorrectLimitException e) {
			logger.error("Unhandled exception occurred while password limit exceed.", e);

			message.setStatus(Status.fail);
			message.setCode(1011);
			message.setMessage(messageSource.getMessage("auth.password.incorrect.limit.exceed", new String[] {e.getMessage()}, locale));

		} catch (BadCredentialsException e) {
			logger.error("Unhandled exception occurred while get token.", e);

			try {
				// update fail count
				memberService.updateFailLimitCount(authenticationRequest.getUsername());

			} catch (Exception e1) {
				logger.error("Unhandled exception occurred while update fail count.", e1);
			}

			message.setStatus(Status.fail);
			message.setCode(1006);
			message.setMessage(messageSource.getMessage("auth.login.failed", new String[] {e.getMessage()}, locale));
		} catch (Exception e) {
			logger.error("Unhandled exception occurred while get token.", e);

			message.setStatus(Status.fail);
			if (e instanceof InternalAuthenticationServiceException) {
				message.setCode(1011);
			} else {
				message.setCode(1010);
			}
			message.setMessage(messageSource.getMessage("auth.login.failed", new String[] {e.getMessage()}, locale));
		}

		return message;
	}

	/**
	 * <pre>
	 * 어플리케이션 버전을 조회한다.
	 * </pre>
	 * @return
	 */
	@ApiOperation(value = "어플리케이션 버전 조회", notes = "어플리케이션 버전을 조회한다.")
	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public PlayceMessage getVersion(HttpServletRequest request) {
		PlayceMessage message = new PlayceMessage();

		try {
			if (applicationVersion == null || applicationVersion.equals("")) {
				throw new PlayceException("Application version does not exists.");
			}

			HashMap<String, String> dataMap = new HashMap<>();
			dataMap.put("applicationVersion", applicationVersion);

			message.setStatus(Status.success);
			message.setData(dataMap);

		} catch (PlayceException e) {
			logger.error("Unhandled exception occurred while get application version.", e);

			message.setStatus(Status.fail);
			message.setMessage("Can NOT get application version. [Reason] : " + e.getMessage());
		}

		return message;
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param userId
	 * //@param refreshToken
	 */
	private void updateLastLoginDate(String userId) {
		try {
			memberService.updateLastLoginDate(userId);
		} catch (Exception e) {
			logger.warn("Unhandled exception occurred while update last login date. [Reason] : {}", e.getMessage());
		}
	}
}

