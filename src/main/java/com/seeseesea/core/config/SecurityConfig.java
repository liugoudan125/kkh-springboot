package com.seeseesea.core.config;

import com.seeseesea.core.constants.RedisKeys;
import com.seeseesea.core.response.BaseResponse;
import com.seeseesea.core.utils.JsonUtils;
import com.seeseesea.model.dto.SysUserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.Duration;

/**
 * SecurityConfig
 */
@Configuration
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final StringRedisTemplate stringRedisTemplate;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/sys/login/**", "/sys/register/**", "/sys/logout", "/actuator/**", "/druid/**").permitAll()
                            .requestMatchers("/*/admin/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST).hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.PUT).hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.GET).permitAll();
                })
                .exceptionHandling(customizer -> {
                    customizer.authenticationEntryPoint((request, response, authException) -> {
                        BaseResponse<Void> baseResponse = BaseResponse.fail(401, "用户未登录或登录失效，请重新登录").setException(authException.getMessage());
                        writeResponse(response, baseResponse);
                    });
                    customizer.accessDeniedHandler((request, response, accessDeniedException) -> {
                        BaseResponse<Void> baseResponse = BaseResponse.fail(403, "权限不足，无法访问").setException(accessDeniedException.getMessage());
                        writeResponse(response, baseResponse);
                    });
                })
                .addFilterAt((servletRequest, servletResponse, filterChain) -> {
                    HttpServletRequest request = (HttpServletRequest) servletRequest;
                    String token = request.getHeader("token");
                    if (StringUtils.isNoneBlank(token)) {
                        String userTokenRedisKey = RedisKeys.USER_TOKEN_KEY.generate(token);
                        String userJsonStr = stringRedisTemplate.opsForValue().getAndExpire(userTokenRedisKey, Duration.ofDays(1));
                        if (StringUtils.isNotBlank(userJsonStr)) {
                            SysUserDTO sysUserDTO = JsonUtils.fromJsonString(userJsonStr, SysUserDTO.class);
                            SecurityContextHolder.getContext()
                                    .setAuthentication(sysUserDTO);
                        }
                    }
                    filterChain.doFilter(servletRequest, servletResponse);

                }, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private void writeResponse(HttpServletResponse response, BaseResponse<Void> baseResponse) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JsonUtils.toJsonString(baseResponse));
    }

}
