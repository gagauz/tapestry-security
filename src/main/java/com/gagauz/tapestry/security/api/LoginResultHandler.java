package com.gagauz.tapestry.security.api;

import com.gagauz.tapestry.security.LoginResult;

public interface LoginResultHandler {
    void handle(LoginResult result);
}
