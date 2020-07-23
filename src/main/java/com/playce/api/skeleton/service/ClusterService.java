package com.playce.api.skeleton.service;

import com.playce.api.skeleton.exception.PlayceException;
import com.playce.api.skeleton.model.Cluster;
import com.playce.api.skeleton.model.History;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author Jaeeon Bae
 * @version 1.0
 */
public interface ClusterService {

    List<Cluster> getClusterList() throws PlayceException;

    List<Cluster> getClusterListWithPermission() throws PlayceException;

    Cluster getCluster(long clusterId) throws PlayceException;

    Cluster getClusterWithPermission(long clusterId) throws PlayceException;

    History createCluster(Cluster cluster) throws PlayceException;

    History modifyCluster(Cluster originCluster, Cluster newCluster) throws PlayceException;

    History removeCluster(Cluster cluster) throws PlayceException;
}
//end of ClusterService.java