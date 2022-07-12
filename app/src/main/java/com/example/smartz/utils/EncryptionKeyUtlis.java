package com.example.smartz.utils;

import java.util.Arrays;

public class EncryptionKeyUtlis {

    private static final int SHIFT_DIGIT = 9;

    // Encrypts/ decrypts text using a shift od s
    private static String ubfuscate(String text, int shift)
    {
        StringBuffer result= new StringBuffer();

        for (int i=0; i<text.length(); i++)
        {
            if (Character.isUpperCase(text.charAt(i)))
            {
                char ch = (char)(((int)text.charAt(i) +
                    shift - 65) % 26 + 65);
                result.append(ch);
            }
            else
            {
                char ch = (char)(((int)text.charAt(i) +
                    shift - 97) % 26 + 97);
                result.append(ch);
            }
        }
        return result.toString();
    }

    // Encrypts text using a shift od s
    private static String decrypt()
    {
        return ubfuscate( "Cqrbrbjbjuccxdkodbljcnbcarwp",26-SHIFT_DIGIT);
    }

    public static String encode(String s, String key) {
        byte[] arr = xorWithKey(s.getBytes(), key.getBytes());
        return new String(xorWithKey(arr,key.getBytes()));
    }

    public static String getString(byte[] arr) {
        return new String(xorWithKey(arr,decrypt().getBytes()));
    }

    private static byte[] xorWithKey(byte[] a, byte[] key) {
        byte[] out = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ key[i%key.length]);
        }
        return out;
    }

}