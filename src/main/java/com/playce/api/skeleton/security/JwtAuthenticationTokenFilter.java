/*
 * Copyright 2020 The Playce Project.
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Revision History
 * Author			Date				Description
 * ---------------	----------------	------------
 * SangCheon Park	Jul 22, 2020	    First Draft.
 */
package com.playce.api.skeleton.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    private static final String HEADER_PREFIX = "Bearer ";

    @Autowired
    @Qualifier("jwtUserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authToken = extract(request.getHeader(this.tokenHeader));

        if (!StringUtils.isEmpty(authToken)) {
            String username = jwtTokenUtil.getUsernameFromToken(authToken);

            if (!"/api/users/refresh".equals(request.getServletPath())
                    && "REFRESH_TOKEN".equals(jwtTokenUtil.getAuthFromToken(authToken))) {
                username = null;
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // logger.debug("Checking authentication for user [{}]", username);

                // It is not compelling necessary to load the use details from the database. You
                // could also store the information
                // in the token and read it from it. It's up to you ;)
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                // Verified Check

                //if (!((JwtUser) userDetails).isVerified()) {
                //    String message = username + " does not verified yet.";

                //    response.sendError(HttpStatus.UNAUTHORIZED.value(), message);
                //    return;
                //}

                // For simple validation it is completely sufficient to just check the token
                // integrity. You don't have to call
                // the database compellingly. Again it's up to you ;)
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    // logger.debug("Authenticated user [{}], setting security context.", username);
                }
            }
        }

        chain.doFilter(request, response);
    }

    public String extract(String header) {
        if (!StringUtils.isEmpty(header)) {
            if (header.startsWith(HEADER_PREFIX)) {
                return header.substring(HEADER_PREFIX.length(), header.length());
            }
            /*
            if (header.length() < HEADER_PREFIX.length()) {
                logger.debug("[{}] Invalid authorization header size.", header);
                // throw new AuthenticationServiceException("Invalid authorization header size.");
            }

            if (!header.startsWith(HEADER_PREFIX)) {
                logger.debug("[{}] Invalid authorization header prefix.", header);
                // throw new AuthenticationServiceException("Invalid authorization header prefix.");
            }
            */
        }

        return null;
    }
}