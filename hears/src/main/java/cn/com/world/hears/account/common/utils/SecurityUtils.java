package cn.com.world.hears.account.common.utils;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

/**
 * 加密解密相关
 * @author Administrator
 *
 */
public class SecurityUtils {
    
    private static final String HMAC_SHA1 = "HmacSHA1";  

    /**
     * MD5加密
     * @param s 原始数据
     * @return 加密后
     */
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes("UTF-8");
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	/**
	 * SHA-1加密
	 * @param sourceStr
	 * @return
	 */
    public static final String sha1(String sourceStr) {
        String signature = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(sourceStr.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return signature;
    }
    
    public static final String hmacSha1(String key, String data) {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1);  
        Mac mac;
        try {
            mac = Mac.getInstance(HMAC_SHA1);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());  
            return byteToHex(rawHmac);  
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static final byte[] byteHmacSha1(String key, String data) {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1);  
        Mac mac;
        try {
            mac = Mac.getInstance(HMAC_SHA1);
            mac.init(signingKey);
            return mac.doFinal(data.getBytes());  
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    
	 public static void main(String[] args) {
	        System.out.println(SecurityUtils.MD5("20121221"));
	        System.out.println(SecurityUtils.MD5("加密"));
	    }
}

