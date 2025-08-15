package com.seeseesea.core.utils;

import com.seeseesea.model.dto.SysUserDTO;
import lombok.experimental.UtilityClass;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * UserUtils
 */
@UtilityClass
public class UserUtils {

    public static SysUserDTO getSysUserDTO() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            throw new AccessDeniedException("用户未登录");
        }
        Object principal = authentication.getPrincipal();
        return (SysUserDTO) principal;
    }

    public static Long getUserId() {
        SysUserDTO sysUserDTO = getSysUserDTO();
        return sysUserDTO.getId();
    }
}
