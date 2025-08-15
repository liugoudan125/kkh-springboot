package com.seeseesea.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.seeseesea.core.response.BaseResponse;
import com.seeseesea.core.utils.UserUtils;
import com.seeseesea.model.dto.SysUserDTO;
import com.seeseesea.model.request.LoginRequest;
import com.seeseesea.model.request.RegisterRequest;
import com.seeseesea.service.SysAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
    @PostMapping("register/email")
    public BaseResponse<Void> registerByEmail(@RequestBody RegisterRequest request) {
        sysAuthService.registerByEmail(request);
        return BaseResponse.ok();
    }

    /**
     * 用户名注册
     *
     * @param request
     * @return
     */
    @PostMapping("register/username")
    public BaseResponse<Void> registerByUsername(@RequestBody RegisterRequest request) {
        sysAuthService.registerByUsername(request);
        return BaseResponse.ok();
    }

    /**
     * 登录（邮箱）
     */
    @PostMapping("/login/email")
    public BaseResponse<String> loginByEmail(@RequestBody LoginRequest request) throws JsonProcessingException {
        String token = sysAuthService.loginByEmail(request.getIdentifier(), request.getAccessToken());
        return BaseResponse.ok(token);
    }

    /**
     * 登录（用户名）
     */
    @PostMapping("/login/username")
    public BaseResponse<String> loginByUsername(@RequestBody LoginRequest request) {
        String token = sysAuthService.loginByUsername(request.getIdentifier(), request.getAccessToken());
        return BaseResponse.ok(token);
    }

    /**
     * 获取当前登录用户信息
     */

    @GetMapping("user/info")
    public BaseResponse<SysUserDTO> userInfo() {
        SysUserDTO sysUserDTO = UserUtils.getSysUserDTO();
        return BaseResponse.ok(sysUserDTO);
    }

}
