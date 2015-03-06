package org.gagauz.tapestry.security.components;

import org.gagauz.tapestry.security.SecurityChecker;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.base.AbstractConditional;
import org.apache.tapestry5.ioc.annotations.Inject;

// TODO: Auto-generated Javadoc
/**
 * The Class IfAuthorized.
 */
public class IfAuthorized extends AbstractConditional {

    /** The roles. */
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String[] roles;

    /** The security checker. */
    @Inject
    private SecurityChecker securityChecker;

    /* (non-Javadoc)
     * @see org.apache.tapestry5.corelib.base.AbstractConditional#test()
     */
    @Override
    protected boolean test() {
        return securityChecker.isCurrentUserHasRoles(roles);
    }
}
