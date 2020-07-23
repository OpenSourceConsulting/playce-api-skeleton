/*
 * Copyright 2019 The Playce-WASUP Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Revision History
 * Author            Date                Description
 * ---------------  ----------------    ------------
 * Jaeeon Bae       Feb 20, 2019            First Draft.
 */
package com.playce.api.skeleton.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * <pre>
 *
 * </pre>
 *
 * @author Jaeeon Bae
 * @version 1.0
 */
@Entity
@Where(clause = "delete_yn='N'")
public class History extends BaseDomain {

    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="history_generator")
    @GenericGenerator(name = "history_generator", strategy = "native")
    @ApiModelProperty(position = 1, hidden = true)
    private Long id;

    /**
     * Process UUID.
     */
    @Column(nullable = false)
    @ApiModelProperty(position = 2)
    private String processUUID;

    /**
     * The Code.
     */
    @Column(nullable = false)
    @ApiModelProperty(position = 3)
    private int code;

    /**
     * The Title.
     */
    @Column(nullable = false)
    @ApiModelProperty(position = 4)
    private String title;

    /**
     * The Wizard Id.
     */
    @Column(nullable = true)
    @ApiModelProperty(position = 5)
    private Long wizardId;

    /**
     * The Cluster Id.
     */
    @Column(nullable = true)
    @ApiModelProperty(position = 6)
    private Long clusterId;

    /**
     * The Domain Id.
     */
    @Column(nullable = true)
    @ApiModelProperty(position = 7)
    private Long domainId;

    /**
     * The Host Id.
     */
    @Column(nullable = true)
    @ApiModelProperty(position = 8)
    private Long hostId;

    /**
     * The Engine id.
     */
    @Column(nullable = true)
    @ApiModelProperty(position = 9)
    private Long engineId;

    /**
     * The Web App Server Id.
     */
    @Column(nullable = true)
    @ApiModelProperty(position = 10)
    private Long webAppServerId;

    /**
     * The Web Server Id.
     */
    @Column(nullable = true)
    @ApiModelProperty(position = 11)
    private Long webServerId;

    /**
     * The Session Server Id.
     */
    @Column(nullable = true)
    @ApiModelProperty(position = 12)
    private Long sessionServerId;

    /**
     * The Scouter Server Id.
     */
    @Column(nullable = true)
    @ApiModelProperty(position = 13)
    private Long scouterServerId;

    /**
     * The Atlassian server id.
     */
    @Column(nullable = true)
    @ApiModelProperty(position = 14)
    private Long atlassianServerId;

    /**
     * The Datasource Id.
     */
    @Column(nullable = true)
    @ApiModelProperty(position = 15)
    private Long datasourceId;

    /**
     * The Application Id.
     */
    @Column(nullable = true)
    @ApiModelProperty(position = 16)
    private Long applicationId;

    /**
     * The Access control id.
     */
    @Column(nullable = true)
    @ApiModelProperty(position = 17)
    private Long accessControlId;

    /**
     * The Config File.
     */
    @Column(nullable = true)
    @ApiModelProperty(position = 18)
    private String configFile;

    /**
     * The Status Code.
     */
    @Column(nullable = false)
    @ApiModelProperty(position = 19)
    private String statusCode;

    /**
     * The Message.
     */
    @Column(nullable = true)
    @ApiModelProperty(position = 20)
    private String message;

    /**
     * The Task User.
     */
    @Column(nullable = true)
    @ColumnDefault(value = "1")
    @ApiModelProperty(position = 21)
    private Long taskUser;

    /**
     * The Read yn.
     */
    @Column(length = 1, nullable = false)
    @ColumnDefault(value = "'N'")
    @ApiModelProperty(hidden = true)
    private String readYn = "N";

    /**
     * The Delete yn.
     */
    @Column(length = 1, nullable = false)
    @ColumnDefault(value = "'N'")
    @ApiModelProperty(hidden = true)
    private String deleteYn = "N";

    /**
     * THe Create Date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    @ColumnDefault(value = "current_timestamp")
    @ApiModelProperty(position = 22, hidden = true)
    private Date createDate = new Date();

    /**
     * The End Date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    @ColumnDefault(value = "current_timestamp")
    @ApiModelProperty(position = 23, hidden = true)
    private Date endDate = new Date();

    /**
     * The History Cnt.
     */
    @Transient
    @ApiModelProperty(hidden = true)
    private Long historyCnt;

    /**
     * The Code String.
     */
    @Transient
    @ApiModelProperty(hidden = true)
    private String codeStr;

    /**
     * The Cluser Name.
     */
    @Transient
    @ApiModelProperty(hidden = true)
    private String clusterName;

    /**
     * The Domain Name.
     */
    @Transient
    @ApiModelProperty(hidden = true)
    private String domainName;

    /**
     * The Host Name.
     */
    @Transient
    @ApiModelProperty(hidden = true)
    private String hostName;

    /**
     * The Web App Server Name.
     */
    @Transient
    @ApiModelProperty(hidden = true)
    private String webAppServerName;

    /**
     * The Web Server Name.
     */
    @Transient
    @ApiModelProperty(hidden = true)
    private String webServerName;

    /**
     * The Session Server Name.
     */
    @Transient
    @ApiModelProperty(hidden = true)
    private String sessionServerName;

    /**
     * The Datasource Name.
     */
    @Transient
    @ApiModelProperty(hidden = true)
    private String datasourceName;

    /**
     * The Application Name.
     */
    @Transient
    @ApiModelProperty(hidden = true)
    private String applicationName;

    /**
     * The Access control name.
     */
    @Transient
    @ApiModelProperty(hidden = true)
    private String accessControlName;

    /**
     * The User Id.
     */
    @Transient
    @ApiModelProperty(hidden = true)
    private String userId;

    /**
     * Instantiates a new History.
     */
    public History() {

    }

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
     * Gets process uuid.
     *
     * @return the process uuid
     */
    public String getProcessUUID() {
        return processUUID;
    }

    /**
     * Sets process uuid.
     *
     * @param processUUID the process uuid
     */
    public void setProcessUUID(String processUUID) {
        this.processUUID = processUUID;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets wizard id.
     *
     * @return the wizard id
     */
    public Long getWizardId() {
        return wizardId;
    }

    /**
     * Sets wizard id.
     *
     * @param wizardId the wizard id
     */
    public void setWizardId(Long wizardId) {
        this.wizardId = wizardId;
    }

    /**
     * Gets cluster id.
     *
     * @return the cluster id
     */
    public Long getClusterId() {
        return clusterId;
    }

    /**
     * Sets cluster id.
     *
     * @param clusterId the cluster id
     */
    public void setClusterId(Long clusterId) {
        this.clusterId = clusterId;
    }

    /**
     * Gets domain id.
     *
     * @return the domain id
     */
    public Long getDomainId() {
        return domainId;
    }

    /**
     * Sets domain id.
     *
     * @param domainId the domain id
     */
    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    /**
     * Gets host id.
     *
     * @return the host id
     */
    public Long getHostId() {
        return hostId;
    }

    /**
     * Sets host id.
     *
     * @param hostId the host id
     */
    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    /**
     * Gets engine id.
     *
     * @return the engine id
     */
    public Long getEngineId() {
        return engineId;
    }

    /**
     * Sets engine id.
     *
     * @param engineId the engine id
     */
    public void setEngineId(Long engineId) {
        this.engineId = engineId;
    }

    /**
     * Gets web app server id.
     *
     * @return the web app server id
     */
    public Long getWebAppServerId() {
        return webAppServerId;
    }

    /**
     * Sets web app server id.
     *
     * @param webAppServerId the web app server id
     */
    public void setWebAppServerId(Long webAppServerId) {
        this.webAppServerId = webAppServerId;
    }

    /**
     * Gets web server id.
     *
     * @return the web server id
     */
    public Long getWebServerId() {
        return webServerId;
    }

    /**
     * Sets web server id.
     *
     * @param webServerId the web server id
     */
    public void setWebServerId(Long webServerId) {
        this.webServerId = webServerId;
    }

    /**
     * Gets session server id.
     *
     * @return the session server id
     */
    public Long getSessionServerId() {
        return sessionServerId;
    }

    /**
     * Sets session server id.
     *
     * @param sessionServerId the session server id
     */
    public void setSessionServerId(Long sessionServerId) {
        this.sessionServerId = sessionServerId;
    }

    /**
     * Gets scouter server id.
     *
     * @return the scouter server id
     */
    public Long getScouterServerId() {
        return scouterServerId;
    }

    /**
     * Sets scouter server id.
     *
     * @param scouterServerId the scouter server id
     */
    public void setScouterServerId(Long scouterServerId) {
        this.scouterServerId = scouterServerId;
    }

    /**
     * Gets atlassian server id.
     *
     * @return the atlassian server id
     */
    public Long getAtlassianServerId() {
        return atlassianServerId;
    }

    /**
     * Sets atlassian server id.
     *
     * @param atlassianServerId the atlassian server id
     */
    public void setAtlassianServerId(Long atlassianServerId) {
        this.atlassianServerId = atlassianServerId;
    }

    /**
     * Gets datasource id.
     *
     * @return the datasource id
     */
    public Long getDatasourceId() {
        return datasourceId;
    }

    /**
     * Sets datasource id.
     *
     * @param datasourceId the datasource id
     */
    public void setDatasourceId(Long datasourceId) {
        this.datasourceId = datasourceId;
    }

    /**
     * Gets application id.
     *
     * @return the application id
     */
    public Long getApplicationId() {
        return applicationId;
    }

    /**
     * Sets application id.
     *
     * @param applicationId the application id
     */
    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * Gets access control id.
     *
     * @return the access control id
     */
    public Long getAccessControlId() {
        return accessControlId;
    }

    /**
     * Sets access control id.
     *
     * @param accessControlId the access control id
     */
    public void setAccessControlId(Long accessControlId) {
        this.accessControlId = accessControlId;
    }

    /**
     * Gets config file.
     *
     * @return the config file
     */
    public String getConfigFile() {
        return configFile;
    }

    /**
     * Sets config file.
     *
     * @param configFile the config file
     */
    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    /**
     * Gets status code.
     *
     * @return the status code
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets status code.
     *
     * @param statusCode the status code
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets task user.
     *
     * @return the task user
     */
    public Long getTaskUser() {
        return taskUser;
    }

    /**
     * Sets task user.
     *
     * @param taskUser the task user
     */
    public void setTaskUser(Long taskUser) {
        this.taskUser = taskUser;
    }

    /**
     * Gets read yn.
     *
     * @return the read yn
     */
    public String getReadYn() {
        return readYn;
    }

    /**
     * Sets read yn.
     *
     * @param readYn the read yn
     */
    public void setReadYn(String readYn) {
        this.readYn = readYn;
    }

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
     * Gets end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets history cnt.
     *
     * @return the history cnt
     */
    public Long getHistoryCnt() {
        return historyCnt;
    }

    /**
     * Sets history cnt.
     *
     * @param historyCnt the history cnt
     */
    public void setHistoryCnt(Long historyCnt) {
        this.historyCnt = historyCnt;
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

    /**
     * Gets domain name.
     *
     * @return the domain name
     */
    public String getDomainName() {
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

    /**
     * Gets host name.
     *
     * @return the host name
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Sets host name.
     *
     * @param hostName the host name
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * Gets web app server name.
     *
     * @return the web app server name
     */
    public String getWebAppServerName() {
        return webAppServerName;
    }

    /**
     * Sets web app server name.
     *
     * @param webAppServerName the web app server name
     */
    public void setWebAppServerName(String webAppServerName) {
        this.webAppServerName = webAppServerName;
    }

    /**
     * Gets web server name.
     *
     * @return the web server name
     */
    public String getWebServerName() {
        return webServerName;
    }

    /**
     * Sets web server name.
     *
     * @param webServerName the web server name
     */
    public void setWebServerName(String webServerName) {
        this.webServerName = webServerName;
    }

    /**
     * Gets session server name.
     *
     * @return the session server name
     */
    public String getSessionServerName() {
        return sessionServerName;
    }

    /**
     * Sets session server name.
     *
     * @param sessionServerName the session server name
     */
    public void setSessionServerName(String sessionServerName) {
        this.sessionServerName = sessionServerName;
    }

    /**
     * Gets datasource name.
     *
     * @return the datasource name
     */
    public String getDatasourceName() {
        return datasourceName;
    }

    /**
     * Sets datasource name.
     *
     * @param datasourceName the datasource name
     */
    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName;
    }

    /**
     * Gets application name.
     *
     * @return the application name
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Sets application name.
     *
     * @param applicationName the application name
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    /**
     * Gets access control name.
     *
     * @return the access control name
     */
    public String getAccessControlName() {
        return accessControlName;
    }

    /**
     * Sets access control name.
     *
     * @param accessControlName the access control name
     */
    public void setAccessControlName(String accessControlName) {
        this.accessControlName = accessControlName;
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

}
//end of History.java