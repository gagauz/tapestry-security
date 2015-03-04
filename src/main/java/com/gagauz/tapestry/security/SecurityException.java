package com.gagauz.tapestry.security;

import com.gagauz.tapestry.security.api.Role;

public class SecurityException extends RuntimeException {

    private Role[] needRoles;

    public SecurityException(Role[] needRoles) {
        this.needRoles = needRoles;
    }

    public Role[] getNeedRoles() {
        return needRoles;
    }

    public void setNeedRoles(Role[] needRoles) {
        this.needRoles = needRoles;
    }

}
