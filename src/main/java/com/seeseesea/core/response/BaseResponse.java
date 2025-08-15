package com.seeseesea.core.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * CommonResponse
 */
@Data
public class BaseResponse<T> {
    //响应状态码 0 成功, -1 失败
    private Integer code;

    //响应错误消息
    private String errorMsg;

    //响应数据
    private T data;

    private String exception;


    public static <T> BaseResponse<T> ok(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setData(data);
        return baseResponse;
    }

    public static <T> BaseResponse<T> ok() {
        return ok(null);
    }

    public static BaseResponse<Void> fail(Integer code, String message) {
        BaseResponse<Void> baseResponse = new BaseResponse<>();
        baseResponse.setCode(code);
        baseResponse.setErrorMsg(message);
        return baseResponse;
    }

    public static BaseResponse<Void> fail(String message) {
        return fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public BaseResponse<T> setException(String exception) {
        this.exception = exception;
        return this;
    }

}
