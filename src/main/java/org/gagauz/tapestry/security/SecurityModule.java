package org.gagauz.tapestry.security;

import org.gagauz.tapestry.security.impl.RedirectLoginHandler;

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

    /**
     * Bind.
     *
     * @param binder the binder
     */
    public static void bind(ServiceBinder binder) {
        binder.bind(SecurityChecker.class).withId("SecurityChecker");
        binder.bind(SecurityUserCreator.class).withId("SessionUserCreator");
        binder.bind(SecurityExceptionInterceptorFilter.class).withId("SecurityExceptionRequestFilter");
        binder.bind(LoginService.class).withId("LoginService");
        binder.bind(LogoutService.class).withId("LogoutService");
        binder.bind(RedirectLoginHandler.class).withId("RedirectLoginHandler");
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
                                                       @Local SecurityExceptionInterceptorFilter filter) {
        configuration.add("SecurityExceptionFilterComponent", filter, "after:*");
    }

    /**
     * Contribute page render request handler.
     *
     * @param configuration the configuration
     * @param filter the filter
     */
    public void contributePageRenderRequestHandler(OrderedConfiguration<PageRenderRequestFilter> configuration,
                                                   @Local SecurityExceptionInterceptorFilter filter) {
        configuration.add("SecurityExceptionFilterPage", filter, "after:*");
    }
}
