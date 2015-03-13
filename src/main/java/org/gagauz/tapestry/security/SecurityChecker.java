package org.gagauz.tapestry.security;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.gagauz.tapestry.security.api.SecurityUser;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityChecker.
 */
public class SecurityChecker {

    /** The session user creator. */
    @Inject
    private ApplicationStateManager applicationStateManager;

    /**
     * Checks if is current user has roles.
     *
     * @param needRoles the need roles
     * @return true, if is current user has roles
     */
    public boolean isCurrentUserHasRoles(String[] needRoles) {
        SecurityUser securityUser = applicationStateManager.getIfExists(SecurityUser.class);
        if (null != securityUser) {
            if (null == needRoles || 0 == needRoles.length) {
                return true;
            }
            return securityUser.checkRoles(needRoles);
        }
        return false;
    }

}
