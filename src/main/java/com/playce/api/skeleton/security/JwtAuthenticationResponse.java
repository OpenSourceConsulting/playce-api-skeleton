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

import java.io.Serializable;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final Long memberId;
    private final String userId;
    private final String userName;
    private final String accessToken;
    private final String refreshToken;

    public JwtAuthenticationResponse(Long memberId, String userId, String userName, String accessToken, String refreshToken) {
        this.memberId = memberId;
        this.userId = userId;
        this.userName = userName;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }
}
