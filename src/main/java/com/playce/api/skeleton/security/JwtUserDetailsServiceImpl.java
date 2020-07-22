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
package com.playce.api.skeleton.security;

import com.playce.api.skeleton.exception.PasswordIncorrectLimitException;
import com.playce.api.skeleton.model.Member;
import com.playce.api.skeleton.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final int PASSWORD_LIMIT_COUNT = 7;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException, PasswordIncorrectLimitException {
        Member member = memberRepository.findByUserId(userId);

        if (member == null) {
            throw new UsernameNotFoundException(String.format("No user found with userId '%s'.", userId));
        } else if (member.getFailLimitCount() == PASSWORD_LIMIT_COUNT) {
            member.setBlockYn("Y");
            memberRepository.save(member);

            throw new PasswordIncorrectLimitException(String.format("Password incorrect limit count exceed with userId '%s'.", userId));
        } else {
            return JwtUserFactory.create(member);
        }
    }
}