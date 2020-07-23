package com.playce.api.skeleton.service.impl;

import com.playce.api.skeleton.common.util.WebUtil;
import com.playce.api.skeleton.exception.NoPermissionException;
import com.playce.api.skeleton.exception.PlayceException;
import com.playce.api.skeleton.model.*;
import com.playce.api.skeleton.repository.DomainRepository;
import com.playce.api.skeleton.repository.HistoryRepository;
import com.playce.api.skeleton.repository.MemberRepository;
import com.playce.api.skeleton.repository.MembersRolesDomainsRepository;
import com.playce.api.skeleton.service.DomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
public class DomainServiceImpl implements DomainService {

    private static final Logger logger = LoggerFactory.getLogger(DomainServiceImpl.class);

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MembersRolesDomainsRepository membersRolesDomainsRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public List<Domain> getDomainList() {
        if (WebUtil.hasRole(1L)) {
            return domainRepository.findAllByOrderByNameAsc();
        } else {
            return domainRepository.findByIdInOrderByNameAsc(WebUtil.getDomaindIdList());
        }
    }

    @Override
    public Domain getDomain(Long id, boolean writable) throws PlayceException {
        Domain domain = domainRepository.findById(id).orElse(null);

        if (domain != null) {
            if (writable) {
                if (!WebUtil.hasWritePermission(id)) {
                    throw new NoPermissionException("You don’t have permission to domain.");
                }
            } else {
                if (!WebUtil.hasReadPermission(id)) {
                    throw new NoPermissionException("You don’t have permission to domain.");
                }
            }
        }

        return domain;
    }

    @Override
    public History createDomain(Domain domain) {
        String uuid = UUID.randomUUID().toString();
        History history = new History();

        try {
            // create Domain
            domain = domainRepository.save(domain);

            // save History
            history.setProcessUUID(uuid);
            history.setCode(HISTORY_CODE_DOMAIN_CREATE);
            history.setTitle("Domain(" + domain.getName() + ") has been created.");
            history.setDomainId(domain.getId());
            history.setTaskUser(WebUtil.getId());
            history.setStatusCode(HISTORY_STATUS_SUCCESS);
            history.setCreateDate(new Date());
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while create domain.", e);
            history.setStatusCode(HISTORY_STATUS_FAILED);
            history.setMessage(e.getMessage());
        } finally {
            history.setReadYn("Y");
            history.setEndDate(new Date());
            history = historyRepository.save(history);
        }

        return history;
    }

    @Override
    public List<Domain> getDomainByName(String name) {
        return domainRepository.findByName(name);
    }

    @Override
    public History modifyDomain(Domain domain, Long afterClusterId) throws PlayceException {
        String uuid = UUID.randomUUID().toString();

        Domain originDomain = domainRepository.findById(domain.getId()).orElse(null);
        if (originDomain == null) {
            throw new PlayceException("Domain does not exists.");
        }

        if (originDomain.getId() == 1) {
            // Can not update default domain
            throw new PlayceException("Can NOT update Default domain.");
        }

        History history = new History();
        history.setProcessUUID(uuid);
        history.setCode(HISTORY_CODE_DOMAIN_UPDATE);
        history.setTitle("Domain(" + originDomain.getName() + ") has been updated.");
        history.setDomainId(originDomain.getId());
        history.setTaskUser(WebUtil.getId());

        try {
            // Cluster의 변경이 이루어질 경우 Domain에 속한 Web App Server의 dolly.properties 변경
//            Long beforeClusterId = originDomain.getCluster().getId();
//            if (beforeClusterId.longValue() != afterClusterId.longValue()) {
//                for (WebAppServer webAppServer : originDomain.getWebAppServer()) {
//                    String beforeClusterServerList = clusterService.getHotrodServerList(beforeClusterId);
//                    String afterClusterServerList = clusterService.getHotrodServerList(afterClusterId);
//
//                    if (StringUtils.compare(beforeClusterServerList, afterClusterServerList) != 0) {
//                         SessionClusteringTask 실행
//                        threadPoolExecutor.execute(new SessionClusteringTask(WebUtil.getId(), webAppServer, null, afterClusterServerList, uuid));
//                    }
//                }
//            }

            // update domain
            originDomain.setName(domain.getName());
            originDomain.setDescription(domain.getDescription());
            originDomain.setCluster(new Cluster(afterClusterId));
            originDomain.setUpdateUser(domain.getUpdateUser());
            originDomain.setUpdateDate(new Date());

            // save History
            history.setStatusCode(HISTORY_STATUS_SUCCESS);
            history.setCreateDate(new Date());
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while update domain.", e);
            history.setStatusCode(HISTORY_STATUS_FAILED);
            history.setMessage(e.getMessage());
        } finally {
            history.setReadYn("Y");
            history.setEndDate(new Date());
            history = historyRepository.save(history);
        }

        return history;
    }

