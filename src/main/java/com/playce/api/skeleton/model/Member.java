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

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
@Entity
@Where(clause = "delete_yn='N'")
public class Member extends AuditBaseDomain {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="member_generator")
    @GenericGenerator(name = "member_generator", strategy = "native")
    private Long id;

    /**
     * The Members roles domains.
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<MembersRolesDomains> membersRolesDomains;

    /**
     * The UserId.
     */
    //@Column(nullable = false, unique = true)
    @Column(nullable = false)
    private String userId;

    /**
     * The Password.
     */
    @Column(length = 512, nullable = false)
    private String password;

    /**
     * The userName.
     */
    @Column
    private String userName;

    /**
     * The Email.
     */
    @Column
    private String email;

    /**
     * The viewMode.
     */
    @Column
    private String viewMode;

    /**
     * The Last login date.
     */
    @Column
    private Date lastLoginDate;

    /**
     * The description.
     */
    @Column
    private String description;

    /**
     * The failLimitCount.
     */
    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private Long failLimitCount = 0L;

    /**
     * The blockYn.
     */
    @Column(nullable = false, length = 1)
    @ColumnDefault(value = "'N'")
    private String blockYn = "N";

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
     * Gets members roles domains.
     *
     * @return the members roles domains
     */
    public List<MembersRolesDomains> getMembersRolesDomains() {
        return membersRolesDomains;
    }

    /**
     * Sets members roles domains.
     *
     * @param membersRolesDomains the members roles domains
     */
    public void setMembersRolesDomains(List<MembersRolesDomains> membersRolesDomains) {
        this.membersRolesDomains = membersRolesDomains;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
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
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets view mode.
     *
     * @return the view mode
     */
    public String getViewMode() {
        return viewMode;
    }

    /**
     * Sets view mode.
     *
     * @param viewMode the view mode
     */
    public void setViewMode(String viewMode) {
        this.viewMode = viewMode;
    }

    /**
     * Gets last login date.
     *
     * @return the last login date
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * Sets last login date.
     *
     * @param lastLoginDate the last login date
     */
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets fail limit count.
     *
     * @return the fail limit count
     */
    public Long getFailLimitCount() {
        if (failLimitCount == null) {
            failLimitCount = 0L;
        }

        return failLimitCount;
    }

    /**
     * Sets fail limit count.
     *
     * @param failLimitCount the fail limit count
     */
    public void setFailLimitCount(Long failLimitCount) {
        this.failLimitCount = failLimitCount;
    }

    /**
     * Gets block yn.
     *
     * @return the block yn
     */
    public String getBlockYn() {
        return blockYn;
    }

    /**
     * Sets block yn.
     *
     * @param blockYn the block yn
     */
    public void setBlockYn(String blockYn) {
        this.blockYn = blockYn;
    }
}
//end of Member.java