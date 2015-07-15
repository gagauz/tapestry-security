package org.gagauz.tapestry.security.api;

public interface AuthenticationHandler<T extends LoginDetails> {
    void handleLogin(User newUser, T credentials);

    void handleLogout(User user);
}
