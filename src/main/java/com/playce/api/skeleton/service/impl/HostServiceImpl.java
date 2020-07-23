package com.playce.api.skeleton.service.impl;

import com.playce.api.skeleton.exception.NoPermissionException;
import com.playce.api.skeleton.exception.PlayceException;
import com.playce.api.skeleton.model.Host;
import com.playce.api.skeleton.repository.HistoryRepository;
import com.playce.api.skeleton.repository.HostRepository;
import com.playce.api.skeleton.service.HostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author Jaeeon Bae
 * @version 1.0
 */
@Service
@Transactional(rollbackFor = {PlayceException.class, NoPermissionException.class})
public class HostServiceImpl implements HostService {

    private static final Logger logger = LoggerFactory.getLogger(HostServiceImpl.class);

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public List<Host> getHostList() throws PlayceException {
        return hostRepository.findAll();
    }

    @Override
    public List<Host> getHostListWithPermission() throws PlayceException {
        List<Host> hostList = getHostList();

//        for (Host host : hostList) {
//            for (Iterator<WebAppServer> it = host.getWebAppServers().iterator(); it.hasNext(); ) {
//                WebAppServer was = it.next();
//
//                if (!WebUtil.hasReadPermission(was.getDomain().getId())) {
//                    it.remove();
//                }
//            }
//
//            for (Iterator<WebServer> it = host.getWebServers().iterator(); it.hasNext(); ) {
//                WebServer ws = it.next();
//
//                if (!WebUtil.hasReadPermission(ws.getDomain().getId())) {
//                    it.remove();
//                }
//            }
//
//            for (Iterator<SessionServer> it = host.getSessionServers().iterator(); it.hasNext(); ) {
//                SessionServer ss = it.next();
//
//                boolean hasPermission = false;
//                for (Domain domain : ss.getCluster().getDomain()) {
//                    hasPermission = false;
//
//                    if (WebUtil.hasReadPermission(domain.getId())) {
//                        hasPermission = true;
//                        break;
//                    }
//                }
//
//                if (!hasPermission) {
//                    it.remove();
//                }
//            }
//        }

        return hostList;
    }

    @Override
    public List<Host> getHostListWithPermission(Long scouterServerId) throws PlayceException {
        List<Host> hostList = getHostListWithPermission();

        if (scouterServerId != null && scouterServerId > 0L) {

            Host host = null;
            for (Iterator<Host> it = hostList.iterator(); it.hasNext(); ) {
                host = it.next();

//                if (host.getScouterServer() == null || !host.getScouterServer().getId().equals(scouterServerId)) {
//                    it.remove();
//                }
            }
        }

        return hostList;
    }

    @Override
    public Host getHost(Long hostId) throws PlayceException {
        Host host = hostRepository.findById(hostId).orElse(null);

//        if (host != null) {
//            UsageInfo cpuUsage = UsageSummary.getCpu(host.getId());
//            UsageInfo memoryUsage = UsageSummary.getMemory(host.getId());
//            UsageInfo diskUsage = UsageSummary.getDisk(host.getId());
//
//            HostAlarm hostAlarm = host.getAlarm();
//            hostAlarm.setCpuValue(cpuUsage == null ? null : cpuUsage.getValue());
//            hostAlarm.setMemValue(memoryUsage == null ? null : memoryUsage.getValue());
//            hostAlarm.setDiskValue(diskUsage == null ? null : diskUsage.getValue());
//
//            // calculate for alarm level
//            hostAlarm.setCpuAlarmLevel(cpuUsage == null ? null : calcAlarmLevelForCpu(hostAlarm));
//            hostAlarm.setMemAlarmLevel(memoryUsage == null ? null : calcAlarmLevelForMemory(hostAlarm));
//            hostAlarm.setDiskAlarmLevel(diskUsage == null ? null : calcAlarmLevelForDisk(hostAlarm));
//        }

        return host;
    }
}
//end of HostServiceImpl.java