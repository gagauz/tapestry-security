package org.gagauz.tapestry.security.api;


// TODO: Auto-generated Javadoc
/**
 * The Interface SecurityUserProvider.
 */
public interface SecurityUserProvider {
    
    /**
     * Load by credentials.
     *
     * @param credentials the credentials
     * @return the security user
     */
    SecurityUser loadByCredentials(Credentials credentials);
}
