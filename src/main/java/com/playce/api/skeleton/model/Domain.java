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

import com.fasterxml.jackson.annotation.*;
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
public class Domain extends AuditBaseDomain {

    /**
     * Instantiates a new Domain.
     */
    public Domain() {
    }

    /**
     * Instantiates a new Domain.
     *
     * @param id the id
     */
    public Domain(Long id) {
        this.id = id;
    }

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="domain_generator")
    @GenericGenerator(name = "domain_generator", strategy = "native")
    @ApiModelProperty(hidden = true)
    private Long id;

    /**
     * The Members roles domains.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "domain", fetch = FetchType.LAZY)
    private List<MembersRolesDomains> membersRolesDomains;

    /**
     * The Name.
     */
    @Column(nullable = false)
    @ApiModelProperty(position = 1)
    private String name;

    /**
     * The Description
     */
    @Column(nullable = true)
    @ApiModelProperty(position = 2)
    private String description;

    /**
     * The Cluster.
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "cluster_id")
    @JsonProperty("clusterId")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ApiModelProperty(position = 3)
    private Cluster cluster;

    /**
     * The Cluster name.
     */
    @Transient
    @ApiModelProperty(hidden = true)
    private String clusterName;

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
     * Gets cluster.
     *
     * @return the cluster
     */
    public Cluster getCluster() {
        return cluster;
    }

    /**
     * Sets cluster.
     *
     * @param cluster list the cluster
     */
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    /**
     * Gets cluster name.
     *
     * @return the cluster name
     */
    public String getClusterName() {
        return clusterName;
    }

    /**
     * Sets cluster name.
     *
     * @param clusterName the cluster name
     */
    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

}
//end of Domain.java