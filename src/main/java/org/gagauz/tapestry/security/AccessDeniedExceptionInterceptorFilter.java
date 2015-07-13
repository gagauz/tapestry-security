package org.gagauz.tapestry.security;

import java.io.IOException;
import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.gagauz.tapestry.security.api.AccessDeniedHandler;
import org.gagauz.tapestry.utils.AbstractCommonHandlerWrapper;
import org.gagauz.tapestry.utils.AbstractCommonRequestFilter;

public class AccessDeniedExceptionInterceptorFilter extends AbstractCommonRequestFilter {

    @Inject
    private List<AccessDeniedHandler> handlers;

    @Override
    public void handleInternal(AbstractCommonHandlerWrapper handlerWrapper) throws IOException {
        try {
            handlerWrapper.handle();
        } catch (IOException io) {
            // Pass IOException through.
            throw io;
        } catch (Throwable e) {
            Throwable cause = e;
            while (null != cause.getCause()) {
                cause = cause.getCause();
            }
            if (cause instanceof AccessDeniedException) {
                for (AccessDeniedHandler exceptionHandler : handlers) {
                    exceptionHandler.handleException(handlerWrapper, (AccessDeniedException) cause);
                }
                return;
            }
            // Pass non-security exception through.
            throw new RuntimeException(e);
        }
    }

}
