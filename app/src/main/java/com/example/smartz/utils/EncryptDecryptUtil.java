package com.example.smartz.utils;

import android.text.TextUtils;
import android.util.Base64;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class EncryptDecryptUtil {
    private static final byte[] RSA = {31, 27, 8, 64, 90, 69, 85, 65, 42, 31, 21, 71, 92, 67, 86, 84};
    private static final byte[] AES_CBS_PKCS5Padding = {21, 45, 58, 92, 42, 49, 34, 92, 49, 39, 55, 39, 90, 37, 35, 34, 49, 58, 45, 38};
    private static final byte[] INITVECTOR = {6, 9, 31, 26, 59, 32, 40, 29, 8, 24, 34, 17, 12, 1, 13, 20};
    private static final String AES = "AES";



    private EncryptDecryptUtil(){

    }

    /**
     *  Encrypt string using AES algorithum
     * @param encryptValue
     * @return
     */
    public static String encrypt(final String encryptValue) {
        String encryptedText = null;
        try {
            // Create key and cipher
            final SecretKeySpec aesKey = new SecretKeySpec(EncryptionKeyUtlis
                       .getString(RSA).getBytes(), AES);

            final IvParameterSpec ivParameterSpec = new IvParameterSpec
                (EncryptionKeyUtlis.getString(INITVECTOR).getBytes( StandardCharsets.UTF_8 ));

            final Cipher cipher = Cipher.getInstance(EncryptionKeyUtlis.getString(AES_CBS_PKCS5Padding));
            //Encrypt data
           // cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivParameterSpec);
            final byte[] inputByte = encryptValue.getBytes();
            final byte[] encryptedName = Base64.encode(cipher.doFinal(inputByte), Base64.NO_WRAP);
            encryptedText = new String(encryptedName);
        } catch (final Exception e) {
        }
        return encryptedText;

    }

    /**
     * Encrypt string using AES algorithum
     * @param encryptValue
     * @return : string into byte
     */
    public static byte[] encryptInBytes(final String encryptValue) {
        byte[] encryptBytes = null;
        try {
            encryptBytes = encrypt( encryptValue ).getBytes();
        } catch (final Exception e) {
        }
        return  encryptBytes;
    }

    /**
     * Decrypt string using AES algorithum
     * @param decryptValue
     * @return
     */
    public static String decrypt(final String decryptValue) {
        String decryptText = null;
        if(TextUtils.isEmpty( decryptValue )){
            return  decryptText;
        }
        try {
            // Create key and cipher
            final SecretKeySpec aesKey = new SecretKeySpec(EncryptionKeyUtlis.getString(RSA).getBytes(), AES);
            final IvParameterSpec ivParameterSpec = new IvParameterSpec
                (EncryptionKeyUtlis.getString(INITVECTOR).getBytes(StandardCharsets.UTF_8 ));
            final Cipher cipher = Cipher.getInstance(EncryptionKeyUtlis.getString(AES_CBS_PKCS5Padding));
            //cipher.init(Cipher.DECRYPT_MODE, aesKey);
            cipher.init(Cipher.DECRYPT_MODE, aesKey, ivParameterSpec);
            final byte[] inputByte = decryptValue.getBytes();
            final byte[] decrypted = cipher.doFinal(Base64.decode(inputByte, Base64.DEFAULT));
            decryptText = new String(decrypted);
        } catch (final Exception e) {
        }
        return decryptText;
    }
}
