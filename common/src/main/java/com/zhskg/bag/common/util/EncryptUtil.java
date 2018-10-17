package com.zhskg.bag.common.util;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @Auther:jean
 * @Date:2018/9/5
 * @Descripsion
 */
public class EncryptUtil {

    public static String encrypMD5(String info) throws NoSuchAlgorithmException {
        //根据MD5算法生成MessageDigest对象
        if (info == null) return null;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] srcBytes = info.getBytes();
        //使用srcBytes更新摘要
        md5.update(srcBytes);
        //完成哈希计算，得到result
        byte[] resultBytes = md5.digest();
        return convertByteToHexString(resultBytes);
    }

    // 加密
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = Base64.getEncoder().encodeToString(b);
        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {

            try {
                b = Base64.getDecoder().decode(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String convertByteToHexString(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int x = 0; x < bytes.length; x++) {
            String temp = Integer.toHexString(bytes[x] & 0xff);
            if (temp.length() < 2) {
                stringBuffer.append("0").append(temp);
            } else {
                stringBuffer.append(temp);
            }

        }
        return stringBuffer.toString();
    }

    public static void main(String args[]) {


//        System.out.println(EncryptUtil.getBase64("zhsRedis@123"));
//        System.out.println(EncryptUtil.getFromBase64(EncryptUtil.getBase64("zhsRedis@123")));
        System.out.println(getFromBase64("emhzUmVkaXNAMTIz"));
    }
}
