/*
 * Copyright 2020 The Playce-api-Skeleton Project.
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
 * SangCheon Park       7월 23, 2020            First Draft.
 */
package com.playce.api.skeleton.controller;

import com.playce.api.skeleton.common.util.WebUtil;
import com.playce.api.skeleton.dto.PlayceMessage;
import com.playce.api.skeleton.dto.Status;
import com.playce.api.skeleton.exception.NoPermissionException;
import com.playce.api.skeleton.exception.PlayceException;
import com.playce.api.skeleton.model.Host;
import com.playce.api.skeleton.service.HostService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/servers")
@Api(tags = {"Servers"}, description = "REST APIs for Servers Menu")
public class HostController {

    private static final Logger logger = LoggerFactory.getLogger(HostController.class);

    @Autowired
    private HostService hostService;

    /**
     * Gets host list.
     *
     * @param message  the message
     * @param pageable the pageable
     * @return the host list
     */
    @ApiOperation(value = "호스트 목록 조회", notes = "호스트 목록을 조회한다.")
    @RequestMapping(value = "/host", method = RequestMethod.GET)
    public PlayceMessage getHostList(@ApiIgnore PlayceMessage message,
                                     //@ApiParam(name = "scouterServerId", value = "Scouter Server ID") @RequestParam(required = false) Long scouterServerId,
                                     @ApiIgnore @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        try {
            // get host list
            List<Host> hostList = hostService.getHostList();

            for (Host host : hostList) {
                if (StringUtils.isNotEmpty(host.getPassword())) {
                    host.setPassword(host.getPassword());
                }
            }

            message.setStatus(Status.success);
            message.setData(hostList);
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while fetch host list.", e);
            message.setStatus(Status.fail);
            message.setMessage("Can NOT fetch host list. [Reason] : " + e.getMessage());
        }

        return message;
    }

    /**
     * Gets host.
     *
     * @param message the message
     * @param id      the id
     * @return the host
     */
    @ApiOperation(value = "호스트 상세정보 조회", notes = "호스트 상세정보를 조회한다.")
    @RequestMapping(value = "/host/{id}", method = RequestMethod.GET)
    public PlayceMessage getHost(@ApiIgnore PlayceMessage message,
                                @ApiParam(name = "id", value = "Host ID", required = true) @PathVariable Long id) {
        try {
            // get host detail
            Host host = hostService.getHost(id);

            if (host == null) {
                throw new PlayceException("Host not exists.");
            }

            if (StringUtils.isNotEmpty(host.getPassword())) {
                host.setPassword(host.getPassword());
            }

            message.setStatus(Status.success);
            message.setData(host);
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while fetch host.", e);
            message.setStatus(Status.fail);
            message.setMessage("Can NOT fetch host. [Reason] : " + e.getMessage());
        }

        return message;
    }

    /**
     * Create host.
     *
     * @param message the message
     * @param host    the host
     * @param keyFile the key file
     * @return the wasup message
     */
    @ApiOperation(value = "호스트 신규 등록", notes = "호스트를 신규 등록한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ipAddress", value = "Host IP Address", required = true, dataType = "string", paramType = "formData"),
            @ApiImplicitParam(name = "port", value = "SSH Port", required = true, dataType = "int", paramType = "formData"),
            @ApiImplicitParam(name = "username", value = "Username", required = true, dataType = "string", paramType = "formData"),
            @ApiImplicitParam(name = "password", value = "Password", required = false, dataType = "string", paramType = "formData"),
            @ApiImplicitParam(name = "sudoerYn", value = "Sudo Enabled", required = false, dataType = "string", paramType = "formData"),
            //@ApiImplicitParam(name = "keyFile", value = "Select the file to Upload", required = false, dataType = "file", paramType = "form"),
            @ApiImplicitParam(name = "name", value = "Host Name", required = true, dataType = "string", paramType = "formData"),
            @ApiImplicitParam(name = "agentInstallPath", value = "Agent Install Path", required = false, dataType = "string", paramType = "formData"),
            @ApiImplicitParam(name = "description", value = "Description", required = false, dataType = "string", paramType = "formData")
    })
    @RequestMapping(value = "/host", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PlayceMessage createHost(@ApiIgnore PlayceMessage message, @ApiIgnore Host host,
                                   @ApiParam(name = "keyFile", value = "Select the file to Upload", required = false)
                                   @RequestPart(value = "keyFile", required = false) MultipartFile keyFile) {
        try {
            // admin 권한 체크
            if (!WebUtil.hasRole(1L)) {
                message.setCode(403);
                throw new NoPermissionException("You don’t have permission to create host.");
            }

            if (StringUtils.isNotEmpty(host.getPassword())) {
                host.setPassword(host.getPassword());
            }

            host.setCreateUser(WebUtil.getId());
            host.setUpdateUser(WebUtil.getId());
            host.setMonitoringYn("Y");

            // get host detail
            host = hostService.createHost(host, keyFile);

            message.setStatus(Status.success);
            message.setData(host);
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while insert host.", e);
            message.setStatus(Status.fail);
            message.setMessage("Can NOT insert host or start agent. [Reason] : " + e.getMessage());
        }

        return message;
    }
}
//end of HostController.java