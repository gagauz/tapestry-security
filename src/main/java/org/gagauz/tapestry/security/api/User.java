package org.gagauz.tapestry.security.api;

public interface User {
    boolean checkRoles(String[] needRoles);
}
