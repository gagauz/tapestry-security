package org.gagauz.tapestry.security;

import org.gagauz.tapestry.security.api.SecurityUser;

import org.apache.tapestry5.services.ApplicationStateManager;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityUserCreator.
 */
public class SecurityUserCreator {
    
    /** The Constant USER_SESSION_PATH. */
    static final String USER_SESSION_PATH = "sso:" + SecurityUser.class.getName();
    
    /** The state manager. */
    protected final ApplicationStateManager stateManager;

    /**
     * Instantiates a new security user creator.
     *
     * @param stateManager the state manager
     */
    public SecurityUserCreator(ApplicationStateManager stateManager) {
        this.stateManager = stateManager;
    }

    /**
     * Creates the user.
     *
     * @param newUser the new user
     * @return the security user
     */
    public SecurityUser createUser(SecurityUser newUser) {
        if (newUser != null) {
            SecurityUser oldUser = stateManager.getIfExists(SecurityUser.class);
            stateManager.set(SecurityUser.class, newUser);
            return oldUser;
        }
        return null;
    }

    /**
     * Gets the user from context.
     *
     * @return the user from context
     */
    public SecurityUser getUserFromContext() {
        return stateManager.getIfExists(SecurityUser.class);
    }

}
