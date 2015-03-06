package org.gagauz.tapestry.security;

import org.apache.tapestry5.services.*;

import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractCommonRequestFilter.
 */
public abstract class AbstractCommonRequestFilter implements PageRenderRequestFilter, ComponentEventRequestFilter {

    /**
     * Handle internal.
     *
     * @param handlerWrapper the handler wrapper
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public abstract void handleInternal(AbstractCommonHandlerWrapper handlerWrapper) throws IOException;

    /* (non-Javadoc)
     * @see org.apache.tapestry5.services.ComponentEventRequestFilter#handle(org.apache.tapestry5.services.ComponentEventRequestParameters, org.apache.tapestry5.services.ComponentEventRequestHandler)
     */
    @Override
    public void handle(final ComponentEventRequestParameters parameters, final ComponentEventRequestHandler handler) throws IOException {
        handleInternal(new AbstractCommonHandlerWrapper(parameters) {
            @Override
            public void handle() throws IOException {
                handler.handle(parameters);
            }
        });
    }

    /* (non-Javadoc)
     * @see org.apache.tapestry5.services.PageRenderRequestFilter#handle(org.apache.tapestry5.services.PageRenderRequestParameters, org.apache.tapestry5.services.PageRenderRequestHandler)
     */
    @Override
    public void handle(final PageRenderRequestParameters parameters, final PageRenderRequestHandler handler) throws IOException {
        handleInternal(new AbstractCommonHandlerWrapper(parameters) {
            @Override
            public void handle() throws IOException {
                handler.handle(parameters);
            }
        });
    }
}
