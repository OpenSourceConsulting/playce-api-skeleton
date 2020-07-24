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
package com.playce.api.skeleton.service.impl;

import com.playce.api.skeleton.exception.NoPermissionException;
import com.playce.api.skeleton.exception.PlayceException;
import com.playce.api.skeleton.model.Host;
import com.playce.api.skeleton.model.HostMonitor;
import com.playce.api.skeleton.repository.HostMonitorRepository;
import com.playce.api.skeleton.repository.HostRepository;
import com.playce.api.skeleton.service.MonitoringService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.playce.api.skeleton.common.constant.PlayceConstants.*;

/**
 * <pre>
 *
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
@Service
@Transactional(rollbackFor = {PlayceException.class, NoPermissionException.class})
public class MonitoringServiceImpl implements MonitoringService {

    @Autowired
    private HostMonitorRepository hostMonitorRepository;

    @Autowired
    private HostRepository hostRepository;

    @Override
    public Map<String, Object> getHostMonitorList(String hostIds, String timeRange, long currentTimeMillis,
                                                  Long searchStartTime, Long searchEndTime, String searchPeriod) {
        long oneMinute = 1000 * 60;
        long fiveMinute = 5 * oneMinute;
        long tenMinute = 10 * oneMinute;
        long twentyMinute = 20 * oneMinute;
        long thirtyMinute = 30 * oneMinute;
        long oneHour = oneMinute * 60;
        long twoHour = 2 * oneHour;
        long threeHour = 3 * oneHour;
        long sixHour = 6 * oneHour;
        long twelveHour = 2 * sixHour;
        long oneDay = oneHour * 24;
        long oneWeek = oneDay * 7;
        long oneMonth = oneDay * 30;

        //Date currentDate = new Date(currentTimeMillis / fiveMinute * fiveMinute);
        Map<String, Object> dataMap = null;

        if (searchStartTime == null && searchEndTime == null) {
            if (MONITOR_TIME_ONE_HOUR.equals(timeRange)) {
                // 1 hour
                long beforeTimestamp = (currentTimeMillis - oneHour) / fiveMinute * fiveMinute;
                Date currentDate = new Date(currentTimeMillis / fiveMinute * fiveMinute);
                Date beforeDate = new Date(beforeTimestamp);

                // get calc host monitor list. (5분 단위의 데이터 조회)
                dataMap = getCalcHostMonitorList(hostIds, currentDate, beforeDate, MONITOR_TIME_FIVE_MINUTE, fiveMinute);

            } else if (MONITOR_TIME_THREE_HOUR.equals(timeRange)) {
                // default 3 hour
                long beforeTimestamp = (currentTimeMillis - (3 * oneHour)) / fiveMinute * fiveMinute;
                Date currentDate = new Date(currentTimeMillis / fiveMinute * fiveMinute);
                Date beforeDate = new Date(beforeTimestamp);

                // get calc host monitor list. (5분 단위의 데이터 조회)
                dataMap = getCalcHostMonitorList(hostIds, currentDate, beforeDate, MONITOR_TIME_FIVE_MINUTE, fiveMinute);

            } else if (MONITOR_TIME_SIX_HOUR.equals(timeRange)) {
                // 6 hour
                long beforeTimestamp = (currentTimeMillis - (6 * oneHour)) / tenMinute * tenMinute;
                Date currentDate = new Date(currentTimeMillis / tenMinute * tenMinute);
                Date beforeDate = new Date(beforeTimestamp);

                // get calc host monitor list. (10분 단위의 데이터 조회)
                dataMap = getCalcHostMonitorList(hostIds, currentDate, beforeDate, MONITOR_TIME_TEN_MINUTE, tenMinute);

            } else if (MONITOR_TIME_TWELVE_HOUR.equals(timeRange)) {
                // 12 hour
                long beforeTimestamp = (currentTimeMillis - (12 * oneHour)) / twentyMinute * twentyMinute;
                Date currentDate = new Date(currentTimeMillis / twentyMinute * twentyMinute);
                Date beforeDate = new Date(beforeTimestamp);

                // get calc host monitor list. (20분 단위의 데이터 조회)
                dataMap = getCalcHostMonitorList(hostIds, currentDate, beforeDate, MONITOR_TIME_TWENTY_MINUTE, twentyMinute);

            } else if (MONITOR_TIME_ONE_DAY.equals(timeRange)) {
                // 1 day
                long beforeTimestamp = (currentTimeMillis - oneDay) / thirtyMinute * thirtyMinute;
                Date currentDate = new Date(currentTimeMillis / thirtyMinute * thirtyMinute);
                Date beforeDate = new Date(beforeTimestamp);

                // get calc host monitor list. (30분 단위의 데이터 조회)
                dataMap = getCalcHostMonitorList(hostIds, currentDate, beforeDate, MONITOR_TIME_THIRTY_MINUTE, thirtyMinute);

            } else if (MONITOR_TIME_THREE_DAY.equals(timeRange)) {
                // 3 day
                long beforeTimestamp = (currentTimeMillis - (3 * oneDay)) / twoHour * twoHour;
                Date currentDate = new Date(currentTimeMillis / twoHour * twoHour);
                Date beforeDate = new Date(beforeTimestamp);

                // get calc host monitor list. (2시간 단위의 데이터 조회)
                dataMap = getCalcHostMonitorList(hostIds, currentDate, beforeDate, MONITOR_TIME_TWO_HOUR, twoHour);

            } else if (MONITOR_TIME_ONE_WEEK.equals(timeRange)) {
                // 1 week
                long beforeTimestamp = (currentTimeMillis - oneWeek) / threeHour * threeHour;
                Date currentDate = new Date(currentTimeMillis / threeHour * threeHour);
                Date beforeDate = new Date(beforeTimestamp);

                // get calc host monitor list. (3시간 단위의 데이터 조회)
                dataMap = getCalcHostMonitorList(hostIds, currentDate, beforeDate, MONITOR_TIME_THREE_HOUR, threeHour);

            } else if (MONITOR_TIME_TWO_WEEK.equals(timeRange)) {
                // 2 week
                long beforeTimestamp = (currentTimeMillis - (2 * oneWeek)) / sixHour * sixHour;
                Date currentDate = new Date(currentTimeMillis / sixHour * sixHour);
                Date beforeDate = new Date(beforeTimestamp);

                // get calc host monitor list. (6시간 단위의 데이터 조회)
                dataMap = getCalcHostMonitorList(hostIds, currentDate, beforeDate, MONITOR_TIME_SIX_HOUR, sixHour);
            } else if (MONITOR_TIME_ONE_MONTH.equals(timeRange)) {
                // 30 day
                long beforeTimestamp = (currentTimeMillis - oneMonth) / twelveHour * twelveHour;
                Date currentDate = new Date(currentTimeMillis / twelveHour * twelveHour);
                Date beforeDate = new Date(beforeTimestamp);

                // get calc host monitor list. (12시간 단위의 데이터 조회)
                dataMap = getCalcHostMonitorList(hostIds, currentDate, beforeDate, MONITOR_TIME_TWELVE_HOUR, twelveHour);
            }
        } else {
            // custom period
            Date startDate = new Date(searchStartTime.longValue());
            Date endDate = new Date(searchEndTime.longValue());
            Long unit = null;

            if (MONITOR_TIME_FIVE_MINUTE.equals(searchPeriod)) {
                unit = fiveMinute;
            } else if (MONITOR_TIME_TEN_MINUTE.equals(searchPeriod)) {
                unit = tenMinute;
            } else if (MONITOR_TIME_TWENTY_MINUTE.equals(searchPeriod)) {
                unit = twentyMinute;
            } else if (MONITOR_TIME_THIRTY_MINUTE.equals(searchPeriod)) {
                unit = thirtyMinute;
            } else if (MONITOR_TIME_TWO_HOUR.equals(searchPeriod)) {
                unit = twoHour;
            } else if (MONITOR_TIME_THREE_HOUR.equals(searchPeriod)) {
                unit = threeHour;
            } else if (MONITOR_TIME_SIX_HOUR.equals(searchPeriod)) {
                unit = sixHour;
            } else if (MONITOR_TIME_TWELVE_HOUR.equals(searchPeriod)) {
                unit = twelveHour;
            }

            dataMap = getCalcHostMonitorList(hostIds, endDate, startDate, searchPeriod, unit);
        }

        return dataMap;
    }

    /**
     * 파라미터로 넘어온 데이터로 Host Monitor data 를 chart data 로 변환 후 리턴한다.
     *
     * @param hostIds
     * @param currentDate
     * @param beforeDate
     * @return
     */
    private Map<String, Object> getCalcHostMonitorList(String hostIds, Date currentDate, Date beforeDate, String type, long unit) {
        List<HostMonitor> hostMonitorList = null;
        long oneMinute = 1000 * 60;
        long oneHour = oneMinute * 60;
        List<Long> labelList = new ArrayList<Long>();
        Map<String, Object> dataMap = new HashMap<String, Object>();

        if (StringUtils.isEmpty(hostIds)) {
            List<Host> hostList = hostRepository.findAll();
            List<Long> hostIdList = hostList.stream().map(Host::getId).collect(Collectors.toList());

            hostMonitorList = hostMonitorRepository
                    .findByMonitorDateGreaterThanEqualAndMonitorDateLessThanEqualAndTypeAndHostIdInOrderByMonitorDateAsc(beforeDate, currentDate, type, hostIdList);

            //hostMonitorList = new ArrayList<HostMonitor>();

            long beforeTimestamp = beforeDate.getTime();
            long currentTimestamp = currentDate.getTime();

            for (long i = beforeTimestamp; i <= currentTimestamp; i+= unit) {
                labelList.add(i);
            }

            dataMap.put("times", labelList);
            dataMap.put("data", hostMonitorList);
        } else {
            List<Long> hostIdList = Arrays.stream(hostIds.split(",")).map(Long::parseLong).collect(Collectors.toList());

            hostMonitorList = hostMonitorRepository
                    .findByMonitorDateGreaterThanEqualAndMonitorDateLessThanEqualAndTypeAndHostIdInOrderByMonitorDateAsc(beforeDate, currentDate, type, hostIdList);

            long beforeTimestamp = beforeDate.getTime();
            long currentTimestamp = currentDate.getTime();

            for (long i = beforeTimestamp; i <= currentTimestamp; i+= unit) {
                labelList.add(i);
            }

            dataMap.put("times", labelList);
            dataMap.put("data", hostMonitorList);
        }

        return dataMap;
    }
}
//end of MonitoringServiceImpl.java