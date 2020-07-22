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

import com.playce.api.skeleton.exception.PlayceException;
import com.playce.api.skeleton.model.Member;

import java.util.List;

/**
 * <pre>
 *
 *
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
public interface MemberService {

    public List<Member> getMemberList();

    public Member getMember(Long id);

    public Member getMember(String userId);

    public List<Long> getDomainIdList(Long id);

    public void updateLastLoginDate(String userId);

    public void deleteMember(long id);

    public void createMember(Member member);

    public void modifyMember(Member originMember, Member newMember);

    public Member updateMemberPassword(Member originMember, String newPassword);

    public void updateFailLimitCount(String userId);

    public void updateFailLimitCount(String userId, Long count);
}
//end of MemberService.java