package org.gagauz.tapestry.security.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.base.AbstractConditional;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.gagauz.tapestry.security.AccessDeniedException;
import org.gagauz.tapestry.security.api.AccessChecker;

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
    private AccessChecker securityChecker;

    @Override
    protected boolean test() {
        try {
            securityChecker.check(roles);
        } catch (AccessDeniedException e) {
            return false;
        }
        return true;
    }
}
