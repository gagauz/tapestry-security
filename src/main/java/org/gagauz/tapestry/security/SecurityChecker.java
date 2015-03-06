package org.gagauz.tapestry.security;

import org.gagauz.tapestry.security.api.SecurityUser;

import javax.inject.Inject;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityChecker.
 */
public class SecurityChecker {
    
    /** The session user creator. */
    @Inject
    private SecurityUserCreator sessionUserCreator;

    /**
     * Checks if is current user has roles.
     *
     * @param needRoles the need roles
     * @return true, if is current user has roles
     */
    public boolean isCurrentUserHasRoles(String[] needRoles) {

        SecurityUser user = sessionUserCreator.getUserFromContext();
        if (null != user) {
            if (null == needRoles || 0 == needRoles.length) {
                return true;
            }
            return user.checkRoles(needRoles);
        }
        return false;
    }

}
