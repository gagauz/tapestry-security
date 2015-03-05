package com.gagauz.tapestry.security;

public class SecurityException extends RuntimeException {

    private String[] needRoles;

    public SecurityException(String[] needRoles) {
        this.needRoles = needRoles;
    }

    public String[] getNeedRoles() {
        return needRoles;
    }

    public void setNeedRoles(String[] needRoles) {
        this.needRoles = needRoles;
    }

}
