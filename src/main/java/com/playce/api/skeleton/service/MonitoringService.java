package com.playce.api.skeleton.service;

import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 *
 * @author Jaeeon Bae
 * @version 1.0
 */
public interface MonitoringService {

    Map<String, Object> getHostMonitorList(String hostIds, String timeRange, long currentTimeMillis, Long searchStartTime, Long searchEndTime, String searchPeriod);
}
//end of MonitoringService.java