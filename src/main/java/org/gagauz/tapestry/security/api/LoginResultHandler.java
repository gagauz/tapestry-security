package org.gagauz.tapestry.security.api;

import org.gagauz.tapestry.security.LoginResult;

// TODO: Auto-generated Javadoc
/**
 * The Interface LoginResultHandler.
 */
public interface LoginResultHandler {

    /**
     * Handle.
     *
     * @param result the result
     */
    void handle(LoginResult result, Credentials credentials);
}
