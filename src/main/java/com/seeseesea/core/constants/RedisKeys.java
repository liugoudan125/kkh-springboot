package com.seeseesea.core.constants;

/**
 * RedisKeys
 */
public enum RedisKeys {

    USER_TOKEN_KEY("kkh:user:token:%s");

    private final String pattern;

    RedisKeys(String pattern) {
        this.pattern = pattern;
    }

    public String generate(String... args) {
        return String.format(pattern, (Object[]) args);
    }


}
