package com.seeseesea.core.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonUtils
 */
public class JsonUtils {

    private static ObjectMapper objectMapper;

    public static void setObjectMapper(ObjectMapper objectMapper) {
        JsonUtils.objectMapper = objectMapper;
    }

    private static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            throw new IllegalStateException("没有给JsonUtils设置ObjectMapper. 请使用JsonUtils.setObjectMapper(ObjectMapper objectMapper).设置ObjectMapper");
        }
        return objectMapper;
    }

    public static String toJsonString(Object object) {
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Object转Json字符串异常", e);
        }
    }

    public static <T> T fromJsonString(String jsonString, Class<T> valueType) {
        try {
            return getObjectMapper().readValue(jsonString, valueType);
        } catch (Exception e) {
            throw new RuntimeException("Json字符串转Object异常", e);
        }
    }

    public static <T> T fromJsonString(String jsonString, TypeReference<T> typeReference) {
        try {
            return getObjectMapper().readValue(jsonString, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Json字符串转Object异常", e);
        }
    }

}
