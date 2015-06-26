package org.gagauz.tapestry.security;

import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.services.ComponentEventRequestFilter;
import org.apache.tapestry5.services.PageRenderRequestFilter;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityModule.
 */
public class SecurityModule {

    public static void bind(ServiceBinder binder) {
        binder.bind(AccessDeniedExceptionInterceptorFilter.class).withId("SecurityExceptionRequestFilter");
        binder.bind(AuthService.class).withId("AuthService");
        binder.bind(AccessAttributeChecker.class);
    }

    /**
     * Contribute component class transform worker2.
     *
     * @param configuration the configuration
     */
    @Contribute(ComponentClassTransformWorker2.class)
    public void contributeComponentClassTransformWorker2(OrderedConfiguration<ComponentClassTransformWorker2> configuration) {
        configuration.addInstance("SecurityTransformer", SecurityTransformer.class);
    }

    /**
     * Contribute component event request handler.
     *
     * @param configuration the configuration
     * @param filter the filter
     */
    public void contributeComponentEventRequestHandler(OrderedConfiguration<ComponentEventRequestFilter> configuration,
                                                       @Local AccessDeniedExceptionInterceptorFilter filter) {
        configuration.add("SecurityExceptionFilterComponent", filter, "after:*");
    }

    /**
     * Contribute page render request handler.
     *
     * @param configuration the configuration
     * @param filter the filter
     */
    public void contributePageRenderRequestHandler(OrderedConfiguration<PageRenderRequestFilter> configuration,
                                                   @Local AccessDeniedExceptionInterceptorFilter filter) {
        configuration.add("SecurityExceptionFilterPage", filter, "after:*");
    }
}
