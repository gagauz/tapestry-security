package org.gagauz.tapestry.security.api;

import org.gagauz.tapestry.security.LoginResult;

public interface AuthHandler {

    void handleLogin(LoginResult result, Credentials credentials);

    void handleLogout(SecurityUser user);
}
