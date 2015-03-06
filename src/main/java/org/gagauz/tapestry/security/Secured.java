package org.gagauz.tapestry.security;

import java.lang.annotation.*;

// TODO: Auto-generated Javadoc
/**
 * The Interface Secured.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Secured {

    /**
     * Array of roles
     *
     * @return the string[]
     */
    String[] value() default {};
}
