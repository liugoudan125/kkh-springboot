package com.seeseesea.core.exception;

import com.seeseesea.core.response.BaseResponse;
import com.seeseesea.model.dto.SysUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    /**
     * 处理自定义异常
     * permitAll 放行的未登录请求，是AnonymousAuthenticationToken
     * ignore 放行的未登录请求，是Authentication = null,会抛出AuthenticationCredentialsNotFoundException
     *
     * @param e 自定义异常
     * @return BaseResponse
     */
    @ExceptionHandler(value = {AuthenticationException.class, AccessDeniedException.class})
    public BaseResponse<Void> handAccessDeniedException(Exception e) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            log.info("用户未登录，拒绝访问: {}", e.getMessage());
            return BaseResponse.fail(401, "用户未登录或登录失效，请重新登录").setException(e.getMessage());
        }
        SysUserDTO sysUserDTO = (SysUserDTO) authentication.getPrincipal();
        log.info("用户权限不足 {}({}) 拒绝访问: {}", sysUserDTO.getNickname(), sysUserDTO.getId(), e.getMessage());
        return BaseResponse.fail(403, "权限不足，无法访问").setException(e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public BaseResponse<Void> handleException(Exception exception) {
        log.error("全局异常: ", exception);
        return BaseResponse.fail(exception.getMessage());
    }
}
