package com.centyun.core.util.encode;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.util.StringUtils;

/**
 * 用于用户密码的加密, 先对明文进行SHA256加密，然后encrypt加密
 * 因为encrypt加密是的混淆码是基于加密前的内容动态生成的,因此最终的密文不能直接解密
 * @author yinww
 *
 */

public class EncryptUtils {
    
    /**
     * 先SHA256加密, 再jasypt加密
     * @param text, 要加密的文本
     * @return
     */
    public static String encryptPwd(String text) {
        String sha256 = Sha256Util.getSha256(text);
        return encrypt(sha256);
    }
    
    /**
     * jasypt加密, 加密的秘钥由内容决定
     * @param text, 要加密的文本
     * @return
     */
    public static String encrypt(String text) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        String code = getObfuscationCode(text);
        encryptor.setPassword(code);
        return encryptor.encrypt(text);
    }
    
    /**
     * 验证原始内容和加密后的密码是否匹配
     * @param text, 经过sh256加密过后的内容
     * @param encryptCode, 最终加密的密文
     * @return
     */
    public static boolean valid(String text, String encryptCode) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        String code = getObfuscationCode(text);
        encryptor.setPassword(code);
        String decrypt = null;
        try {
            decrypt = encryptor.decrypt(encryptCode);
        } catch (Exception e) {
            return false;
        }
        return decrypt == null ? false : decrypt.equals(text);
    }
    
    /**
     * 根据text内容生成混淆码
     * @param text, 要获取混淆码的文本
     * @return
     */
    private static String getObfuscationCode(String text) {
        if(!StringUtils.isEmpty(text)) {
            String key = "centYun";
            int len = text.length();
            int mod = len % 2 == 0 ? len / 2 : len / 2 + 1;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i< mod; i++) {
                sb.append(text.charAt(i*2));
                if(sb.length() >= 6) {
                    return sb.toString();
                }
            }
            int n = 6 - sb.length();
            if(n > 0) {
                sb.append(key.substring(0, n));
            }
            return sb.toString();
        }
        return null;
    }

}
