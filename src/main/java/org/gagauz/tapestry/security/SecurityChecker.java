package org.gagauz.tapestry.security;

import org.apache.tapestry5.annotations.SessionState;
import org.gagauz.tapestry.security.api.SecurityUser;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityChecker.
 */
public class SecurityChecker {

    /** The session user creator. */
    @SessionState(create = false)
    private SecurityUser securityUser;

    /**
     * Checks if is current user has roles.
     *
     * @param needRoles the need roles
     * @return true, if is current user has roles
     */
    public boolean isCurrentUserHasRoles(String[] needRoles) {
        if (null != securityUser) {
            if (null == needRoles || 0 == needRoles.length) {
                return true;
            }
            return securityUser.checkRoles(needRoles);
        }
        return false;
    }

}
