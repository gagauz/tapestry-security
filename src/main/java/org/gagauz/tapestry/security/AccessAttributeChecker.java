package org.gagauz.tapestry.security;

import org.gagauz.tapestry.security.api.AccessAttribute;

import org.gagauz.tapestry.security.api.AccessAttributeCreatorChecker;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.plastic.AnnotationAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AccessAttributeChecker {
    @Inject
    private Map<Class, AccessAttributeCreatorChecker> annotationCheckersMap;

    public List<AccessAttribute> getAccessAttribute(AnnotationAccess invocationObject) {
        List<AccessAttribute> result = new ArrayList<AccessAttribute>();
        for (final Entry<Class, AccessAttributeCreatorChecker> entry : annotationCheckersMap.entrySet()) {
            AccessAttribute attribute = entry.getValue().create(invocationObject);
            if (null != attribute) {
                result.add(attribute);
            }
        }
        return result;
    }

    public void checkAttribute(AccessAttribute accessAttribute) throws AccessDeniedException {
        AccessAttributeCreatorChecker checker = annotationCheckersMap.get(accessAttribute.getClass());
        if (null == checker) {
            throw new IllegalStateException("No access checker for AccessAttribute of " + accessAttribute.getClass());
        }
        checker.check(accessAttribute);

    }

}
