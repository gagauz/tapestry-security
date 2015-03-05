package com.gagauz.tapestry.security.components;

import com.gagauz.tapestry.security.SecurityChecker;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.base.AbstractConditional;
import org.apache.tapestry5.ioc.annotations.Inject;

public class IfAuthorized extends AbstractConditional {

    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String[] roles;

    @Inject
    private SecurityChecker securityChecker;

    @Override
    protected boolean test() {
        return securityChecker.isCurrentUserHasRoles(roles);
    }
}
