package com.playce.api.skeleton.service.impl;

import com.playce.api.skeleton.common.util.WebUtil;
import com.playce.api.skeleton.exception.NoPermissionException;
import com.playce.api.skeleton.exception.PlayceException;
import com.playce.api.skeleton.model.Cluster;
import com.playce.api.skeleton.model.Domain;
import com.playce.api.skeleton.model.History;
import com.playce.api.skeleton.repository.ClusterRepository;
import com.playce.api.skeleton.repository.HistoryRepository;
import com.playce.api.skeleton.service.ClusterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ClusterServiceImpl implements ClusterService {

    private static final Logger logger = LoggerFactory.getLogger(ClusterServiceImpl.class);

    @Autowired
    private ClusterRepository clusterRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public List<Cluster> getClusterList() throws PlayceException {
        return clusterRepository.findAll();
    }

    @Override
    public List<Cluster> getClusterListWithPermission() throws PlayceException {
        List<Cluster> clusterList = getClusterList();

        for (Cluster cluster : clusterList) {
//            for (Iterator<SessionServer> it = cluster.getSessionServers().iterator(); it.hasNext(); ) {
//                SessionServer ss = it.next();
//
//                boolean hasPermission = false;
//                if (ss.getCluster().getDomain().size() > 0) {
//                    for (Domain domain : ss.getCluster().getDomain()) {
//                        hasPermission = false;
//
//                        if (WebUtil.hasReadPermission(domain.getId())) {
//                            hasPermission = true;
//                            break;
//                        }
//                    }
//
//                    if (!hasPermission) {
//                        it.remove();
//                    }
//                }
//            }

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
    public Cluster getCluster(long clusterId) throws PlayceException {
        return clusterRepository.findById(clusterId).orElse(null);
    }

    @Override
    public Cluster getClusterWithPermission(long clusterId) throws PlayceException {
        Cluster cluster = getCluster(clusterId);

        if (cluster != null) {
            /*for (Iterator<SessionServer> it = cluster.getSessionServers().iterator(); it.hasNext(); ) {
                SessionServer ss = it.next();

                boolean hasPermission = false;

                if (ss.getCluster().getDomain().size() > 0) {
                    for (Domain domain : ss.getCluster().getDomain()) {
                        hasPermission = false;

                        if (WebUtil.hasReadPermission(domain.getId())) {
                            hasPermission = true;
                            break;
                        }
                    }

                    if (!hasPermission) {
                        it.remove();
                    }
                }
            }*/

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
    public History createCluster(Cluster cluster) throws PlayceException {
        String uuid = UUID.randomUUID().toString();
        History history = new History();

        try {
            // create cluster
            cluster = clusterRepository.save(cluster);

            // save History
            history.setProcessUUID(uuid);
            history.setCode(HISTORY_CODE_CLUSTER_CREATE);
            history.setTitle("Create a cluster(" + cluster.getName() + ")");
            history.setClusterId(cluster.getId());
            history.setTaskUser(WebUtil.getId());
            history.setCreateDate(new Date());
            history.setStatusCode(HISTORY_STATUS_SUCCESS);
        } catch (Exception e) {
            history.setStatusCode(HISTORY_STATUS_FAILED);
            history.setMessage(e.getMessage());
        } finally {
            history = historyRepository.save(history);
        }

        return history;
    }

    @Override
    public History modifyCluster(Cluster originCluster, Cluster newCluster) throws PlayceException {
        String uuid = UUID.randomUUID().toString();
        History history = new History();

        try {
            // update cluster
            originCluster.setName(newCluster.getName());
            originCluster.setDescription(newCluster.getDescription());
            originCluster.setUpdateUser(WebUtil.getId());
            originCluster.setUpdateDate(new Date());

            // save History
            history.setProcessUUID(uuid);
            history.setCode(HISTORY_CODE_CLUSTER_UPDATE);
            history.setTitle("Update a cluster(" + originCluster.getName() + ")");
            history.setClusterId(originCluster.getId());
            history.setTaskUser(WebUtil.getId());
            history.setCreateDate(new Date());
            history.setStatusCode(HISTORY_STATUS_SUCCESS);

        } catch (Exception e) {
            history.setStatusCode(HISTORY_STATUS_FAILED);
            history.setMessage(e.getMessage());
        } finally {
            history = historyRepository.save(history);
        }

        return history;
    }

    @Override
    public History removeCluster(Cluster cluster) throws PlayceException {
        String uuid = UUID.randomUUID().toString();
        History history = new History();

        try {
            // delete cluster (delete_yn을 'y'로 변경)
            cluster.setDeleteYn("Y");
            cluster.setUpdateUser(WebUtil.getId());
            cluster.setUpdateDate(new Date());

            // save History
            history.setProcessUUID(uuid);
            history.setCode(HISTORY_CODE_CLUSTER_DELETE);
            history.setTitle("Delete a cluster(" + cluster.getName() + ")");
            history.setClusterId(cluster.getId());
            history.setTaskUser(WebUtil.getId());
            history.setCreateDate(new Date());
            history.setStatusCode(HISTORY_STATUS_SUCCESS);

        } catch (Exception e) {
            history.setStatusCode(HISTORY_STATUS_FAILED);
            history.setMessage(e.getMessage());
        } finally {
            history = historyRepository.save(history);
        }

        return history;
    }
}
//end of ClusterServiceImpl.java