package org.gagauz.tapestry.security.api;

public interface LoginHandler {
    void handleLogin(User newUser, Credentials credentials);
}
