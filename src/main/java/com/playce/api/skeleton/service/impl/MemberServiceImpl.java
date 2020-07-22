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
import com.playce.api.skeleton.model.Member;
import com.playce.api.skeleton.model.MembersRolesDomains;
import com.playce.api.skeleton.repository.DomainRepository;
import com.playce.api.skeleton.repository.MemberRepository;
import com.playce.api.skeleton.repository.MembersRolesDomainsRepository;
import com.playce.api.skeleton.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 *
 *
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
@Service
@Transactional(rollbackFor = {PlayceException.class, NoPermissionException.class})
public class MemberServiceImpl implements MemberService {

    private static Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private MembersRolesDomainsRepository membersRolesDomainsRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMember(Long id) {
        Assert.notNull(id, "id must not be null");
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member getMember(String userId) {
        Assert.notNull(userId, "userId must not be null");
        return memberRepository.findByUserId(userId);
    }

    @Override
    public List<Long> getDomainIdList(Long id) {
        Member member = memberRepository.findById(id).orElse(null);

        List<Long> domainIdList = new ArrayList<Long>();
        domainIdList.add(1L);

        if (member != null) {
            for (MembersRolesDomains mrd : member.getMembersRolesDomains()) {
                if (!mrd.getRoleId().equals(1L)) {
                    domainIdList.add(mrd.getDomain().getId());
                } else {
                    return domainRepository.findAll().stream().map(domain -> domain.getId()).collect(Collectors.toList());
                }

            }
        }

        return domainIdList;
    }

    @Override
    public void updateLastLoginDate(String userId) {
        Member member = memberRepository.findByUserId(userId);
        member.setLastLoginDate(new Date());
    }

    @Override
    public void createMember(Member member) {
        member.setCreateUser(WebUtil.getId());
        member.setUpdateUser(WebUtil.getId());
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setFailLimitCount(0L);
        member.setBlockYn("N");

        // save member
        member = memberRepository.save(member);

        // save members roles domains
        List<MembersRolesDomains> membersRolesDomainList = member.getMembersRolesDomains();

        // default 도메인 Manager에 대한 권한 추가
        MembersRolesDomains defaultMembersRolesDomains = new MembersRolesDomains();
        defaultMembersRolesDomains.setMember(member);
        defaultMembersRolesDomains.setDomainId(1L);
        defaultMembersRolesDomains.setRoleId(2L);
        membersRolesDomainList.add(defaultMembersRolesDomains);

        for (MembersRolesDomains mrd : membersRolesDomainList) {
            mrd.setMember(member);
            membersRolesDomainsRepository.save(mrd);
        }
    }

    @Override
    public void modifyMember(Member originMember, Member newMember) {
        // origin members Roles domains delete
        List<MembersRolesDomains> originMembersRolesDomainsList = originMember.getMembersRolesDomains();
        for (MembersRolesDomains mrd : originMembersRolesDomainsList) {
            membersRolesDomainsRepository.deleteById(mrd.getId());
        }

        // update Member
        originMember.setUserName(newMember.getUserName());
        if (!StringUtils.isEmpty(newMember.getPassword())) {
            // 신규 패스워드가 있을 시 update
            originMember.setPassword(passwordEncoder.encode(newMember.getPassword()));
        }
        originMember.setEmail(newMember.getEmail());
        originMember.setDescription(newMember.getDescription());
        originMember.setUpdateUser(WebUtil.getId());
        originMember.setUpdateDate(new Date());
        originMember.setFailLimitCount(0L);
        originMember.setBlockYn("N");

        // new members Roles domains save
        List<MembersRolesDomains> newMembersRolesDomainList = newMember.getMembersRolesDomains();

        // default domain role add
        MembersRolesDomains defaultMembersRolesDomains = new MembersRolesDomains();
        defaultMembersRolesDomains.setDomainId(1L);
        defaultMembersRolesDomains.setRoleId(2L);
        newMembersRolesDomainList.add(defaultMembersRolesDomains);

        for (MembersRolesDomains mrd : newMembersRolesDomainList) {
            mrd.setMember(originMember);
            membersRolesDomainsRepository.save(mrd);
        }
    }

    @Override
    public void deleteMember(long id) {
        Member member = memberRepository.findById(id).orElse(null);

        List<MembersRolesDomains> membersRolesDomainsList = member.getMembersRolesDomains();
        for (MembersRolesDomains mrd : membersRolesDomainsList) {
            membersRolesDomainsRepository.deleteById(mrd.getId());
        }

        member.setDeleteYn("Y");
        member.setUpdateUser(WebUtil.getId());
        member.setUpdateDate(new Date());
    }

    @Override
    public Member updateMemberPassword(Member originMember, String newPassword) {
        // update password
        if (!StringUtils.isEmpty(newPassword)) {
            originMember.setPassword(newPassword);
        }

        return originMember;
    }

    @Override
    public void updateFailLimitCount(String userId) {
        Member member = memberRepository.findByUserId(userId);

        if (member != null) {
            updateFailLimitCount(userId, member.getFailLimitCount() + 1);
        }
    }

    @Override
    public void updateFailLimitCount(String userId, Long count) {
        Member member = memberRepository.findByUserId(userId);
        if (member != null) {
            member.setFailLimitCount(count);
        }
    }
}
//end of MemberServiceImpl.java