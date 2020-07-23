package com.playce.api.skeleton.controller;

import com.playce.api.skeleton.common.helper.HistoryResultHelper;
import com.playce.api.skeleton.common.util.WebUtil;
import com.playce.api.skeleton.dto.PlayceMessage;
import com.playce.api.skeleton.dto.Status;
import com.playce.api.skeleton.exception.NoPermissionException;
import com.playce.api.skeleton.exception.PlayceException;
import com.playce.api.skeleton.model.Cluster;
import com.playce.api.skeleton.model.Domain;
import com.playce.api.skeleton.model.History;
import com.playce.api.skeleton.service.DomainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
@RequestMapping(value = "/api/domain")
@Api(tags = {"Domain"}, description = "Rest APIs for Domain Menu")
public class DomainController {

    private static final Logger logger = LoggerFactory.getLogger(DomainController.class);

    @Autowired
    private DomainService domainService;

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    private HistoryResultHelper historyResultHelper;

    /**
     * <pre>
     * 등록된 도메인 목록을 조회한다.
     * </pre>
     * @param domain
     * @param pageable
     * @return
     */
    @ApiOperation(value = "도메인 목록 조회", notes = "도메인 목록을 조회한다.")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public PlayceMessage getDomainList(@ApiIgnore Domain domain, @ApiIgnore @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        PlayceMessage message = new PlayceMessage();

        try {
            // get domain list
            List<Domain> domainList = domainService.getDomainList();

            for (Domain d : domainList) {
                d.setClusterName(d.getCluster().getName());
            }

            message.setStatus(Status.success);
            message.setData(domainList);
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while fetch domain list.", e);
            message.setStatus(Status.fail);
            message.setMessage("Can NOT fetch domain list. [Reason] : " + e.getMessage());
        }

        return message;
    }

    /**
     * <pre>
     * 도메인 상세 정보를 조회한다.
     * </pre>
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "도메인 상세정보 조회", notes = "도메인 상세정보를 조회한다.")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PlayceMessage getDomainDetail(@PathVariable Long id, HttpServletRequest request) {
        PlayceMessage message = new PlayceMessage();

        try {
            // get domain
            Domain domain = domainService.getDomain(id, false);

            if (domain == null) {
                message.setCode(404);
                throw new PlayceException("Domain does not exists.");
            }

            domain.setClusterName(domain.getCluster().getName());

            message.setStatus(Status.success);
            message.setData(domain);
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while fetch domain.", e);
            message.setStatus(Status.fail);

            if (e instanceof NoPermissionException) {
                message.setCode(403);
            }

            message.setMessage("Can NOT fetch domain. [Reason] : " + e.getMessage());
        }

        return message;
    }

    /**
     * <pre>
     * 신규 도메인을 등록한다.
     * </pre>
     * @param domain
     * @param request
     * @return
     */
    @ApiOperation(value = "신규 도메인 생성", notes = "신규 도메인을 생성한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "Domain Name", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "Domain Description", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "clusterId", value = "Cluster ID", required = true, dataType = "long", paramType = "query")
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public PlayceMessage createDomain(@ApiIgnore Domain domain, @RequestParam Long clusterId, HttpServletRequest request) {
        PlayceMessage message = new PlayceMessage();
        Locale locale = localeResolver.resolveLocale(request);

        try {
            // admin 권한 체크
            if (!WebUtil.hasRole(1L)) {
                message.setCode(403);
                throw new NoPermissionException("You don’t have permission to create domain.");
            }

            // check duplicate domain name
            List<Domain> d = domainService.getDomainByName(domain.getName());

            if (d.size() > 0) {
                message.setCode(409);
                throw new PlayceException("domain name is duplicated.");
            }

            domain.setCluster(new Cluster(clusterId));
            domain.setCreateUser(WebUtil.getId());
            domain.setUpdateUser(WebUtil.getId());

            /// create domain
            History history = domainService.createDomain(domain);

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
            logger.error("Unhandled exception occurred while create doamin.", e);
            message.setStatus(Status.fail);
            message.setMessage("Can NOT create domain. [Reason] : " + e.getMessage());
        }

        return message;
    }

    /**
     * <pre>
     * 도메인 정보를 수정한다.
     * </pre>
     * @param domain
     * @param id
     * @return
     */
    @ApiOperation(value = "도메인 수정", notes = "도메인 정보를 수정한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Domain ID", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "Domain Name", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "Domain Description", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "clusterId", value = "Cluster ID", required = true, dataType = "long", paramType = "query")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public PlayceMessage modifyDomain(@ApiIgnore Domain domain, @PathVariable long id,
                                     @RequestParam Long clusterId) {
        PlayceMessage message = new PlayceMessage();

        domain.setId(id);
        domain.setUpdateUser(WebUtil.getId());
        domain.setUpdateDate(new Date());
        //domain.setCluster(new Cluster(clusterId));

        try {
            // manager 권한 체크
            if (!WebUtil.hasWritePermission(id)) {
                message.setCode(403);
                throw new NoPermissionException("You don’t have permission to domain.");
            }

            // update domain
            History history = domainService.modifyDomain(domain, clusterId);

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
            logger.error("Unhandled exception occurred while update domain.", e);
            message.setStatus(Status.fail);
            message.setMessage("Can NOT update domain. [Reason] : " + e.getMessage());
        }

        return message;
    }

    /**
     * <pre>
     * 도메인 정보를 삭제한다.
     * </pre>
     * @param id
     * @return
     */
    @ApiOperation(value = "도메인 삭제", notes = "도메인 정보를 삭제한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Domain ID", required = true, dataType = "long", paramType = "path")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public PlayceMessage removeDomain(@PathVariable long id) {
        PlayceMessage message = new PlayceMessage();

        try {
            // admin 권한 체크
            if (!WebUtil.hasRole(1L)) {
                message.setCode(403);
                throw new NoPermissionException("You don’t have permission to delete domain.");
            }

            Domain domain = domainService.getDomain(id, true);

            if (domain == null) {
                message.setCode(404);
                throw new PlayceException("Domain does not exists.");
            }

            domain.setUpdateUser(WebUtil.getId());
            domain.setUpdateDate(new Date());
            domain.setDeleteYn("Y");

            // 해당 도메인의 롤의 가진 회원 정보를 삭제한다.
            domainService.updateMemberRolesDomainsByDomainId(domain);

            // 도메인 삭제 - 도메인의 delete_yn을 "Y"로 변경한다.
            History history = domainService.removeDomain(domain);

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
            logger.error("Unhandled exception occurred while delete domain.", e);
            message.setStatus(Status.fail);
            message.setMessage("Can NOT delete domain. [Reason] : " + e.getMessage());
        }

        return message;
    }
}
//end of DomainController.java