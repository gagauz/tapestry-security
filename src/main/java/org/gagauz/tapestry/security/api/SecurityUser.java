package org.gagauz.tapestry.security.api;

// TODO: Auto-generated Javadoc
/**
 * The Interface SecurityUser.
 */
public interface SecurityUser {
    
    /**
     * Check roles.
     *
     * @param needRoles the need roles
     * @return true, if successful
     */
    boolean checkRoles(String[] needRoles);
}
