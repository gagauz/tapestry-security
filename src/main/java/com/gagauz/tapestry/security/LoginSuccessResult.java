package com.gagauz.tapestry.security;

import com.gagauz.tapestry.security.api.SecurityUser;

public class LoginSuccessResult extends LoginResult {
    public LoginSuccessResult(SecurityUser user) {
        super(user);
    }

    @Override
    public boolean isSuccess() {
        return true;
    }
}