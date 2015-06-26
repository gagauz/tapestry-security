package org.gagauz.tapestry.security.api;

import org.gagauz.tapestry.security.AccessDeniedException;

public interface AccessAttributeCreatorChecker {
    AccessAttribute create(Object object);

    void check(AccessAttribute accessAttribute) throws AccessDeniedException;
}
