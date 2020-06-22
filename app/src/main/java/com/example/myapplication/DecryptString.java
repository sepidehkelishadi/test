package com.example.myapplication;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class DecryptString {
    public static String decipher(String str) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec("Uh6QgtcmzN6UTdsmxtKvSEtgnpHvpfLQ".toCharArray(), "Uh6QgtcmzN6UTdsmxtKvSEtgnpHvpfLQ".getBytes(), 128, 256)).getEncoded(), "AES");
        Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
        instance.init(2, secretKeySpec);
        return new String(instance.doFinal(toByte(str)));
    }

    public static String decryptString(String str) {
        try {
            return decipher(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] decryptStringArray(String[] strArr) {
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr2[i] = decryptString(strArr[i]);
        }
        return strArr2;
    }

    private static byte[] toByte(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = 2 * i;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }
}