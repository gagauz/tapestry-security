package org.gagauz.tapestry.security.api;

public interface UserProvider<T extends LoginDetails> {
	User findByCredentials(T credentials);
}
