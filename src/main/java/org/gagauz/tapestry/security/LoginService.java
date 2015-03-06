package org.gagauz.tapestry.security;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.gagauz.tapestry.security.api.Credentials;
import org.gagauz.tapestry.security.api.LoginResultHandler;
import org.gagauz.tapestry.security.api.SecurityUser;
import org.gagauz.tapestry.security.api.SecurityUserProvider;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginService.
 */
public class LoginService {

    /** The session user service. */
    @Inject
    private SecurityUserProvider sessionUserService;

    /** The application state manager. */
    @Inject
    private ApplicationStateManager applicationStateManager;

    /** The handlers. */
    @Inject
    private List<LoginResultHandler> handlers;

    /**
     * Authenticate.
     *
     * @param Accepts any implementation of {@link Credentials} interface which will be passed to implementation of {@link SecurityUserProvider}  
     * @return {@link SecurityUser} instance
     */
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
