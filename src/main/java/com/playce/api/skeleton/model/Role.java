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
public class Role extends BaseDomain {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="role_generator")
    @GenericGenerator(name = "role_generator", strategy = "native")
    private Long id;

    /**
     * The Members roles domains.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<MembersRolesDomains> membersRolesDomains;

    /**
     * The Name.
     */
    @Column(nullable = false, unique = true)
    private String name;

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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

}
//end of Role.java