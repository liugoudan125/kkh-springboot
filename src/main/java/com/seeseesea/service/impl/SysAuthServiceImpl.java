package com.seeseesea.service.impl;

import com.seeseesea.core.constants.LoginMethodType;
import com.seeseesea.core.constants.RedisKeys;
import com.seeseesea.core.utils.JsonUtils;
import com.seeseesea.model.SysLoginMethod;
import com.seeseesea.model.SysUser;
import com.seeseesea.model.dto.SysLoginMethodDTO;
import com.seeseesea.model.dto.SysRoleDTO;
import com.seeseesea.model.dto.SysUserDTO;
import com.seeseesea.model.request.RegisterRequest;
import com.seeseesea.service.SysAuthService;
import com.seeseesea.service.SysLoginMethodService;
import com.seeseesea.service.SysRoleService;
import com.seeseesea.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

/**
 * SysAuthServiceImpl
 */
@Service
@RequiredArgsConstructor
public class SysAuthServiceImpl implements SysAuthService {

    private final SysUserService sysUserService;
    private final SysLoginMethodService sysLoginMethodService;
    private final SysRoleService sysRoleService;
    private final PasswordEncoder passwordEncoder;

    private final StringRedisTemplate stringRedisTemplate;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerByEmail(RegisterRequest request) {
        SysLoginMethodDTO sysLoginMethodDTO = sysLoginMethodService.getByIdentifierAndMethodType(request.getIdentifier(), LoginMethodType.EMAIL);
        if (sysLoginMethodDTO == null) {
            SysUser sysUser = new SysUser();
            sysUser.setNickname(request.getNickname());
            sysUserService.save(sysUser);
            SysLoginMethod sysLoginMethod = new SysLoginMethod();
            sysLoginMethod.setUserId(sysUser.getId());
            sysLoginMethod.setIdentifier(request.getIdentifier());
            sysLoginMethod.setMethodType(LoginMethodType.EMAIL);
            sysLoginMethod.setAccessToken(passwordEncoder.encode(request.getAccessToken()));
            sysLoginMethodService.save(sysLoginMethod);
            linkDefaultRole(sysUser.getId());
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerByUsername(RegisterRequest request) {
        SysLoginMethodDTO sysLoginMethodDTO = sysLoginMethodService.getByIdentifierAndMethodType(request.getIdentifier(), LoginMethodType.USERNAME);
        if (sysLoginMethodDTO == null) {
            SysUser sysUser = new SysUser();
            sysUser.setNickname(request.getNickname());
            sysUserService.save(sysUser);
            SysLoginMethod sysLoginMethod = new SysLoginMethod();
            sysLoginMethod.setUserId(sysUser.getId());
            sysLoginMethod.setIdentifier(request.getIdentifier());
            sysLoginMethod.setMethodType(LoginMethodType.USERNAME);
            sysLoginMethod.setAccessToken(passwordEncoder.encode(request.getAccessToken()));
            sysLoginMethodService.save(sysLoginMethod);
            linkDefaultRole(sysUser.getId());
        }
    }

    /**
     * 关联默认角色
     *
     * @param userId 用户ID
     */
    private void linkDefaultRole(Long userId) {
        SysRoleDTO sysRoleDTO = sysRoleService.getDefaultRole();
        sysUserService.linkRoles(userId, List.of(sysRoleDTO.getId()));
    }

    @Override
    public String loginByEmail(String identifier, String accessToken) {
        SysLoginMethodDTO sysLoginMethodDTO = sysLoginMethodService
                .getByIdentifierAndMethodType(identifier, LoginMethodType.EMAIL);
        if (sysLoginMethodDTO == null) {
            throw new RuntimeException("该邮箱不存在");
        }
        if (!passwordEncoder.matches(accessToken, sysLoginMethodDTO.getAccessToken())) {
            throw new RuntimeException("密码错误");
        }
        String token = UUID.randomUUID().toString();
        setUserRedisCache(sysLoginMethodDTO.getUserId(), token);
        return token;
    }

    @Override
    public String loginByUsername(String identifier, String accessToken) {
        SysLoginMethodDTO sysLoginMethodDTO = sysLoginMethodService
                .getByIdentifierAndMethodType(identifier, LoginMethodType.USERNAME);
        if (sysLoginMethodDTO == null) {
            throw new RuntimeException("该用户名不存在");
        }
        if (!passwordEncoder.matches(accessToken, sysLoginMethodDTO.getAccessToken())) {
            throw new RuntimeException("密码错误");
        }
        String token = UUID.randomUUID().toString();
        setUserRedisCache(sysLoginMethodDTO.getUserId(), token);
        return token;
    }

    private void setUserRedisCache(Long userId, String token) {
        SysUserDTO sysUserDTO = sysUserService.getById(userId);
        sysUserDTO.setAuthorities(sysUserService.listRoleByUserId(sysUserDTO.getId()));
        sysUserDTO.setAuthenticated(true);
        stringRedisTemplate.opsForValue().set(RedisKeys.USER_TOKEN_KEY.generate(token), JsonUtils.toJsonString(sysUserDTO), Duration.ofDays(1));
    }
}
