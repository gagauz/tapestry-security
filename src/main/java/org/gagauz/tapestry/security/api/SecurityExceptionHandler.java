package org.gagauz.tapestry.security.api;

import org.gagauz.tapestry.security.AbstractCommonHandlerWrapper;
import org.gagauz.tapestry.security.SecurityException;

// TODO: Auto-generated Javadoc
/**
 * The Interface SecurityExceptionHandler.
 */
public interface SecurityExceptionHandler {

    /**
     * Handle.
     *
     * @param handlerWrapper the handler wrapper
     * @param cause the cause
     */
    void handle(AbstractCommonHandlerWrapper handlerWrapper, SecurityException cause);

}
