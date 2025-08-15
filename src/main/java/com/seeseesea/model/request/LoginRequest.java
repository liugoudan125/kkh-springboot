package com.seeseesea.model.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * LoginRequest
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class LoginRequest implements Serializable {
    private String identifier;
    private String accessToken;
}
