package org.gagauz.tapestry.security;

import org.gagauz.tapestry.security.api.LogoutHandler;
import org.gagauz.tapestry.security.api.SecurityUser;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class LogoutService.
 */
public class LogoutService {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(LogoutService.class);

    /** The request. */
    @Inject
    private Request request;

    /** The application state manager. */
    @Inject
    private ApplicationStateManager applicationStateManager;

    /** The handlers. */
    @Inject
    private List<LogoutHandler> handlers;

    /**
     * Logout.
     */
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
