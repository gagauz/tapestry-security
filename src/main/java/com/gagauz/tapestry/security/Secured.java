package com.gagauz.tapestry.security;

import com.gagauz.tapestry.security.api.Role;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Secured {
    Role[] value() default {};
}
