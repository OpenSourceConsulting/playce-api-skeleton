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
 * @author Jaeeon Bae
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
            @ApiImplicitParam(name = "searchStartTime", value = "Search Start Time", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "searchEndTime", value = "Search End Time", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "searchPeriod", value = "Search Period", required = false, dataType = "string", paramType = "query")
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

//            if (timeRange == null) {
//                throw new WasupException("Time range must not be null.");
//            }

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