package com.gagauz.tapestry.security;


public class LoginFailedResult extends LoginResult {
    public LoginFailedResult() {
        super(null);
    }

    @Override
    public boolean isSuccess() {
        return false;
    }
}
