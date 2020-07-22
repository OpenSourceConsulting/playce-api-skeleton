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

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * <pre>
 * AuditBaseDomain contains below default columns
 *
 * <ul>
 *     <li>delete_yn</li>
 *     <li>create_user</li>
 *     <li>create_date</li>
 *     <li>update_user</li>
 *     <li>update_date</li>
 * </ul>
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
@MappedSuperclass
public class AuditBaseDomain extends BaseDomain {

    /**
     * The Delete yn.
     */
    @Column(length = 1, nullable = false)
    @ColumnDefault(value = "'N'")
    @ApiModelProperty(hidden = true)
    private String deleteYn = "N";

    /**
     * The Create user.
     */
    @Column
    @ColumnDefault(value = "1")
    @ApiModelProperty(hidden = true)
    private Long createUser;

    /**
     * The Create date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    @ColumnDefault(value = "current_timestamp")
    //@CreatedDate
    @ApiModelProperty(hidden = true)
    private Date createDate = new Date();

    /**
     * The Update user.
     */
    @Column
    @ColumnDefault(value = "1")
    @ApiModelProperty(hidden = true)
    private Long updateUser;

    /**
     * The Update date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @ColumnDefault(value = "current_timestamp")
    //@LastModifiedDate
    @ApiModelProperty(hidden = true)
    private Date updateDate = new Date();

    /**
     * Gets delete yn.
     *
     * @return the delete yn
     */
    public String getDeleteYn() {
        return deleteYn;
    }

    /**
     * Sets delete yn.
     *
     * @param deleteYn the delete yn
     */
    public void setDeleteYn(String deleteYn) {
        this.deleteYn = deleteYn;
    }

    /**
     * Gets create user.
     *
     * @return the create user
     */
    public Long getCreateUser() {
        return createUser;
    }

    /**
     * Sets create user.
     *
     * @param createUser the create user
     */
    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    /**
     * Gets create date.
     *
     * @return the create date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets create date.
     *
     * @param createDate the create date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets update user.
     *
     * @return the update user
     */
    public Long getUpdateUser() {
        return updateUser;
    }

    /**
     * Sets update user.
     *
     * @param updateUser the update user
     */
    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * Gets update date.
     *
     * @return the update date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets update date.
     *
     * @param updateDate the update date
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return ", deleteYn='" + deleteYn + '\'' +
                ", createUser=" + createUser +
                ", createDate=" + createDate +
                ", updateUser=" + updateUser +
                ", updateDate=" + updateDate +
                '}';
    }
}
//end of AuditBaseDomain.java