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

import com.playce.api.skeleton.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

/**
 * <pre>
 * JWT 관련 Utility Class
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
@Component
public class JwtTokenUtil implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    private static final long serialVersionUID = -752350610217886922L;

    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_CREATED = "created";
    static final String CLAIM_KEY_AUTH = "auth";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.refresh.expiration}")
    private Long refreshExpiration;

    /**
     * <pre>
     * token으로부터 username을 조회한다.
     * </pre>
     * 
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username = null;

        try {
            final Claims claims = getClaimsFromToken(token);

            if (claims != null) {
                username = claims.getSubject();
            }
        } catch (Exception e) {
            logger.warn("Unhandled exception occurred while invoke getUsernameFromToken(). Reason : [{}]",
                    e.getMessage());
        }

        return username;
    }

    /**
     * <pre>
     * token으로부터 auth 정보를 조회한다.
     * </pre>
     * 
     * @param token
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public String getAuthFromToken(String token) {
        String auth = null;

        try {
            final Claims claims = getClaimsFromToken(token);

            if (claims != null) {
                auth = ((ArrayList<HashMap>) claims.get("auth")).get(0).get("authority").toString();
            }
        } catch (Exception e) {
            logger.warn("Unhandled exception occurred while invoke getAuthFromToken(). Reason : [{}]", e.getMessage());
        }

        return auth;
    }

    /**
     * <pre>
     * token으로부터 토큰 생성 시간을 조회한다.
     * </pre>
     * 
     * @param token
     * @return
     */
    public Date getCreatedDateFromToken(String token) {
        Date created = null;

        try {
            final Claims claims = getClaimsFromToken(token);

            if (claims != null) {
                created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
            }
        } catch (Exception e) {
            logger.warn("Unhandled exception occurred while invoke getCreatedDateFromToken(). Reason : [{}]",
                    e.getMessage());
        }

        return created;
    }

    /**
     * <pre>
     * token으로부터 토큰 만료 시간을 조회한다.
     * </pre>
     * 
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration = null;

        try {
            final Claims claims = getClaimsFromToken(token);

            if (claims != null) {
                expiration = claims.getExpiration();
            }
        } catch (Exception e) {
            logger.warn("Unhandled exception occurred while invoke getExpirationDateFromToken(). Reason : [{}]",
                    e.getMessage());
        }

        return expiration;
    }

    /**
     * <pre>
     * token으로부터 claim 정보를 조회한다.
     * </pre>
     * 
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;

        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            logger.warn("Unhandled exception occurred while invoke getClaimsFromToken(). Reason : [{}]",
                    e.getMessage());
        }

        return claims;
    }

    /**
     * <pre>
     * 토큰 만료 시간을 생성한다.
     * </pre>
     * 
     * @return
     */
    // private Date generateExpirationDate() {
    // return new Date(System.currentTimeMillis() + (expiration * 1000 * 60));
    // }

    /**
     * <pre>
     * 토큰 만료 여부를 검사한다.
     * </pre>
     * 
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * <pre>
     * 토큰을 생성한다.
     * </pre>
     * 
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_AUTH, userDetails.getAuthorities());
        return generateToken(claims, expiration);
    }

    /**
     * <pre>
     * 토큰을 생성한다.
     * </pre>
     * 
     * @param userDetails
     * @return
     */
    public String generateRefreshToken(UserDetails userDetails) {
        Set<Role> authorities = new HashSet<Role>();
        Role auth = new Role();
        auth.setName("REFRESH_TOKEN");

        authorities.add(auth);

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_AUTH, JwtUserFactory.mapToGrantedAuthorities(authorities));
        return generateToken(claims, refreshExpiration);
    }

    /**
     * <pre>
     * 토큰을 생성한다.
     * </pre>
     * 
     * @param claims
     * @return
     */
    public String generateToken(Map<String, Object> claims, long expiration) {
        return Jwts.builder().setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + (expiration * 1000 * 60)))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * <pre>
     * token을 갱신한다.
     * </pre>
     * 
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        String refreshedToken = null;

        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims, expiration);
        } catch (Exception e) {
            logger.warn("Unhandled exception occurred while invoke refreshToken(). Reason : [{}]", e.getMessage());
        }

        return refreshedToken;
    }

    /**
     * <pre>
     * 유효한 토큰인지 검사한다.
     * </pre>
     * 
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }
}