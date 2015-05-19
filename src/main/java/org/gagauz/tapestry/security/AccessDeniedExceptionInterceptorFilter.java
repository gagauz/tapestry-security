package org.gagauz.tapestry.security;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.gagauz.tapestry.security.api.AccessDeniedExceptionHandler;

import java.io.IOException;
import java.util.List;

public class AccessDeniedExceptionInterceptorFilter extends AbstractCommonRequestFilter {

    @Inject
    private List<AccessDeniedExceptionHandler> handlers;

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
                for (AccessDeniedExceptionHandler exceptionHandler : handlers) {
                    exceptionHandler.handleException(handlerWrapper, (AccessDeniedException) cause);
                }
                return;
            }
            // Pass non-security exception through.
            throw new RuntimeException(e);
        }
    }

}
