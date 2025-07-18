package com.seeseesea.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.seeseesea.model.request.RegisterRequest;

/**
 * SysAuthService
 */
public interface SysAuthService {

    void register(RegisterRequest request);

    String loginByEmail(String identifier, String accessToken) throws JsonProcessingException;
}
