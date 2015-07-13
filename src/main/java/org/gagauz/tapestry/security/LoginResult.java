package org.gagauz.tapestry.security;

import org.gagauz.tapestry.security.api.User;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginResult.
 */
public abstract class LoginResult {

    /** The user. */
    private User user;
    
    /** The old user. */
    private User oldUser;

    /**
     * Instantiates a new login result.
     *
     * @param user the user
     */
    public LoginResult(User user) {
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
    public User getUser() {
        return user;
    }

    /**
     * Gets the user.
     *
     * @param user the user
     * @return the user
     */
    public void getUser(User user) {
        this.user = user;
    }

    /**
     * Gets the old user.
     *
     * @return the old user
     */
    public User getOldUser() {
        return oldUser;
    }

    /**
     * Sets the old user.
     *
     * @param oldUser the new old user
     */
    public void setOldUser(User oldUser) {
        this.oldUser = oldUser;
    }
}
