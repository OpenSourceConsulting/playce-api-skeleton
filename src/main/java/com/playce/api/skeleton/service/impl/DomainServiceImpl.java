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
import com.playce.api.skeleton.model.*;
import com.playce.api.skeleton.repository.DomainRepository;
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
public class DomainServiceImpl implements DomainService {

    private static final Logger logger = LoggerFactory.getLogger(DomainServiceImpl.class);

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MembersRolesDomainsRepository membersRolesDomainsRepository;

    @Override
    public List<Domain> getDomainList() {
        if (WebUtil.hasRole(1L)) {
            return domainRepository.findAllByOrderByNameAsc();
        } else {
            return domainRepository.findByIdInOrderByNameAsc(WebUtil.getDomaindIdList());
        }
    }

    @Override
    public Domain getDomain(Long id, boolean writable) throws NoPermissionException {
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
    public Domain createDomain(Domain domain) {
        // create Domain
        domain = domainRepository.save(domain);

        return domain;
    }

    @Override
    public List<Domain> getDomainByName(String name) {
        return domainRepository.findByName(name);
    }

    @Override
    public Domain modifyDomain(Domain domain, Long afterClusterId) throws PlayceException {
        Domain originDomain = domainRepository.findById(domain.getId()).orElse(null);
        if (originDomain == null) {
            throw new PlayceException("Domain does not exists.");
        }

        if (originDomain.getId() == 1) {
            // Can not update default domain
            throw new PlayceException("Can NOT update Default domain.");
        }

        // update domain
        originDomain.setName(domain.getName());
        originDomain.setDescription(domain.getDescription());
        originDomain.setCluster(new Cluster(afterClusterId));
        originDomain.setUpdateUser(domain.getUpdateUser());
        originDomain.setUpdateDate(new Date());

        return originDomain;
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
    public void removeDomain(Domain domain) throws PlayceException {
        Domain d = domainRepository.findById(domain.getId()).orElse(null);
        if (d == null) {
            throw new PlayceException("Domain does not exists.");
        }

        if (d.getId() == 1) {
            // Can NOT delete default domain
            throw new PlayceException("Can NOT delete Default domain.");
        }

        // delelte domain
        d.setDeleteYn(domain.getDeleteYn());
        d.setUpdateUser(domain.getUpdateUser());
        d.setUpdateDate(new Date());
    }
}
//end of DomainServiceImpl.java