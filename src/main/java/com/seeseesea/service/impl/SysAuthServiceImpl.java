package com.seeseesea.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seeseesea.core.constants.LoginMethodType;
import com.seeseesea.core.constants.RedisKeys;
import com.seeseesea.core.utils.JsonUtils;
import com.seeseesea.model.SysUserDTO;
import com.seeseesea.model.request.RegisterRequest;
import com.seeseesea.model.SysLoginMethod;
import com.seeseesea.model.SysLoginMethodDTO;
import com.seeseesea.model.SysUser;
import com.seeseesea.service.SysAuthService;
import com.seeseesea.service.SysLoginMethodService;
import com.seeseesea.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.UUID;

/**
 * SysAuthServiceImpl
 */
@Service
@RequiredArgsConstructor
public class SysAuthServiceImpl implements SysAuthService {

    private final SysUserService sysUserService;
    private final SysLoginMethodService sysLoginMethodService;

    private final PasswordEncoder passwordEncoder;

    private final StringRedisTemplate stringRedisTemplate;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterRequest request) {
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
        }
    }

    @Override
    public String loginByEmail(String identifier, String accessToken) throws JsonProcessingException {
        SysLoginMethodDTO sysLoginMethodDTO = sysLoginMethodService
                .getByIdentifierAndMethodType(identifier, LoginMethodType.EMAIL);
        if (sysLoginMethodDTO == null) {
            throw new RuntimeException("该邮箱不存在");
        }
        if (!passwordEncoder.matches(accessToken, sysLoginMethodDTO.getAccessToken())) {
            throw new RuntimeException("密码错误");
        }
        SysUserDTO sysUserDTO = sysUserService.getById(sysLoginMethodDTO.getUserId());
        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(RedisKeys.USER_TOKEN_KEY.generate(token), JsonUtils.toJsonString(sysUserDTO), Duration.ofDays(1));
        return token;
    }
}
