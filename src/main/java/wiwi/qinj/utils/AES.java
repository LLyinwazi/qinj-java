package wiwi.qinj.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Wisya
 * @description
 * @buildTime 2018-10-11 18:05
 */
public class AES {

    public static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";
    private final static String algorithmName = "AES";

    public static void main(String[] args) {
//		String s = "hello,您好";
        String s = "uhfugqpowzffhDWQc20z-pUadl&2d1@Dkzqlo/adfwQQlo+AdjcdUyakLjFM$YUaxOasGDBeyTail5Is9Ush+0aXPiU";

        System.out.println("s:" + s);

        String key = "abcdef1234567890";

        String s1 = AES.encrypt(s, key);
        System.out.println("s1:" + s1);

        System.out.println("s2:" + AES.decrypt(s1, key));

    }

    /**
     * 方法说明：AES加密.
     * @param data 待加密数据
     * @param key  加密密钥
     * @return the string
     * @author sunchao
     * <p>
     * CreateDate: 2018.4.09
     */
    public static String encrypt(String data, String key) {
        byte[] valueByte = encrypt(data.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(valueByte));
    }

    /**
     * 方法说明：AES解密.
     * @param data the data
     * @param key  the key
     * @return the string
     * @author sunchao
     * <p>
     * CreateDate: 2018.4.09
     */
    public static String decrypt(String data, String key) {
        byte[] originalData = Base64.getDecoder().decode(data.getBytes());
        byte[] valueByte = decrypt(originalData, key.getBytes(StandardCharsets.UTF_8));
        return new String(valueByte, StandardCharsets.UTF_8);
    }

    /**
     * 方法说明：AES加密.
     * @param data 待加密数据
     * @param key  加密密钥
     * @return the byte[]
     * @author sunchao
     * <p>
     * CreateDate: 2018.4.09
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, algorithmName);
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, algorithmName);
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);    // 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, seckey);    // 初始化
            byte[] result = cipher.doFinal(data);
            return result; // 加密
        } catch (Exception e) {
            throw new RuntimeException("encrypt fail!", e);
        }
    }

    /**
     * 方法说明：AES解密.
     * @param data 待解密数据
     * @param key  密钥
     * @return the byte[]
     * @author sunchao
     * <p>
     * CreateDate: 2018.4.09
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, algorithmName);
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec seckey = new SecretKeySpec(enCodeFormat, algorithmName);
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);    // 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, seckey);    // 初始化
            byte[] result = cipher.doFinal(data);
            return result; // 加密
        } catch (Exception e) {
            throw new RuntimeException("decrypt fail!", e);
        }
    }


}
