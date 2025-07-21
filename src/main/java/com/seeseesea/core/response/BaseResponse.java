package com.seeseesea.core.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * CommonResponse
 */
@Data
public class BaseResponse<T> {
    //响应状态码 0 成功, -1 失败
    private Boolean status;

    //响应消息
    private String errorMessage;

    //响应数据
    private T data;

    //响应额外数据
    private Map<String, Object> extra;


    //工厂方法
    public static <T> BaseResponse<T> ok(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setStatus(true);
        baseResponse.setData(data);
        return baseResponse;
    }

    //工厂方法
    public static <T> BaseResponse<T> ok() {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setStatus(true);
        baseResponse.setData(null);
        return baseResponse;
    }

    public static BaseResponse<Void> fail(String message) {
        BaseResponse<Void> baseResponse = new BaseResponse<>();
        baseResponse.setStatus(false);
        baseResponse.setErrorMessage(message);
        return baseResponse;
    }

    public BaseResponse<T> putExtra(String key, Object value) {
        if (this.extra == null) {
            this.extra = new HashMap<>();
        }
        this.extra.put(key, value);
        return this;
    }

}
