package org.gagauz.tapestry.security.impl;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.ComponentEventLinkEncoder;
import org.apache.tapestry5.services.Request;
import org.gagauz.tapestry.security.AbstractCommonHandlerWrapper;
import org.gagauz.tapestry.security.LoginResult;
import org.gagauz.tapestry.security.SecurityConstants;
import org.gagauz.tapestry.security.SecurityException;
import org.gagauz.tapestry.security.api.LoginResultHandler;
import org.gagauz.tapestry.security.api.SecurityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;

// TODO: Auto-generated Javadoc
/**
 * The Class RedirectLoginHandler.
 */
public class RedirectLoginHandler implements LoginResultHandler, SecurityExceptionHandler {

    /** The login form url. */
    @Inject
    @Symbol(SecurityConstants.AUTH_REDIRECT_URL)
    private String loginFormUrl;

    /** The redirect param. */
    @Inject
    @Symbol(SecurityConstants.REDIRECT_PARAM)
    private String redirectParam;

    /** The request. */
    @Inject
    private Request request;

    /** The response. */
    @Inject
    private HttpServletResponse response;

    /** The component event link encoder. */
    @Inject
    private ComponentEventLinkEncoder componentEventLinkEncoder;

    /**
     * On {@link SecurityException} redirects to login form page provided by loginFormUrl.
     *
     * @param handlerWrapper the handler wrapper
     * @param cause the cause
     */
    @Override
    public void handle(AbstractCommonHandlerWrapper handlerWrapper, SecurityException cause) {
        try {
            Link link;
            if (handlerWrapper.getComponentEventRequestParameters() != null) {
                link = componentEventLinkEncoder.createComponentEventLink(handlerWrapper.getComponentEventRequestParameters(), false);
            } else {
                link = componentEventLinkEncoder.createPageRenderLink(handlerWrapper.getPageRenderRequestParameters());
            }

            response.sendRedirect(loginFormUrl + '?' + redirectParam + '=' + URLEncoder.encode(link.toRedirectURI()));
            response.flushBuffer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* (non-Javadoc)
     * @see org.gagauz.tapestry.security.api.LoginResultHandler#handle(org.gagauz.tapestry.security.LoginResult)
     */
    @Override
    public void handle(LoginResult result) {
        if (result.isSuccess() && null != request.getParameter(redirectParam)) {
            try {
                response.sendRedirect(request.getParameter(redirectParam));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
