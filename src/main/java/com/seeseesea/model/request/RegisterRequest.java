package com.seeseesea.model.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RegisterRequest
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class RegisterRequest {
    private String identifier;
    private String accessToken;
    private String nickname;
}
