package org.gagauz.tapestry.security;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;
import org.gagauz.tapestry.security.api.AuthHandler;
import org.gagauz.tapestry.security.api.Credentials;
import org.gagauz.tapestry.security.api.SecurityUser;
import org.gagauz.tapestry.security.api.SecurityUserProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    @Inject
    private SecurityUserProvider securityUserProvider;

    @Inject
    private ApplicationStateManager applicationStateManager;

    @Inject
    private List<AuthHandler> authHandlers;

    @Inject
    private Request request;

    public SecurityUser login(Credentials credentials) {
        SecurityUser newUser = securityUserProvider.loadByCredentials(credentials);

        LoginResult result = null;

        if (null != newUser) {
            SecurityUser oldUser = applicationStateManager.getIfExists(newUser.getClass());
            Class clz = newUser.getClass();
            applicationStateManager.set(clz, newUser);
            result = new LoginSuccessResult(newUser);
            result.setOldUser(oldUser);
        } else {
            result = new LoginFailedResult();
        }

        for (AuthHandler handler : authHandlers) {
            handler.handleLogin(result, credentials);
        }

        return newUser;
    }

    public void logout() {

        SecurityUser user = applicationStateManager.getIfExists(SecurityUser.class);

        for (AuthHandler handler : authHandlers) {
            try {
                handler.handleLogout(user);
            } catch (Exception e) {
                log.error("Failed to handle logout", e);
            }
        }

        applicationStateManager.set(SecurityUser.class, null);

        Session session = request.getSession(false);

        if (null != session) {
            try {
                session.invalidate();
            } catch (Exception e) {
                log.error("Session invalidate error", e);
            }
        }
    }
}
