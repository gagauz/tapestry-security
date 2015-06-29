package org.gagauz.tapestry.security;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.gagauz.tapestry.security.api.AccessAttribute;
import org.gagauz.tapestry.security.api.AccessAttributeChecker;

import java.util.Map;

public class AccessAttributesCheckerContainer {
    @Inject
    private Map<Class, AccessAttributeChecker> accessAttributeCheckersMap;

    public void checkAttribute(AccessAttribute accessAttribute) throws AccessDeniedException {
        AccessAttributeChecker checker = accessAttributeCheckersMap.get(accessAttribute.getClass());
        if (null == checker) {
            throw new IllegalStateException("No access checker for AccessAttribute of class " + accessAttribute.getClass());
        }
        checker.check(accessAttribute);
    }
}
