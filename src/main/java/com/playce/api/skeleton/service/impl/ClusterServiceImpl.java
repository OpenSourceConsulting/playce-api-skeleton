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

import com.playce.api.skeleton.common.util.WebUtil;
import com.playce.api.skeleton.exception.NoPermissionException;
import com.playce.api.skeleton.exception.PlayceException;
import com.playce.api.skeleton.model.Cluster;
import com.playce.api.skeleton.model.Domain;

import com.playce.api.skeleton.repository.ClusterRepository;
import com.playce.api.skeleton.service.ClusterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
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
public class ClusterServiceImpl implements ClusterService {

    private static final Logger logger = LoggerFactory.getLogger(ClusterServiceImpl.class);

    @Autowired
    private ClusterRepository clusterRepository;

    @Override
    public List<Cluster> getClusterList() {
        return clusterRepository.findAll();
    }

    @Override
    public List<Cluster> getClusterListWithPermission() {
        List<Cluster> clusterList = getClusterList();

        for (Cluster cluster : clusterList) {
            for (Iterator<Domain> it = cluster.getDomain().iterator(); it.hasNext(); ) {
                Domain domain = it.next();

                if (!WebUtil.hasReadPermission(domain.getId())) {
                    it.remove();
                }
            }
        }

        return clusterList;
    }

    @Override
    public Cluster getCluster(long clusterId) {
        return clusterRepository.findById(clusterId).orElse(null);
    }

    @Override
    public Cluster getClusterWithPermission(long clusterId) {
        Cluster cluster = getCluster(clusterId);

        if (cluster != null) {
            for (Iterator<Domain> it = cluster.getDomain().iterator(); it.hasNext(); ) {
                Domain domain = it.next();

                if (!WebUtil.hasReadPermission(domain.getId())) {
                    it.remove();
                }
            }
        }

        return cluster;
    }

    @Override
    public Cluster createCluster(Cluster cluster) {
        return clusterRepository.save(cluster);
    }

    @Override
    public Cluster modifyCluster(Cluster originCluster, Cluster newCluster) {
        // update cluster
        originCluster.setName(newCluster.getName());
        originCluster.setDescription(newCluster.getDescription());
        originCluster.setUpdateUser(WebUtil.getId());
        originCluster.setUpdateDate(new Date());

        return originCluster;
    }

    @Override
    public void removeCluster(Cluster cluster) {
        // delete cluster (delete_yn을 'y'로 변경)
        cluster.setDeleteYn("Y");
        cluster.setUpdateUser(WebUtil.getId());
        cluster.setUpdateDate(new Date());
    }
}
//end of ClusterServiceImpl.java