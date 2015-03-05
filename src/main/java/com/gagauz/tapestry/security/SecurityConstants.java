package com.gagauz.tapestry.security;

public class SecurityConstants {
    private SecurityConstants() {
    }

    public static final String REDIRECT_PARAM = "security.redirect-param";
    public static final String REDIRECT_PARAM_VALUE = value(REDIRECT_PARAM);

    public static final String AUTH_REDIRECT_URL = "security.auth-redirect-url";
    public static final String AUTH_REDIRECT_URL_VALUE = value(AUTH_REDIRECT_URL);

    private static String value(String str) {
        return "${" + str + "}";
    }
}
