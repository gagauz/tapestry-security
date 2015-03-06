package org.gagauz.tapestry.security;

import org.gagauz.tapestry.security.api.SecurityUser;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginSuccessResult.
 */
public class LoginSuccessResult extends LoginResult {
    
    /**
     * Instantiates a new login success result.
     *
     * @param user the user
     */
    public LoginSuccessResult(SecurityUser user) {
        super(user);
    }

    /* (non-Javadoc)
     * @see org.gagauz.tapestry.security.LoginResult#isSuccess()
     */
    @Override
    public boolean isSuccess() {
        return true;
    }
}