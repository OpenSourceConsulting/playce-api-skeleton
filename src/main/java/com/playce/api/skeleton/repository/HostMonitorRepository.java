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

import com.playce.api.skeleton.model.HostMonitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
public interface HostMonitorRepository extends JpaRepository<HostMonitor, Long> {

    public List<HostMonitor> findByMonitorDateGreaterThanEqualAndMonitorDateLessThanEqualAndTypeOrderByHostIdAscMonitorDateAsc(Date beforeDate, Date currentDate, String type);

    public List<HostMonitor> findByMonitorDateGreaterThanEqualAndMonitorDateLessThanEqualAndTypeAndHostIdInOrderByMonitorDateAsc(Date beforeDate, Date currentDate, String type, List<Long> hostIdList);

    @Modifying
    @Transactional
    @Query("DELETE FROM HostMonitor h where h.monitorDate < :date")
    public void deleteFromDay(Date date);
}
//end of HostMonitorRepository.java
