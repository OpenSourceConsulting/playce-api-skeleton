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

import com.playce.api.skeleton.model.Member;
import com.playce.api.skeleton.model.MembersRolesDomains;
import com.playce.api.skeleton.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(Member member) {
        return new JwtUser(member.getId(),
                member.getUserId(),
                member.getPassword(),
                member.getUserName(),
                member.getEmail(),
                member.getViewMode(),
                member.getMembersRolesDomains(),
                mapToGrantedAuthorities(member.getMembersRolesDomains()),
                member.getDeleteYn().equals("Y") ? true : false);
    }

    public static List<GrantedAuthority> mapToGrantedAuthorities(List<MembersRolesDomains> authorities) {
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getRoleName()))
                .collect(Collectors.toList());
    }

    public static List<GrantedAuthority> mapToGrantedAuthorities(Set<Role> authorities) {
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
    }
}