    @Override
    public void updateMemberRolesDomainsByDomainId(Domain domain) {
        Domain d = domainRepository.findById(domain.getId()).orElse(null);

        /*
        List<MembersRolesDomains> membersRolesDomainsList = d.getMembersRolesDomains();
        for (MembersRolesDomains mrd : membersRolesDomainsList) {
            membersRolesDomainsRepository.deleteById(mrd.getId());
        }
        /*/
        if (d.getMembersRolesDomains() != null && d.getMembersRolesDomains().size() > 0) {
            for (MembersRolesDomains membersRolesDomains : d.getMembersRolesDomains()) {
                Long memberId = membersRolesDomains.getMemberId();

                Member member = memberRepository.findById(memberId).orElse(null);

                if (member.getMembersRolesDomains() != null && !(member.getMembersRolesDomains().size() > 1)) {

                    // 도메인 & 회원 매핑 테이블의 개수가 1개일 경우, default domain 권한 add 후 삭제
                    MembersRolesDomains newMemberRoles = new MembersRolesDomains();
                    newMemberRoles.setDomainId(1L);
                    newMemberRoles.setMemberId(memberId);
                    newMemberRoles.setRoleId(2L);
                    membersRolesDomainsRepository.save(newMemberRoles);

                    membersRolesDomainsRepository.deleteById(membersRolesDomains.getId());
                } else {
                    // 권한이 여러개일 경우 해당 권한만 삭제
                    membersRolesDomainsRepository.deleteById(membersRolesDomains.getId());
                }
            }
        }
        //*/
    }

    @Override
    public History removeDomain(Domain domain) throws PlayceException {
        String uuid = UUID.randomUUID().toString();

        Domain d = domainRepository.findById(domain.getId()).orElse(null);
        if (d == null) {
            throw new PlayceException("Domain does not exists.");
        }

        if (d.getId() == 1) {
            // Can NOT delete default domain
            throw new PlayceException("Can NOT delete Default domain.");
        }

        // get application servers & web servers
//        List<WebAppServer> webAppServers = appServerRepository.findByDomainId(d.getId());
//        List<WebServer> webServers = webServerRepository.findByDomainId(d.getId());
//
//        if (webAppServers.size() > 0) {
//            throw new WasupException("This domain still has web application servers.");
//        }
//
//        if (webServers.size() > 0) {
//            throw new WasupException("This domain still has web servers.");
//        }

        History history = new History();

        try {
            // save History
            history.setProcessUUID(uuid);
            history.setCode(HISTORY_CODE_DOMAIN_DELETE);
            history.setTitle("Domain(" + d.getName() + ") has been deleted.");
            history.setDomainId(d.getId());
            history.setTaskUser(WebUtil.getId());
            history.setStatusCode(HISTORY_STATUS_SUCCESS);
            history.setCreateDate(new Date());

            // delelte domain
            d.setDeleteYn(domain.getDeleteYn());
            d.setUpdateUser(domain.getUpdateUser());
            d.setUpdateDate(new Date());
        } catch (Exception e) {
            logger.error("Unhandled exception occurred while delete domain.", e);
            history.setStatusCode(HISTORY_STATUS_FAILED);
            history.setMessage(e.getMessage());
        } finally {
            history.setReadYn("Y");
            history.setEndDate(new Date());
            history = historyRepository.save(history);
        }

        return history;
    }
}
//end of DomainServiceImpl.java