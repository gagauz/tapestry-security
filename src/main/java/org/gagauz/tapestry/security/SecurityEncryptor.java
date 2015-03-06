package org.gagauz.tapestry.security;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.spec.KeySpec;
import java.util.Collection;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityEncryptor.
 */
public class SecurityEncryptor {
    
    /** The Constant JOIN_STR. */
    private static final char JOIN_STR = '\0';
    
    /** The Constant ALGORITHM. */
    private static final String ALGORITHM = "AES";
    
    /** The encrypt. */
    private final Cipher encrypt;
    
    /** The decrypt. */
    private final Cipher decrypt;
    
    /** The Constant CH. */
    private static final String CH = "latin1";

    /**
     * Instantiates a new security encryptor.
     *
     * @param passphrase the passphrase
     */
    public SecurityEncryptor(String passphrase) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), "sdfadsfds".getBytes(CH), 65536, 128);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey key = new SecretKeySpec(tmp.getEncoded(), ALGORITHM);
            encrypt = Cipher.getInstance(ALGORITHM);
            encrypt.init(Cipher.ENCRYPT_MODE, key);

            decrypt = Cipher.getInstance(ALGORITHM);
            decrypt.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Encrypt.
     *
     * @param valueToEnc the value to enc
     * @return the string
     */
    public String encrypt(String valueToEnc) {
        try {
            byte[] encValue = encrypt.doFinal(valueToEnc.getBytes(CH));
            System.out.println(Base64.encodeBase64String(encValue));
            return Base64.encodeBase64String(encValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Decrypt.
     *
     * @param encryptedValue the encrypted value
     * @return the string
     */
    public String decrypt(String encryptedValue) {
        try {
            byte[] decordedValue = Base64.decodeBase64(encryptedValue.getBytes(CH));
            byte[] decValue = decrypt.doFinal(decordedValue);
            return new String(decValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Encrypt array.
     *
     * @param strings the strings
     * @return the string
     */
    public String encryptArray(Collection<String> strings) {
        String joined = StringUtils.join(strings, JOIN_STR);
        return encrypt(joined);
    }

    /**
     * Encrypt array.
     *
     * @param strings the strings
     * @return the string
     */
    public String encryptArray(String... strings) {
        String joined = StringUtils.join(strings, JOIN_STR);
        return encrypt(joined);
    }

    /**
     * Decrypt array.
     *
     * @param string the string
     * @return the string[]
     */
    public String[] decryptArray(String string) {
        string = decrypt(string);
        return StringUtils.split(string, JOIN_STR);
    }

}
