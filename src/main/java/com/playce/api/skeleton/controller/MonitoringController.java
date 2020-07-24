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
package com.playce.api.skeleton.controller;

import com.playce.api.skeleton.dto.PlayceMessage;
import com.playce.api.skeleton.dto.Status;
import com.playce.api.skeleton.service.MonitoringService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/monitoring")
@Api(tags = {"Monitoring"}, description = "Rest APIs for Monitoring Menu")
public class MonitoringController {

    private static final Logger logger = LoggerFactory.getLogger(MonitoringController.class);

    @Autowired
    private MonitoringService monitoringService;

    /**
     * <pre>
     * 호스트 모니터링 정보 조회.
     * </pre>
     * @param message
     * @param hostIds
     * @param timeRange
     * @return
     */
    @ApiOperation(value = "호스트 모니터링 정보 조회", notes = "호스트 모니터링 정보를 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hostIds", value = "Host ID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "timeRange", value = "Time Range", required = false, dataType = "string", paramType = "query", defaultValue = "3H"),
            @ApiImplicitParam(name = "searchStartTime", value = "Search Start Time", required = false, dataType = "long", paramType = "query", defaultValue = "1592633100000"),
            @ApiImplicitParam(name = "searchEndTime", value = "Search End Time", required = false, dataType = "long", paramType = "query", defaultValue = "1592634600000"),
            @ApiImplicitParam(name = "searchPeriod", value = "Search Period(5M / 10M / 20M)", required = false, dataType = "string", paramType = "query", defaultValue = "5M")
    })
    @RequestMapping(value = "/host", method = RequestMethod.GET)
    public PlayceMessage getHostMonitorList(@ApiIgnore PlayceMessage message,
                                            @RequestParam(required = false) String hostIds,
                                            @RequestParam(required = false) String timeRange,
                                            @RequestParam(required = false) Long searchStartTime,
                                            @RequestParam(required = false) Long searchEndTime,
                                            @RequestParam(required = false) String searchPeriod) {
        try {
            long currentTimeMillis = System.currentTimeMillis();

            // get Host monitor data
            Map<String, Object> hostMonitors = monitoringService
                    .getHostMonitorList(hostIds, timeRange, currentTimeMillis, searchStartTime, searchEndTime, searchPeriod);

            message.setStatus(Status.success);
            message.setData(hostMonitors);
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while fetch host monitor.", e);
            message.setStatus(Status.fail);
            message.setMessage("Can NOT get Host monitor. [Reason] : " + e.getMessage());
        }

        return message;
    }
}
//end of MonitoringController.java