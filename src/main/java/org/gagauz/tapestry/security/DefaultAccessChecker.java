package org.gagauz.tapestry.security;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.gagauz.tapestry.security.api.AccessChecker;
import org.gagauz.tapestry.security.api.SecurityUser;

public class DefaultAccessChecker implements AccessChecker {

    /** The session user creator. */
    @Inject
    private ApplicationStateManager applicationStateManager;

    /**
     * Checks if is current user has roles.
     * 
     * @param needRoles
     *            the need roles
     */
    @Override
    public void check(String[] needRoles) throws AccessDeniedException {
        if (null != needRoles) {
            SecurityUser securityUser = applicationStateManager.getIfExists(SecurityUser.class);
            if (null == securityUser || !securityUser.checkRoles(needRoles)) {
                throw new AccessDeniedException(needRoles);
            }
        }

    }
}
