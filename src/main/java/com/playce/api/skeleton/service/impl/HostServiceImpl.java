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
import com.playce.api.skeleton.model.HostDetail;
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
import java.util.List;

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
public class HostServiceImpl implements HostService {

    private static final Logger logger = LoggerFactory.getLogger(HostServiceImpl.class);

    @Autowired
    private HostRepository hostRepository;

    @Override
    public List<Host> getHostList() {
        return hostRepository.findAll();
    }

    @Override
    public Host getHost(Long hostId) {
        return hostRepository.findById(hostId).orElse(null);
    }

    @Override
    public Host createHost(Host host, MultipartFile keyFile) throws PlayceException {
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

        HostDetail hostDetail = new HostDetail();
        host.setDetail(hostDetail);
        hostDetail.setHost(host);

        // save Host
        host = hostRepository.save(host);

        return host;
    }
}
//end of HostServiceImpl.java