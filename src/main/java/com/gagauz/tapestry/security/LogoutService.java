package com.gagauz.tapestry.security;

import com.gagauz.tapestry.security.api.LogoutHandler;
import com.gagauz.tapestry.security.api.SecurityUser;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LogoutService {

    private static final Logger log = LoggerFactory.getLogger(LogoutService.class);

    @Inject
    private Request request;

    @Inject
    private ApplicationStateManager applicationStateManager;

    @Inject
    private List<LogoutHandler> handlers;

    public void logout() {

        SecurityUser user = applicationStateManager.getIfExists(SecurityUser.class);

        for (LogoutHandler handler : handlers) {
            try {
                handler.handle(user);
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
