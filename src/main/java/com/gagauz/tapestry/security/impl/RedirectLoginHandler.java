package com.gagauz.tapestry.security.impl;

import com.gagauz.common.tools.Function;
import com.gagauz.tapestry.security.AbstractCommonHandlerWrapper;
import com.gagauz.tapestry.security.LoginResult;
import com.gagauz.tapestry.security.SecurityConstants;
import com.gagauz.tapestry.security.SecurityException;
import com.gagauz.tapestry.security.api.LoginResultHandler;
import com.gagauz.tapestry.security.api.SecurityExceptionHandler;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.ComponentEventLinkEncoder;
import org.apache.tapestry5.services.Request;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;

public class RedirectLoginHandler implements LoginResultHandler, SecurityExceptionHandler {

    public static final Function<Enum<?>, String> ENUM_TO_STRING = new Function<Enum<?>, String>() {
        @Override
        public String call(Enum<?> p) {
            return p.name();
        }
    };

    @Inject
    @Symbol(SecurityConstants.AUTH_REDIRECT_URL)
    private String loginFormUrl;

    @Inject
    @Symbol(SecurityConstants.REDIRECT_PARAM)
    private String redirectParam;

    @Inject
    private Request request;

    @Inject
    private HttpServletResponse response;

    @Inject
    private ComponentEventLinkEncoder componentEventLinkEncoder;

    /**
     * On {@link SecurityException} redirects to login form page provided by loginFormUrl
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
