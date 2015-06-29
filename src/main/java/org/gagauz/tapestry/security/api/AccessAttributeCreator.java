package org.gagauz.tapestry.security.api;

import org.apache.tapestry5.plastic.PlasticClass;
import org.apache.tapestry5.plastic.PlasticMethod;

public interface AccessAttributeCreator {
    AccessAttribute create(PlasticClass plasticClass, PlasticMethod plasticMethod);
}
