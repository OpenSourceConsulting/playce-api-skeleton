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
 * Author			Date				Description
 * ---------------	----------------	------------
 * SangCheon Park   Apr 22, 2019		    First Draft.
 */
package com.playce.api.skeleton.common.helper;

import com.playce.api.skeleton.model.History;
import com.playce.api.skeleton.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.playce.api.skeleton.common.constant.PlayceConstants.HISTORY_STATUS_RUNNING;

/**
 * <pre>
 *
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
@Component
public class HistoryResultHelper {

    @Autowired
    private HistoryRepository historyRepository;

    /**
     * Gets history result.
     *
     * @param historyId the history id
     * @return the history result
     */
    public History getHistoryResult(Long historyId) {
        History history = null;
        int cnt = 0;
        int maxCnt = 6;
        while (true) {
            history = historyRepository.findById(historyId).orElse(null);

            if (history != null) {
                if (history.getStatusCode().equals(HISTORY_STATUS_RUNNING)) {
                    if (cnt++ >= maxCnt) {
                        break;
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // ignore...
                    }

                    continue;
                }

                history.setReadYn("Y");
                break;
            } else {
                break;
            }
        }

        return history;
    }

}
//end of HistoryResultHelper.java