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
package com.playce.api.skeleton.service;

import com.playce.api.skeleton.model.Cluster;


import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
public interface ClusterService {

    public List<Cluster> getClusterList();

    public List<Cluster> getClusterListWithPermission();

    public Cluster getCluster(long clusterId);

    public Cluster getClusterWithPermission(long clusterId);

    public Cluster createCluster(Cluster cluster);

    public Cluster modifyCluster(Cluster originCluster, Cluster newCluster);

    public void removeCluster(Cluster cluster);
}
//end of ClusterService.java