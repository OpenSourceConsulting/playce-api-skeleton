package com.playce.api.skeleton.controller;

import com.playce.api.skeleton.common.helper.HistoryResultHelper;
import com.playce.api.skeleton.common.util.WebUtil;
import com.playce.api.skeleton.dto.PlayceMessage;
import com.playce.api.skeleton.dto.Status;
import com.playce.api.skeleton.exception.NoPermissionException;
import com.playce.api.skeleton.exception.PlayceException;
import com.playce.api.skeleton.model.Cluster;
import com.playce.api.skeleton.model.History;
import com.playce.api.skeleton.service.ClusterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;

import static com.playce.api.skeleton.common.constant.PlayceConstants.HISTORY_STATUS_FAILED;
import static com.playce.api.skeleton.common.constant.PlayceConstants.HISTORY_STATUS_RUNNING;

/**
 * <pre>
 *
 * </pre>
 *
 * @author Jaeeon Bae
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/resources")
@Api(tags = {"Resources"}, description = "REST APIs for Resources Menu")
public class ClusterController {

    private static final Logger logger = LoggerFactory.getLogger(ClusterController.class);

    @Autowired
    private ClusterService clusterService;

    @Autowired
    private HistoryResultHelper historyResultHelper;

    /**
     * <pre>
     * 클러스터 목록을 조회한다.
     * </pre>
     * @param message
     * @param pageable
     * @return
     */
    @ApiOperation(value = "클러스터 목록 조회", notes = "클러스터 목록을 조회한다.")
    @RequestMapping(value = "/cluster", method = RequestMethod.GET)
    public PlayceMessage getClusterList(@ApiIgnore PlayceMessage message, @ApiIgnore @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {

        try {
            // get cluster list
            List<Cluster> clusterList = clusterService.getClusterListWithPermission();

            message.setStatus(Status.success);
            message.setData(clusterList);
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while fetch cluster list.", e);
            message.setStatus(Status.fail);
            message.setMessage("Can NOT fetch datasource list. [Reason] : " + e.getMessage());
        }

        return message;
    }

    /**
     * <pre>
     * 클러스터 상세정보를 조회한다.
     * </pre>
     * @param message
     * @param id
     * @return
     */
    @ApiOperation(value = "클러스터 상세정보 조회", notes = "클러스터 상세정보를 조회한다.")
    @RequestMapping(value = "/cluster/{id}", method = RequestMethod.GET)
    public PlayceMessage getClusterDetail(@ApiIgnore PlayceMessage message, @PathVariable long id) {

        try {
            // get cluster
            Cluster cluster = clusterService.getClusterWithPermission(id);

            if (cluster == null) {
                // cluster가 없으면 실패코드를 리턴한다.
                message.setCode(404);
                throw new PlayceException("Cluster does not exists.");
            }

            message.setStatus(Status.success);
            message.setData(cluster);
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while fetch cluster.", e);
            message.setStatus(Status.fail);
            message.setMessage("Can NOT fetch cluster. [Reason] : " + e.getMessage());
        }

        return message;
    }

    /**
     * <pre>
     * 신규 클러스터를 생성한다.
     * </pre>
     * @param message
     * @param cluster
     * @return
     */
    @ApiOperation(value = "신규 클러스터 생성", notes = "신규 클러스터를 생성한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "Name", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "Description", required = false, dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/cluster", method = RequestMethod.POST)
    public PlayceMessage createCluster(@ApiIgnore PlayceMessage message, @ApiIgnore Cluster cluster) {
        try {
            // admin 권한 체크
            if (!WebUtil.hasRole(1L)) {
                message.setCode(403);
                throw new NoPermissionException("You don’t have permission to create cluster.");
            }

            cluster.setCreateUser(WebUtil.getId());
            cluster.setCreateDate(new Date());
            cluster.setUpdateUser(WebUtil.getId());
            cluster.setUpdateDate(new Date());

            // create cluster
            History history = clusterService.createCluster(cluster);

            if (history.getStatusCode().equals(HISTORY_STATUS_RUNNING)) {
                history = historyResultHelper.getHistoryResult(history.getId());
            }

            if (history.getStatusCode().equals(HISTORY_STATUS_FAILED)) {
                throw new PlayceException(history.getMessage());
            } else {
                message.setStatus(Status.success);
                message.setData(history);
            }
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while create cluster.", e);
            message.setStatus(Status.fail);

            if (e instanceof NoPermissionException) {
                message.setCode(403);
            }

            message.setMessage("Can NOT create a cluster. [Reason] : " + e.getMessage());
        }

        return message;
    }

    /**
     * <pre>
     * 클러스터를 수정한다.
     * </pre>
     * @param message
     * @param id
     * @param newCluster
     * @return
     */
    @ApiOperation(value = "클러스터 수정", notes = "클러스터를 수정한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "Name", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "Description", required = false, dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/cluster/{id}", method = RequestMethod.PUT)
    public PlayceMessage editCluster(@ApiIgnore PlayceMessage message, @PathVariable Long id, @ApiIgnore Cluster newCluster) {
        try {
            // admin 권한 체크
            if (!WebUtil.hasRole(1L)) {
                message.setCode(403);
                throw new NoPermissionException("You don’t have permission to cluster.");
            }

            // Cluster ID Validation
            Cluster originCluster = clusterService.getCluster(id);

            if (originCluster == null) {
                message.setCode(404);
                throw new PlayceException("Cluster does not exists.");
            }

            // update cluster
            History history = clusterService.modifyCluster(originCluster, newCluster);

            if (history.getStatusCode().equals(HISTORY_STATUS_RUNNING)) {
                history = historyResultHelper.getHistoryResult(history.getId());
            }

            if (history.getStatusCode().equals(HISTORY_STATUS_FAILED)) {
                throw new PlayceException(history.getMessage());
            } else {
                message.setStatus(Status.success);
                message.setData(history);
            }
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while update cluster.", e);
            message.setStatus(Status.fail);

            if (e instanceof NoPermissionException) {
                message.setCode(403);
            }

            message.setMessage("Can NOT update a cluster. [Reason] : " + e.getMessage());
        }

        return message;
    }

    /**
     * <pre>
     * 클러스터를 삭제한다.
     * </pre>
     * @param message
     * @param id
     * @return
     */
    @ApiOperation(value = "클러스터 삭제", notes = "클러스터를 삭제한다.")
    @RequestMapping(value = "/cluster/{id}", method = RequestMethod.DELETE)
    public PlayceMessage removeCluster(@ApiIgnore PlayceMessage message, @PathVariable Long id) {
        try {
            // admin 권한 체크
            if (!WebUtil.hasRole(1L)) {
                message.setCode(403);
                throw new NoPermissionException("You don’t have permission to delete cluster.");
            }

            // Cluster ID Validation
            Cluster cluster = clusterService.getCluster(id);

            if (cluster == null) {
                message.setCode(404);
                throw new PlayceException("Cluster does not exists.");
            }

            if (cluster.getId() == 1) {
                throw new PlayceException("Can NOT delete Default cluster.");
            }

            if (cluster.getDomain().size() > 0) {
                throw new PlayceException("This Cluster(" + cluster.getName() + ") still has domain(s).");
            }

//            if (cluster.getSessionServers().size() > 0) {
//                throw new PlayceException("This Cluser(" + cluster.getName() + ") still has session server(s).");
//            }

            // delete cluster
            History history = clusterService.removeCluster(cluster);

            if (history.getStatusCode().equals(HISTORY_STATUS_RUNNING)) {
                history = historyResultHelper.getHistoryResult(history.getId());
            }

            if (history.getStatusCode().equals(HISTORY_STATUS_FAILED)) {
                throw new PlayceException(history.getMessage());
            } else {
                message.setStatus(Status.success);
                message.setData(history);
            }
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while delete cluster.", e);
            message.setStatus(Status.fail);

            if (e instanceof NoPermissionException) {
                message.setCode(403);
            }

            message.setMessage("Can NOT delete a cluster. [Reason] : " + e.getMessage());
        }

        return message;
    }
}
//end of ClusterController.java