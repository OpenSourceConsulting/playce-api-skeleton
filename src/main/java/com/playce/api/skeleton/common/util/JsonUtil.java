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

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * <pre>
 * json convert utility class.
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * <pre>
     * convert json string to list.
     * </pre>
     * @param json
     * @param parametrized
     * @param parameterClasses
     * @return
     */
    public static <T> T jsonToList(String json, Class<?> parametrized, Class<?>... parameterClasses) {
        try {
            TypeFactory typeFactory = MAPPER.getTypeFactory();
            return MAPPER.readValue(json, typeFactory.constructParametricType(parametrized, parameterClasses));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * <pre>
     * convert json string to class Object.
     * </pre>
     * @param <T>
     * @param json
     * @param valueType
     * @return
     */
    public static <T> T jsonToObj(String json, Class<T> valueType) {
        try {
            return MAPPER.readValue(json, valueType);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * <pre>
     * convert json string to TypeReference Object.
     * </pre>
     * @param json
     * @param typeRef
     * @return
     */
    public static <T> T jsonToObj(String json, TypeReference<T> typeRef) {
        try {
            return MAPPER.readValue(json, typeRef);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * <pre>
     * convert Object to json String.
     * </pre>
     * @param obj
     * @return
     */
    public static String objToJson(Object obj) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JsonGenerator generator = MAPPER.getFactory().createGenerator(outputStream, JsonEncoding.UTF8);
        MAPPER.writeValue(generator, obj);
        return outputStream.toString(JsonEncoding.UTF8.getJavaName());
    }

    /**
     * <pre>
     * Method to deserialize JSON content as tree expressed using set of JsonNode instances.
     * </pre>
     * @param json JSON content
     * @return
     */
    public static JsonNode readTree(String json) {
        try {
            return MAPPER.readTree(json);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * <pre>
     * convert object to json node
     * </pre>
     *
     * @param value the value
     * @return json node
     */
    public static JsonNode convertToJsonNode(Object value) {
        return MAPPER.convertValue(value, JsonNode.class);
    }

    /**
     * <pre>
     * Create an array node.
     * </pre>
     *
     * @return array node
     */
    public static ArrayNode createArrayNode() {
        try {
            return MAPPER.createArrayNode();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * <pre>
     * Create an object node.
     * </pre>
     *
     * @return object node
     */
    public static ObjectNode createObjectNode() {
        try {
            return MAPPER.createObjectNode();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Convert value t.
     *
     * @param <T>   the type parameter
     * @param value the value
     * @param clazz the clazz
     * @return the t
     */
    public static <T> T convertValue(Object value, Class<T> clazz) {
        try {
            return MAPPER.convertValue(value, clazz);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Convert value t.
     *
     * @param <T>            the type parameter
     * @param value          the value
     * @param toValueTypeRef the to value type ref
     * @return the t
     */
    public static <T> T convertValue(Object value, TypeReference<T> toValueTypeRef) {
        try {
            return MAPPER.convertValue(value, toValueTypeRef);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
//end of JsonUtil.java