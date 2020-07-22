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
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
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
@Entity
@Where(clause = "delete_yn='N'")
public class Cluster extends AuditBaseDomain {

    /**
     * Instantiates a new Cluster.
     */
    public Cluster() {

    }

    /**
     * Instantiates a new Cluster.
     *
     * @param id the id
     */
    public Cluster(Long id) {
        this.id = id;
    }

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="cluster_generator")
    @GenericGenerator(name = "cluster_generator", strategy = "native")
    private Long id;

    /**
     * The Domain.
     */
    @OneToMany(mappedBy = "cluster", fetch = FetchType.LAZY)
    @ApiModelProperty(hidden = true)
    private List<Domain> domain;

    /**
     * The Name.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The Description.
     */
    @Column(nullable = true)
    private String description;

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
     * Gets domain.
     *
     * @return the domain
     */
    public List<Domain> getDomain() {
        return domain;
    }

    /**
     * Sets domain.
     *
     * @param domain the domain
     */
    public void setDomain(List<Domain> domain) {
        this.domain = domain;
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

}
//end of Cluster.java