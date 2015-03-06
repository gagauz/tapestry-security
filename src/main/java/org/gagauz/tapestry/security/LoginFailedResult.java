package org.gagauz.tapestry.security;


// TODO: Auto-generated Javadoc
/**
 * The Class LoginFailedResult.
 */
public class LoginFailedResult extends LoginResult {
    
    /**
     * Instantiates a new login failed result.
     */
    public LoginFailedResult() {
        super(null);
    }

    /* (non-Javadoc)
     * @see org.gagauz.tapestry.security.LoginResult#isSuccess()
     */
    @Override
    public boolean isSuccess() {
        return false;
    }
}
