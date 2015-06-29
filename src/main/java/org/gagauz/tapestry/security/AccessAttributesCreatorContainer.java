package org.gagauz.tapestry.security;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.plastic.PlasticClass;
import org.apache.tapestry5.plastic.PlasticMethod;
import org.gagauz.tapestry.security.api.AccessAttribute;
import org.gagauz.tapestry.security.api.AccessAttributeCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AccessAttributesCreatorContainer {
    @Inject
    private Map<Class, AccessAttributeCreator> accessAttributeCheckersMap;

    public List<AccessAttribute> createAccessAttributes(PlasticClass plasticClass, PlasticMethod plasticMethod) {
        List<AccessAttribute> result = new ArrayList<AccessAttribute>();
        for (final Entry<Class, AccessAttributeCreator> entry : accessAttributeCheckersMap.entrySet()) {
            AccessAttribute attribute = entry.getValue().create(plasticClass, plasticMethod);
            if (null != attribute) {
                result.add(attribute);
            }
        }
        return result;
    }

}
