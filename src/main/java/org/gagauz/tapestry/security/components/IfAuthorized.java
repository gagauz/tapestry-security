package org.gagauz.tapestry.security.components;

import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.base.AbstractConditional;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.runtime.Component;
import org.gagauz.tapestry.security.AccessAttributesCheckerContainer;
import org.gagauz.tapestry.security.AccessDeniedException;
import org.gagauz.tapestry.security.api.TextAccessAttribute;

// TODO: Auto-generated Javadoc
/**
 * The Class IfAuthorized.
 */
public class IfAuthorized extends AbstractConditional {

    /** The roles. */
    @Parameter
    private String attributes;

    @InjectContainer
    private Component container;

    @Inject
    private AccessAttributesCheckerContainer accessAttributesValidator;

    @Override
    protected boolean test() {
        try {
            accessAttributesValidator.checkAttribute(new TextAccessAttribute(container, attributes));
        } catch (AccessDeniedException e) {
            return false;
        }
        return true;
    }
}
