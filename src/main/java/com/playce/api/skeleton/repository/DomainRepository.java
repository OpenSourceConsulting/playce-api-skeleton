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
package com.playce.api.skeleton.repository;

import com.playce.api.skeleton.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <pre>
 * Spring Data JPA Query Creation
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
@Repository
public interface DomainRepository extends JpaRepository<Domain, Long> {

    List<Domain> findByName(String name);

    List<Domain> findByClusterId(Long id);

    List<Domain> findByIdIn(List<Long> idList);

    List<Domain> findAllByOrderByIdDesc();

    List<Domain> findByIdInOrderByIdDesc(List<Long> domaindIdList);

    List<Domain> findAllByOrderByNameAsc();

    List<Domain> findByIdInOrderByNameAsc(List<Long> domaindIdList);
}
//end of DomainRepository.java