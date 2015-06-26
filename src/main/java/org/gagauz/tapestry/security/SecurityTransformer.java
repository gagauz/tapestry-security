package org.gagauz.tapestry.security;

import org.gagauz.tapestry.security.api.AccessAttribute;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.plastic.MethodAdvice;
import org.apache.tapestry5.plastic.MethodInvocation;
import org.apache.tapestry5.plastic.PlasticClass;
import org.apache.tapestry5.plastic.PlasticMethod;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.runtime.ComponentEvent;
import org.apache.tapestry5.services.ComponentEventHandler;
import org.apache.tapestry5.services.transform.ComponentClassTransformWorker2;
import org.apache.tapestry5.services.transform.TransformationSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * The Class SecurityTransformer.
 */
public class SecurityTransformer implements ComponentClassTransformWorker2 {

    protected static Logger LOG = LoggerFactory.getLogger(SecurityTransformer.class);

    //Contributed 
    @Inject
    private AccessAttributeChecker invocationCheckerResolver;

    private MethodAdvice getSecurityAdvice(final PlasticMethod securedMethod, final List<AccessAttribute> methodAttribute) {
        return new MethodAdvice() {
            @Override
            public void advise(MethodInvocation invocation) {
                for (AccessAttribute attribute : methodAttribute) {
                    invocationCheckerResolver.checkAttribute(attribute);
                }
            }
        };
    }

    @Override
    public void transform(PlasticClass plasticClass, TransformationSupport support, MutableComponentModel model) {
        final List<AccessAttribute> attributes = invocationCheckerResolver.getAccessAttribute(plasticClass);

        if (!attributes.isEmpty()) {
            support.addEventHandler(EventConstants.ACTIVATE, 0, "SecurityTransformer activate event handler", new ComponentEventHandler() {
                @Override
                public void handleEvent(Component instance, ComponentEvent event) {
                    for (AccessAttribute attribute : attributes) {
                        invocationCheckerResolver.checkAttribute(attribute);
                    }
                }
            });
        }
        for (PlasticMethod securedMethod : plasticClass.getMethods()) {
            final List<AccessAttribute> methodAttribute = invocationCheckerResolver.getAccessAttribute(plasticClass);
            if (!methodAttribute.isEmpty()) {
                securedMethod.addAdvice(getSecurityAdvice(securedMethod, methodAttribute));
            }
        }
    }
}
