package org.gagauz.tapestry.security.api;

import org.gagauz.tapestry.security.AccessDeniedException;

public interface AccessChecker {
    void check(String[] strings) throws AccessDeniedException;
}
