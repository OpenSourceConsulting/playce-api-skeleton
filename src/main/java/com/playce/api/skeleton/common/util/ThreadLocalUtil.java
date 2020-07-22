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
package com.playce.api.skeleton.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
public class ThreadLocalUtil {

    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalUtil.class);

    // 스레드 간 공유 객체를 저장하기 위한 ThreadLocal
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    /**
     * <pre>
     * ThreadLocal에 저장된 스레드 간 공유 객체를 반환한다.
     * </pre>
     * @return
     */
    private static Map<String, Object> getThreadLocal() {
        if (threadLocal.get() == null) {
            Map<String, Object> sharedObject = new HashMap<String, Object>();
            threadLocal.set(sharedObject);
        }

        return threadLocal.get();
    }//end of getThreadLocal()

    /**
     * <pre>
     * 스레드 간 공유 객체에 지정된 key의 이름으로 object를 저장한다.
     * </pre>
     * @param key
     * @param object
     */
    public static void add(String key, Object object) {  // NOPMD
        getThreadLocal().put(key, object);
    }//end of add()

    /**
     * <pre>
     * 스레드 간 공유 객체로부터 지정된 key에 해당하는 object를 가져온다.
     * </pre>
     * @param key
     * @return
     */
    public static Object get(String key) {  // NOPMD
        return getThreadLocal().get(key);
    }//end of get()

    /**
     * <pre>
     * 스레드 간 공유 객체에 지정된 key에 해당하는 object가 있는지 여부를 조사한다.
     * </pre>
     * @param key
     * @return
     */
    public static boolean isExist(String key) {
        Object object = getThreadLocal().get(key);
        if (object != null) {
            return true;
        } else {
            return false;
        }
    }//end of isExist()

    /**
     * <pre>
     * 스레드 간 공유 객체를 초기화 한다.
     * </pre>
     */
    public static void clearSharedObject() {
        getThreadLocal().clear();
        threadLocal.set(null);
    }//end of clearSharedObject()

    /**
     * <pre>
     * 스레드 간 공유 객체에 저장된 키 값 목록을 가져온다.
     * </pre>
     * @return
     */
    public static String[] getThreadLocalKeys() {
        String[] arrKeys = new String[getThreadLocal().size()];
        Iterator<String> keyIter = getThreadLocal().keySet().iterator();

        int i = 0;
        while (keyIter.hasNext()) {
            arrKeys[i] = keyIter.next();
            i++;
        }

        return arrKeys;
    }//end of getThreadLocalKeys()

    /**
     * <pre>
     * 스레드 간 공유 객체의 크기를 가져온다.
     * </pre>
     * @return
     */
    public static int size() {
        return getThreadLocal().size();
    }//end of size()

    /**
     * <pre>
     * 스레드 간 공유 객체에 저장된 모든 object 목록을 가져온다.
     * </pre>
     * @return
     */
    public static Object[] getThreadLocalValues() {
        int size = size();

        String[] arrKeys = getThreadLocalKeys();
        Object[] values = new Object[size];

        for (int i = 0; i < size; i++) {
            values[i] = getThreadLocal().get(arrKeys[i]);
        }

        return values;
    }//end of getThreadLocalValues()

    /**
     * <pre>
     * 스레드 간 공유 객체에 저장된 key와 object를 출력한다.
     * </pre>
     */
    public static void toPrintString() {
        int size = size();

        StringBuffer str = new StringBuffer();
        String[] keys = getThreadLocalKeys();
        Object[] values = getThreadLocalValues();

        for (int i = 0; i < size; i++) {
            str.append("  " + keys[i] + " = " + values[i] + " \n");
        }

        logger.info("{}", str.toString());
    }//end of toPringString()
}
//end of ThreadLocalUtil.java