package com.gagauz.tapestry.security.api;

public interface SecurityUser {
    boolean checkRoles(String[] needRoles);
}
