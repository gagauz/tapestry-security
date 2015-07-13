package org.gagauz.tapestry.security.api;

public interface UserProvider<T extends User> {

    T findByCredentials(Credentials credentials);
}
