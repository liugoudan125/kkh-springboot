package com.seeseesea.model.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * LoginRequest
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class LoginRequest {
    private String identifier;
    private String accessToken;
}
