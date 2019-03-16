package com.centyun.core.util.encode;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密算法工具类
 * @author yinww
 *
 */

public class AesCryptUtils {

    private static final AesCryptUtils instance = new AesCryptUtils();
    private static final String SALT = "Ct2y!6H8K";

    private AesCryptUtils() {
    }

    public static AesCryptUtils getInstance() {
        return instance;
    }

    private Key initKeyForAes(String key) throws NoSuchAlgorithmException {
        if (null == key || key.length() == 0) {
            throw new NullPointerException("key not is null");
        }
        SecretKeySpec key2 = null;
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes());
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            key2 = new SecretKeySpec(enCodeFormat, "AES");
        } catch (NoSuchAlgorithmException ex) {
            throw new NoSuchAlgorithmException();
        }
        return key2;

    }

    /**
     * AES加密算法，不受密钥长度限制
     * 
     * @param content
     * @param key
     * @return
     */
    public String encryptAes(String content, String key) {
        try {
            SecretKeySpec secretKey = (SecretKeySpec) initKeyForAes(key);
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return asHex(result); // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String encryptAes(String content) {
        return encryptAes(content, SALT);
    }

    /**
     * aes解密算法，不受密钥长度限制
     * 
     * @param content
     * @param key
     * @return
     */
    public String decryptAes(String content, String key) {
        try {
            SecretKeySpec secretKey = (SecretKeySpec) initKeyForAes(key);
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, secretKey);// 初始化
            byte[] result = cipher.doFinal(asBytes(content));
            return new String(result); // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decryptAes(String content) {
        return decryptAes(content, SALT);
    }

    /**
     * 将2进制数值转换为16进制字符串
     * 
     * @param buf
     * @return
     */
    public String asHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;
        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10)
                strbuf.append("0");
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }

    /**
     * 将16进制转换
     * 
     * @param hexStr
     * @return
     */
    public byte[] asBytes(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    
    public static void main(String[] args) {
        String url = "https://docs.docker.com/engine/reference/commandline/docker/";
        String encode = getInstance().encryptAes(url);
        String decryptAes = getInstance().decryptAes(encode);
        System.out.println(encode);
        System.out.println(decryptAes);
    }

}
