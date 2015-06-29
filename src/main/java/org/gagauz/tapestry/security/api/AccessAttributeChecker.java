package org.gagauz.tapestry.security.api;

import org.gagauz.tapestry.security.AccessDeniedException;

public interface AccessAttributeChecker {

    void check(AccessAttribute accessAttribute) throws AccessDeniedException;
}
