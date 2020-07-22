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
package com.playce.api.skeleton.common.util;

import com.playce.api.skeleton.model.MembersRolesDomains;
import com.playce.api.skeleton.security.JwtUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
public class WebUtil {

    /**
     * <pre>
     * 로그인 사용자 정보를 가져온다.
     * </pre>
     *
     * @return login user
     */
    public static UserDetails getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }

        return null;
    }

    /**
     * <pre>
     * 로그인 사용자의 user id를 가져온다.
     * </pre>
     *
     * @return username
     */
    public static String getUserId() {
        return ((JwtUser) getLoginUser()).getUserId();
    }

    /**
     * <pre>
     * 로그인 사용자의 display name을 가져온다.
     * </pre>
     *
     * @return username
     */
    public static String getDisplayName() {
        return ((JwtUser) getLoginUser()).getDisplayName();
    }

    /**
     * <pre>
     * 로그인 사용자의 id를 가져온다.
     * </pre>
     *
     * @return the user id
     */
    public static Long getId() {
        Long id = null;

        try {
            id = ((JwtUser) getLoginUser()).getId();
        } catch (Exception e) {
            // ignore
        }

        if (id == null) {
            id = (Long) ThreadLocalUtil.get("MEMBER_ID");
        }

        return id;
    }

    /**
     * <pre>
     * WebApplicationContext 객체를 가져온다.
     * </pre>
     * @param request
     * @return
     */
    public static WebApplicationContext getWebApplicationContext(HttpServletRequest request) {
        return WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
    }

    /**
     * <pre>
     * 로그인한 사용자의 권한을 비교한다.
     * </pre>
     * @param role
     * @return
     */
    public static boolean hasRole(String role) {
        // get security context from thread local
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return false;
        }

        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return false;
        }

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (role.equals(auth.getAuthority())) {
                return true;
            }
        }

        return false;
    }

    /**
     * <pre>
     * 로그인한 사용자의 권한을 비교한다.
     * </pre>
     * @param roleId
     * @return
     */
    public static boolean hasRole(Long roleId) {
        List<MembersRolesDomains> membersRolesDomainsList = ((JwtUser) getLoginUser()).getMembersRolesDomainsList();

        for (MembersRolesDomains mrd : membersRolesDomainsList) {
            if (mrd.getRole().getId().longValue() == roleId.longValue()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets domaind id list.
     *
     * @return the domaind id list
     */
    public static List<Long> getDomaindIdList() {
        List<Long> domainIdList = new ArrayList<Long>();
        domainIdList.add(1L);

        List<MembersRolesDomains> membersRolesDomainsList = ((JwtUser) getLoginUser()).getMembersRolesDomainsList();

        for (MembersRolesDomains mrd : membersRolesDomainsList) {
            domainIdList.add(mrd.getDomain().getId());
        }

        return domainIdList;
    }

    /**
     * Has read permission boolean.
     *
     * @param domainId the domain id
     * @return the boolean
     */
    public static boolean hasReadPermission(Long domainId) {
        if (domainId == 1L) {
            return true;
        }

        JwtUser user = (JwtUser) getLoginUser();

        if (user != null) {
            List<MembersRolesDomains> membersRolesDomainsList = ((JwtUser) getLoginUser()).getMembersRolesDomainsList();

            for (MembersRolesDomains mrd : membersRolesDomainsList) {
                if (mrd.getRole().getId().longValue() == 1L) {
                    return true;
                } else {
                    if (mrd.getDomain().getId().longValue() == domainId.longValue()) {
                        return true;
                    }
                }
            }

            return false;
        } else {
            // System
            return true;
        }
    }

    /**
     * Has write permission boolean.
     *
     * @param domainId the domain id
     * @return the boolean
     */
    public static boolean hasWritePermission(Long domainId) {
        JwtUser user = (JwtUser) getLoginUser();

        if (user != null) {
            List<MembersRolesDomains> membersRolesDomainsList = user.getMembersRolesDomainsList();

            for (MembersRolesDomains mrd : membersRolesDomainsList) {
                if (mrd.getRole().getId().longValue() == 1L) {
                    return true;
                } else if (mrd.getRole().getId().longValue() == 2L) {
                    if (mrd.getDomain().getId().longValue() == domainId.longValue()) {
                        return true;
                    }
                }
            }

            return false;
        } else {
            // System
            return true;
        }
    }
}
//end of WebUtil.java