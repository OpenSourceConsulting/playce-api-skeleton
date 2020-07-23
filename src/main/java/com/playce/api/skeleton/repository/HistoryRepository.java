/*
 * Copyright 2019 The Playce-WASUP Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Revision History
 * Author            Date                Description
 * ---------------  ----------------    ------------
 * Jaeeon Bae       Feb 20, 2019            First Draft.
 */
package com.playce.api.skeleton.repository;

import com.playce.api.skeleton.model.History;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author Jaeeon Bae
 * @version 1.0
 */
@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findByWizardId(long wizardId);

    History findByProcessUUIDAndWebAppServerIdAndDatasourceId(String uuid, Long wasId, Long datasourceId);

    History findByProcessUUIDAndWebAppServerIdAndApplicationId(String uuid, Long wasId, Long applicationId);

    List<History> findByProcessUUID(String uuid);

    History findByProcessUUIDAndClusterIdAndSessionServerId(String uuid, Long clusterId, Long ssId);

    History findByProcessUUIDAndDomainIdAndWebAppServerId(String uuid, Long domainId, Long appId);

    History findByProcessUUIDAndDomainIdAndWebServerId(String uuid, Long domainId, Long webId);

    List<History> findByProcessUUIDAndWebAppServerIdAndCodeInAndStatusCodeNot(String uuid, Long wasId, List<Integer> codes, String statusType);

    List<History> findByWebAppServerIdAndStatusCodeAndApplicationIdNotNull(Long webAppServerId, String statusType);

    List<History> findByWebAppServerIdAndStatusCodeAndDatasourceIdNotNull(Long webAppServerId, String statusType);

    List<History> findByWebAppServerIdAndCode(Long webAppServerId, int code, Pageable pageable);

    List<History> findBySessionServerIdAndCode(Long sessionServerId, int code, Pageable pageable);

    List<History> findByWebServerIdAndCode(Long webServerId, int code, Pageable pageable);

    List<History> findByWebServerIdAndStatusCodeAndAccessControlIdNotNull(Long webServerId, String statusType);

    @Query("select min(h.id)" +
           "  from History as h where h.deleteYn = 'N' and h.readYn = 'N' and h.endDate is not null and h.message is not null" +
           " group by h.processUUID")
    List<Long> getUnreadHistoryIdList();

    @Query("select min(h.id) as id, " +
           "       min(h.processUUID) as processUUID, " +
           "       min(h.wizardId) as wizardId, " +
           "       min(h.clusterId) as clusterId, " +
           "       min(h.domainId) as domainId, " +
           "       min(h.hostId) as hostId, " +
           "       min(h.engineId) as engineId, " +
           "       min(h.webAppServerId) as webAppServerId, " +
           "       min(h.webServerId) as webServerId, " +
           "       min(h.sessionServerId) as sessionServerId, " +
           "       min(h.datasourceId) as datasourceId, " +
           "       min(h.applicationId) as applicationId, " +
           "       min(h.configFile) as configFile, " +
           "       min(h.title) as title, " +
           "       min(h.message) as message, " +
           "       min(h.statusCode) as statusCode, " +
           "       min(h.taskUser) as taskUser, " +
           "       min(h.createDate) as createDate, " +
           "       min(h.endDate) as endDate, " +
           "       min(h.deleteYn) as deleteYn, " +
           "       min(h.readYn) as readYn " +
           "  from History as h where h.deleteYn = 'N' and h.readYn = 'N' and h.taskUser = ?1 group by h.processUUID")
    List<History> findByUnreadHistoryList(Long id);

    @Query("select min(h.id) from History as h group by h.processUUID")
    List<Long> findByHistoryIdList();

    @Modifying
    @Transactional
    @Query("update History as h set h.readYn = 'Y' where h.processUUID = ?1")
    void setReadYn(String processUUID);

    @Modifying
    @Transactional
    @Query("DELETE FROM History h where h.id <= :minId")
    void deleteFromCnt(long minId);

    @Modifying
    @Transactional
    @Query("DELETE FROM History h where h.createDate < :date")
    void deleteFromDay(Date date);
}
//end of HistoryRepository.java