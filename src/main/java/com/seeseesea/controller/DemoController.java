package com.seeseesea.controller;

import com.seeseesea.core.response.BaseResponse;
import com.seeseesea.core.utils.UserUtils;
import com.seeseesea.model.dto.SysUserDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DemoController
 */
@RestController
@RequestMapping("demo")
public class DemoController {


    @GetMapping("one")
    public BaseResponse<String> one() {
        return BaseResponse.ok("one");
    }

    @GetMapping("two")
    public BaseResponse<String> two() {
        return BaseResponse.ok("two");
    }

    @GetMapping("three")
    public BaseResponse<String> three() {
        return BaseResponse.ok("three");
    }

    @GetMapping("four")
    public BaseResponse<String> four() {
        return BaseResponse.ok("four");
    }

    @GetMapping("admin/one")
    public BaseResponse<String> adminOne() {
        return BaseResponse.ok("one");
    }

    @GetMapping("admin/two")
    public BaseResponse<String> adminTwo() {
        return BaseResponse.ok("two");
    }

    @GetMapping("admin/three")
    public BaseResponse<String> adminThree() {
        return BaseResponse.ok("three");
    }

    @GetMapping("admin/four")
    public BaseResponse<String> adminFour() {
        return BaseResponse.ok("four");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/info")
    public BaseResponse<SysUserDTO> getSysUserDTO() {
        return BaseResponse.ok(UserUtils.getSysUserDTO());
    }

}
