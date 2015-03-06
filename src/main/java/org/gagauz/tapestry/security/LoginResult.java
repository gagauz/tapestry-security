package org.gagauz.tapestry.security;

import org.gagauz.tapestry.security.api.SecurityUser;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginResult.
 */
public abstract class LoginResult {

    /** The user. */
    private SecurityUser user;
    
    /** The old user. */
    private SecurityUser oldUser;

    /**
     * Instantiates a new login result.
     *
     * @param user the user
     */
    public LoginResult(SecurityUser user) {
        this.user = user;
    }

    /**
     * Checks if is success.
     *
     * @return true, if is success
     */
    public abstract boolean isSuccess();

    /**
     * Gets the user.
     *
     * @return the user
     */
    public SecurityUser getUser() {
        return user;
    }

    /**
     * Gets the user.
     *
     * @param user the user
     * @return the user
     */
    public void getUser(SecurityUser user) {
        this.user = user;
    }

    /**
     * Gets the old user.
     *
     * @return the old user
     */
    public SecurityUser getOldUser() {
        return oldUser;
    }

    /**
     * Sets the old user.
     *
     * @param oldUser the new old user
     */
    public void setOldUser(SecurityUser oldUser) {
        this.oldUser = oldUser;
    }
}
