package com.gagauz.tapestry.security;

import com.gagauz.tapestry.security.api.Credentials;
import com.gagauz.tapestry.security.api.LoginResultHandler;
import com.gagauz.tapestry.security.api.SecurityUser;
import com.gagauz.tapestry.security.api.SecurityUserProvider;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;

import java.util.List;

public class LoginService {

    @Inject
    private SecurityUserProvider sessionUserService;

    @Inject
    private ApplicationStateManager applicationStateManager;

    @Inject
    private List<LoginResultHandler> handlers;

    public SecurityUser authenticate(Credentials credentials) {
        SecurityUser newUser = sessionUserService.loadByCredentials(credentials);

        LoginResult result = null;

        if (null != newUser) {
            SecurityUser oldUser = applicationStateManager.getIfExists(SecurityUser.class);
            applicationStateManager.set(SecurityUser.class, newUser);
            result = new LoginSuccessResult(newUser);
            result.setOldUser(oldUser);
        } else {
            result = new LoginFailedResult();
        }

        for (LoginResultHandler handler : handlers) {
            handler.handle(result);
        }

        return newUser;
    }
}
