package org.gagauz.tapestry.security;

import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;
import org.gagauz.tapestry.security.api.Credentials;
import org.gagauz.tapestry.security.api.LoginHandler;
import org.gagauz.tapestry.security.api.LogoutHandler;
import org.gagauz.tapestry.security.api.User;
import org.gagauz.tapestry.security.api.UserProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    @Inject
    private UserProvider userProvider;

    @Inject
    private ApplicationStateManager applicationStateManager;

    @Inject
    private List<LoginHandler> loginHandlers;

    @Inject
    private List<LogoutHandler> logoutHandlers;

    @Inject
    private Request request;

    public User login(Credentials credentials) {
        User newUser = userProvider.findByCredentials(credentials);

        LoginResult result = null;

        if (null != newUser) {
            User oldUser = applicationStateManager.getIfExists(newUser.getClass());
            Class clz = newUser.getClass();
            applicationStateManager.set(clz, newUser);
        }
        for (LoginHandler handler : loginHandlers) {
            handler.handleLogin(newUser, credentials);
        }

        return newUser;
    }

    public void logout() {

        User user = applicationStateManager.getIfExists(User.class);

        for (LogoutHandler handler : logoutHandlers) {
            handler.handleLogout(user);
        }

        applicationStateManager.set(User.class, null);

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
