package com.vilderlee.common.util;

import com.vilderlee.common.environment.Environment;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/11/30      Create this file
 * </pre>
 */
public class TripleDESUtil {

    private static final byte[] key = {97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120};
    private static final byte[] keyiv = { 1, 2, 3, 4, 5, 6, 7, 8 };
    private static Key deskey;
    private static DESedeKeySpec spec;
    private static SecretKeyFactory keyfactory;
    private static Cipher cipher;
    private static IvParameterSpec ips;

    static {
        try {
            spec = new DESedeKeySpec(key);
            keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);
            cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
            ips = new IvParameterSpec(keyiv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * CBC加密
     *
     * @param plain 明文
     * @return Base64编码的密文
     * @throws Exception
     */
    public static String des3EncodeCBC(String plain) throws Exception {

        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(plain.getBytes());
        return new BASE64Encoder().encode(bOut);
    }

    /**
     * CBC解密
     *
     * @param singnature Base64编码的密文
     * @return 明文
     * @throws Exception
     */
    public static String des3DecodeCBC(String singnature) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        byte[] bOut = cipher.doFinal(new BASE64Decoder().decodeBuffer(singnature));
        return new String(bOut, Environment.ENVIRONMENT_ENCODING);
    }

    public static void main(String[] args) throws Exception {
        String a = "springboot";

        System.out.println(des3EncodeCBC(a));
        System.out.println(des3DecodeCBC("8gEQVysSYmR0dFfAsxkNSw=="));
    }
}
