package org.gagauz.tapestry.security.components;

import org.gagauz.tapestry.security.AccessAttributeChecker;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.base.AbstractConditional;
import org.apache.tapestry5.ioc.annotations.Inject;
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

    @Inject
    private AccessAttributeChecker invocationCheckerList;

    @Override
    protected boolean test() {
        try {
            invocationCheckerList.checkAttribute(new TextAccessAttribute(attributes));
        } catch (AccessDeniedException e) {
            return false;
        }
        return true;
    }
}
