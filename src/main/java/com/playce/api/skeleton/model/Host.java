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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.ColumnDefault;
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
public class Host extends AuditBaseDomain {

    /**
     * Instantiates a new Host.
     */
    public Host() {
    }

    /**
     * Instantiates a new Host.
     *
     * @param id the id
     */
    public Host(Long id) {
        this.id = id;
    }

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="host_generator")
    @GenericGenerator(name = "host_generator", strategy = "native")
    @ApiModelProperty(hidden = true)
    private Long id;

    /**
     * The Detail.
     */
    @OneToOne(mappedBy = "host", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ApiModelProperty(hidden = true)
    private HostDetail detail;

    /**
     * The Host name.
     */
    @Column(nullable = false)
    @ApiModelProperty(position = 5, example = "sample host")
    private String name;

    /**
     * The Ip address.
     */
    @Column(nullable = false)
    @ApiModelProperty(position = 1, example = "127.0.0.1")
    private String ipAddress;

    /**
     * The Port.
     */
    @Column
    @ApiModelProperty(position = 2, example = "22")
    private Integer port;

    /**
     * The Username.
     */
    @Column
    @ApiModelProperty(position = 3, example = "root")
    private String username;

    /**
     * The Password.
     */
    @Column(length = 512)
    @ApiModelProperty(position = 4, example = "root")
    private String password;

    /**
     * The sudoerYn.
     */
    @Column(length = 1)
    @ApiModelProperty(position = 5, example = "N")
    private String sudoerYn = "N";

    /**
     * The Key file path.
     */
    @Column
    @ApiModelProperty(hidden = true)
    private String keyFilePath;

    /**
     * The Key string.
     */
    @Column(length = 4096)
    @ApiModelProperty(hidden = true)
    private String keyString;

    /**
     * The Install path.
     */
    @Column
    private String agentInstallPath;

    /**
     * The Description.
     */
    @Column(nullable = true)
    @ApiModelProperty(position = 6, example = "Host Description")
    private String description;

    /**
     * The Monitoring yn.
     */
    @Column
    @ColumnDefault(value = "'Y'")
    @ApiModelProperty(hidden = true)
    private String monitoringYn = "Y";

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
     * Gets detail.
     *
     * @return the detail
     */
    public HostDetail getDetail() {
        return detail;
    }

    /**
     * Sets detail.
     *
     * @param detail the detail
     */
    public void setDetail(HostDetail detail) {
        this.detail = detail;
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
     * Gets ip address.
     *
     * @return the ip address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets ip address.
     *
     * @param ipAddress the ip address
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * Sets port.
     *
     * @param port the port
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
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
     * Gets sudoerYn.
     *
     * @return the sudoerYn
     */
    public String getSudoerYn() {
        if (sudoerYn == null || "".equals(sudoerYn)) {
            sudoerYn = "N";
        }

        return sudoerYn;
    }

    /**
     * Sets sudoerYn.
     *
     * @param sudoerYn the sudoerYn
     */
    public void setSudoerYn(String sudoerYn) {
        this.sudoerYn = sudoerYn;
    }

    /**
     * Gets key file path.
     *
     * @return the key file path
     */
    public String getKeyFilePath() {
        return keyFilePath;
    }

    /**
     * Sets key file path.
     *
     * @param keyFilePath the key file path
     */
    public void setKeyFilePath(String keyFilePath) {
        this.keyFilePath = keyFilePath;
    }

    /**
     * Gets key string.
     *
     * @return the key string
     */
    public String getKeyString() {
        return keyString;
    }

    /**
     * Sets key string.
     *
     * @param keyString the key string
     */
    public void setKeyString(String keyString) {
        this.keyString = keyString;
    }

    /**
     * Gets agent install path.
     *
     * @return the agent install path
     */
    public String getAgentInstallPath() {
        return agentInstallPath;
    }

    /**
     * Sets agent install path.
     *
     * @param agentInstallPath the agent install path
     */
    public void setAgentInstallPath(String agentInstallPath) {
        this.agentInstallPath = agentInstallPath;
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
     * Gets monitoring yn.
     *
     * @return the monitoring yn
     */
    public String getMonitoringYn() {
        return monitoringYn;
    }

    /**
     * Sets monitoring yn.
     *
     * @param monitoringYn the monitoring yn
     */
    public void setMonitoringYn(String monitoringYn) {
        this.monitoringYn = monitoringYn;
    }

}
//end of Host.java