package org.gagauz.tapestry.security;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityException.
 */
public class SecurityException extends RuntimeException {

    /** The need roles. */
    private String[] needRoles;

    /**
     * Instantiates a new security exception.
     *
     * @param needRoles the need roles
     */
    public SecurityException(String[] needRoles) {
        this.needRoles = needRoles;
    }

    /**
     * Gets the need roles.
     *
     * @return the need roles
     */
    public String[] getNeedRoles() {
        return needRoles;
    }

    /**
     * Sets the need roles.
     *
     * @param needRoles the new need roles
     */
    public void setNeedRoles(String[] needRoles) {
        this.needRoles = needRoles;
    }

}
