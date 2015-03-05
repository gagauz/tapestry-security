package com.gagauz.tapestry.security;

import com.gagauz.tapestry.security.api.SecurityUser;

public abstract class LoginResult {

    private SecurityUser user;
    private SecurityUser oldUser;

    public LoginResult(SecurityUser user) {
        this.user = user;
    }

    public abstract boolean isSuccess();

    public SecurityUser getUser() {
        return user;
    }

    public void getUser(SecurityUser user) {
        this.user = user;
    }

    public SecurityUser getOldUser() {
        return oldUser;
    }

    public void setOldUser(SecurityUser oldUser) {
        this.oldUser = oldUser;
    }
}
