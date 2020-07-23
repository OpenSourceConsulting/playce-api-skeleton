package com.playce.api.skeleton.service.impl;

import com.playce.api.skeleton.common.util.WebUtil;
import com.playce.api.skeleton.exception.NoPermissionException;
import com.playce.api.skeleton.exception.PlayceException;
import com.playce.api.skeleton.model.History;
import com.playce.api.skeleton.model.Host;
import com.playce.api.skeleton.model.HostDetail;
import com.playce.api.skeleton.repository.HistoryRepository;
import com.playce.api.skeleton.repository.HostRepository;
import com.playce.api.skeleton.service.HostService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static com.playce.api.skeleton.common.constant.PlayceConstants.*;

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

    @Override
    public History createHost(Host host, MultipartFile keyFile) throws PlayceException {
        String uuid = UUID.randomUUID().toString();
        History history = new History();

        try {
            history.setProcessUUID(uuid);
            history.setCode(HISTORY_CODE_HOST_CREATE);
            history.setTitle("Host(" + host.getName() + ") has been created.");
            history.setTaskUser(WebUtil.getId());
            history.setCreateDate(new Date());

//            if (host.getScouterServer() != null) {
//                history.setScouterServerId(host.getScouterServer().getId());
//            }

            // IP 중복 체크
//            if (isDuplicate(host.getIpAddress())) {
//                throw new WasupException("IP(" + host.getIpAddress() + ") already exist.");
//            }

            if (keyFile != null && keyFile.getSize() > 0) {
                try {
                    // Do NOT save the key file under the repositoryPath for security reason.
                    File tempFile = File.createTempFile(host.getIpAddress() + "-", ".pem");
                    String keyStr = IOUtils.toString(keyFile.getInputStream(), "UTF-8");

                    host.setKeyFilePath(tempFile.getAbsolutePath());
                    host.setKeyString(keyStr);

                    IOUtils.write(keyStr, new FileOutputStream(tempFile), "UTF-8");
                } catch (IOException e) {
                    throw new PlayceException(e);
                }
            }

            // Connection Test
            /*if (!SSHUtil.healthCheck(TargetHost.convert(host))) {
                throw new WasupException("Can NOT connect to [" + host.getUsername() + "@" + host.getIpAddress() + "] over SSH.");
            }

            // sudo 권한 체크
            //if (!host.getUsername().equals("root") && !isSudoer(host)) {
            if ("Y".equals(host.getSudoerYn()) && !isSudoer(host)) {
                throw new WasupException(host.getUsername() + " is not a member of sudoers or NOPASSWD option was not set.");
            }

            // java 체크
            javaVersionCheck(host);

            // Home Directory
            String homeDir = SSHUtil.executeCommand(TargetHost.convert(host), "eval echo ~$USER").trim();

            if (StringUtils.isEmpty(host.getAgentInstallPath())) {
                host.setAgentInstallPath(homeDir + "/wasup-agent");
            } else {
                if (!host.getAgentInstallPath().equals(homeDir + "/wasup-agent") && !host.getAgentInstallPath().equals(homeDir + "/agent")) {
                    String result = SSHUtil.executeCommand(TargetHost.convert(host), "if test -d " + host.getAgentInstallPath() + "; then echo \"Exists\"; else echo \"Not found\"; fi ").trim();

                    if (result.equals("Exists")) {
                        throw new WasupException(host.getAgentInstallPath() + " already exists.");
                    }

                    // create an empty directory
                    if (host.getAgentInstallPath().startsWith(homeDir) || !"Y".equals(host.getSudoerYn())) {
                        result = SSHUtil.executeCommand(TargetHost.convert(host), "mkdir -p " + host.getAgentInstallPath() + " 2>&1");

                        if (result.contains("Permission denied")) {
                            throw new WasupException(result);
                        }
                    }
                }
            }*/

            HostDetail hostDetail = new HostDetail();
            host.setDetail(hostDetail);
            hostDetail.setHost(host);

            // save Host
            host = hostRepository.save(host);

            // save History
            history.setHostId(host.getId());
            history.setStatusCode(HISTORY_STATUS_SUCCESS);
            history = historyRepository.save(history);
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while create host.", e);
            history.setStatusCode(HISTORY_STATUS_FAILED);
            history.setTitle("Host(" + host.getName() + ") create failed.");
            history.setMessage(e.getMessage());

            //throw WasupException(e);
        } finally {
            history.setReadYn("Y");
            history.setEndDate(new Date());

            if (history.getId() == null) {
                history = historyRepository.save(history);
            }
        }

        return history;
    }
}
//end of HostServiceImpl.java