package org.gagauz.tapestry.security.api;

import org.gagauz.tapestry.security.AbstractCommonHandlerWrapper;
import org.gagauz.tapestry.security.AccessDeniedException;

// TODO: Auto-generated Javadoc
/**
 * The Interface SecurityExceptionHandler.
 */
public interface AccessDeniedExceptionHandler {

    /**
     * Handle.
     *
     * @param handlerWrapper the handler wrapper
     * @param cause the cause
     */
    void handleException(AbstractCommonHandlerWrapper handlerWrapper, AccessDeniedException cause);

}
