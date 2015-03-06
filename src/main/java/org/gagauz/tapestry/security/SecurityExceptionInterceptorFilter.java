package org.gagauz.tapestry.security;

import org.gagauz.tapestry.security.api.SecurityExceptionHandler;

import org.apache.tapestry5.ioc.annotations.Inject;

import java.io.IOException;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityExceptionInterceptorFilter.
 */
public class SecurityExceptionInterceptorFilter extends AbstractCommonRequestFilter {

    /** The handlers. */
    @Inject
    private List<SecurityExceptionHandler> handlers;

    /* (non-Javadoc)
     * @see org.gagauz.tapestry.security.AbstractCommonRequestFilter#handleInternal(org.gagauz.tapestry.security.AbstractCommonHandlerWrapper)
     */
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
            if (cause instanceof SecurityException) {
                for (SecurityExceptionHandler exceptionHandler : handlers) {
                    exceptionHandler.handle(handlerWrapper, (SecurityException) cause);
                }
                return;
            }
            // Pass non-security exception through.
            throw new RuntimeException(e);
        }
    }

}
