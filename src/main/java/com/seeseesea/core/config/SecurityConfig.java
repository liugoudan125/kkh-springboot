package com.seeseesea.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seeseesea.core.constants.RedisKeys;
import com.seeseesea.core.response.BaseResponse;
import com.seeseesea.core.utils.JsonUtils;
import com.seeseesea.model.SysUserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Collection;
import java.util.List;

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
                    registry.requestMatchers("/*/admin/**").authenticated()
                            .anyRequest().permitAll();
                })
                .exceptionHandling(customizer -> {
                    customizer.authenticationEntryPoint((request, response, authException) -> {
                        BaseResponse<Void> baseResponse = BaseResponse.fail(authException.getMessage()).putExtra("code", 401);
                        writeResponse(response, baseResponse);
                    });
                    customizer.accessDeniedHandler((request, response, accessDeniedException) -> {
                        BaseResponse<Void> baseResponse = BaseResponse.fail(accessDeniedException.getMessage()).putExtra("code", 403);
                        writeResponse(response, baseResponse);
                    });
                })
                .addFilterAt((ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) -> {
                    HttpServletRequest request = (HttpServletRequest) servletRequest;
                    String token = request.getHeader("token");
                    if (StringUtils.isNoneBlank(token)) {
                        String userTokenRedisKey = RedisKeys.USER_TOKEN_KEY.generate(token);
                        String userJsonStr = stringRedisTemplate.opsForValue().getAndExpire(userTokenRedisKey, Duration.ofDays(1));
                        if (StringUtils.isNotBlank(userJsonStr)) {
                            SysUserDTO sysUserDTO = JsonUtils.fromJsonString(userJsonStr, SysUserDTO.class);
                            SecurityContextHolder.getContext()
                                    .setAuthentication(new Authentication() {
                                        private boolean authenticated = true;

                                        @Override
                                        public Collection<? extends GrantedAuthority> getAuthorities() {
                                            return List.of();
                                        }

                                        @Override
                                        public Object getCredentials() {
                                            return null;
                                        }

                                        @Override
                                        public Object getDetails() {
                                            return null;
                                        }

                                        @Override
                                        public Object getPrincipal() {
                                            return sysUserDTO;
                                        }

                                        @Override
                                        public boolean isAuthenticated() {
                                            return authenticated;
                                        }

                                        @Override
                                        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
                                            this.authenticated = isAuthenticated;
                                        }

                                        @Override
                                        public String getName() {
                                            return sysUserDTO.getNickname();
                                        }
                                    });

                        }
                    }
                    filterChain.doFilter(servletRequest, servletResponse);

                }, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            web.ignoring()
                    .requestMatchers("/sys/login", "/sys/register", "/sys/logout", "/actuator/**");
        };
    }

    private void writeResponse(HttpServletResponse response, BaseResponse<Void> baseResponse) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JsonUtils.toJsonString(baseResponse));
    }
}
