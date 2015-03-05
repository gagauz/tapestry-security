package com.gagauz.tapestry.security;

import com.gagauz.tapestry.security.api.SecurityUser;

import javax.inject.Inject;

public class SecurityChecker {
    @Inject
    private SecurityUserCreator sessionUserCreator;

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
