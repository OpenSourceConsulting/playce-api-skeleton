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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.playce.api.skeleton.model.MembersRolesDomains;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class JwtUser implements UserDetails {

    private static final long serialVersionUID = 1039378187256880677L;

    private final Long id;
    private final String userId;
    private final String password;
    private final String userName;
    private final String email;
    private final String viewMode;
    private final List<MembersRolesDomains> membersRolesDomainsList;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean deleted;

    public JwtUser(Long id, String userId, String password, String userName, String email, String viewMode, List<MembersRolesDomains> membersRolesDomainsList,
                   Collection<? extends GrantedAuthority> authorities, boolean deleted) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.viewMode = viewMode;
        this.membersRolesDomainsList = membersRolesDomainsList;
        this.authorities = authorities;
        this.deleted = deleted;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getViewMode() {
        return viewMode;
    }

    public List<MembersRolesDomains> getMembersRolesDomainsList() {
        return membersRolesDomainsList;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * @return the deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public boolean isEnabled() {
        return !deleted;
    }
}
