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
package com.playce.api.skeleton.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * <pre>
 *
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
@Entity
public class MembersRolesDomains extends BaseDomain {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="members_roles_domains_generator")
    @GenericGenerator(name = "members_roles_domains_generator", strategy = "native")
    private Long id;

    /**
     * The Member.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Transient
    private Long memberId;

    /**
     * The Role.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @Transient
    private Long roleId;

    /**
     * The Domain.
     */
    @JsonIgnore
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Domain domain;

    @Transient
    private Long domainId;

    /**
     * The User id.
     */
    @Transient
    private String userId;

    /**
     * The Role name.
     */
    @Transient
    private String roleName;

    /**
     * The Domain name.
     */
    @Transient
    private String domainName;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets member.
     *
     * @return the member
     */
    public Member getMember() {
        return member;
    }

    /**
     * Sets member.
     *
     * @param member the member
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets domain.
     *
     * @return the domain
     */
    public Domain getDomain() {
        return domain;
    }

    /**
     * Sets domain.
     *
     * @param domain the domain
     */
    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
        if (userId == null && member != null) {
            userId = member.getUserId();
        }

        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets role name.
     *
     * @return the role name
     */
    public String getRoleName() {
        if (roleName == null && role != null) {
            roleName = role.getName();
        }

        return roleName;
    }

    /**
     * Sets role name.
     *
     * @param roleName the role name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Gets domain name.
     *
     * @return the domain name
     */
    public String getDomainName() {
        if (domainName == null && domain != null) {
            domainName = domain.getName();
        }

        return domainName;
    }

    /**
     * Sets domain name.
     *
     * @param domainName the domain name
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public Long getMemberId() {
        if (memberId == null && member != null) {
            memberId = member.getId();
        }

        return memberId;
    }

    public void setMemberId(Long memberId) {
        if (member == null) {
            member = new Member();
            member.setId(memberId);
        }

        this.memberId = memberId;
    }

    public Long getRoleId() {
        if (roleId == null && role != null) {
            roleId = role.getId();
        }

        return roleId;
    }

    public void setRoleId(Long roleId) {
        if (role == null) {
            role = new Role();
            role.setId(roleId);
        }

        this.roleId = roleId;
    }

    public Long getDomainId() {
        if (domainId == null && domain != null) {
            domainId = domain.getId();
        }

        return domainId;
    }

    public void setDomainId(Long domainId) {
        if (domain == null) {
            domain = new Domain();
            domain.setId(domainId);
        }

        this.domainId = domainId;
    }
}
//end of MembersRolesDomains.java