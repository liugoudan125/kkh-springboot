package com.seeseesea.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.seeseesea.core.response.BaseResponse;
import com.seeseesea.model.request.LoginRequest;
import com.seeseesea.model.request.RegisterRequest;
import com.seeseesea.service.SysAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SysAuthControleller
 */
@RestController
@RequestMapping("sys")
@RequiredArgsConstructor
public class SysAuthController {

    private final SysAuthService sysAuthService;


    /**
     * 邮箱注册
     *
     * @param request
     * @return
     */
    @PostMapping("register")
    public BaseResponse<Void> register(@RequestBody RegisterRequest request) {
        sysAuthService.register(request);
        return BaseResponse.ok();
    }

    /**
     * 登录（邮箱）
     */
    @PostMapping("/login")
    public BaseResponse<String> post(@RequestBody LoginRequest request) throws JsonProcessingException {
        String token = sysAuthService.loginByEmail(request.getIdentifier(), request.getAccessToken());
        return BaseResponse.ok(token);
    }

}
