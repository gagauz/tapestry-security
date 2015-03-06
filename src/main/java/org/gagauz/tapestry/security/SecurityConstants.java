package org.gagauz.tapestry.security;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityConstants.
 */
public class SecurityConstants {
    
    /**
     * Instantiates a new security constants.
     */
    private SecurityConstants() {
    }

    /** The Constant REDIRECT_PARAM. */
    public static final String REDIRECT_PARAM = "security.redirect-param";
    
    /** The Constant REDIRECT_PARAM_VALUE. */
    public static final String REDIRECT_PARAM_VALUE = value(REDIRECT_PARAM);

    /** The Constant AUTH_REDIRECT_URL. */
    public static final String AUTH_REDIRECT_URL = "security.auth-redirect-url";
    
    /** The Constant AUTH_REDIRECT_URL_VALUE. */
    public static final String AUTH_REDIRECT_URL_VALUE = value(AUTH_REDIRECT_URL);

    /**
     * Value.
     *
     * @param str the str
     * @return the string
     */
    private static String value(String str) {
        return "${" + str + "}";
    }
}
