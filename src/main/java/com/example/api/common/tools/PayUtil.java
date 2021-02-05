package com.example.api.common.tools;

import com.example.api.common.exception.ResultErrException;
import java.security.Key;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PayUtil {

  private static Logger logger = LoggerFactory.getLogger(PayUtil.class);

  // 加解密统一使用的编码方式
  private final static String encoding = "utf-8";
  private final static String default_key = "101193d7181cc88340ae5b2b17bba8a1";
  private final static String default_iv = "abcdefgh";

  /**
   * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
   *
   * @param data 待签名数据
   * @param appSecret API密钥
   * @return 签名
   */
  public static String generateSignature(Map<String, String> data, String appSecret) {

    return generateSignature(new TreeMap<>(data), appSecret);
  }

  /**
   * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
   *
   * @param data 待签名数据
   * @param appSecret API密钥
   * @return 签名
   */
  public static String generateSignature(final TreeMap<String, String> data, String appSecret) {
    StringBuilder sb = new StringBuilder();

    Set<String> keySet = data.keySet();
    Iterator<String> iter = keySet.iterator();
    while (iter.hasNext()) {
      String key = iter.next();
      if ("sign".equals(key)) {
        continue;
      }
      if (key.trim().length() > 0 && data.get(key).trim().length() > 0) { // 参数值为空，则不参与签名
        sb.append(key).append("=").append(data.get(key).trim()).append("&");
      }
    }
    sb.append("key=").append(appSecret);
    logger.info("generateSignature ==> " + sb.toString());
    return PayUtil.HMACSHA256(sb.toString(), appSecret);
  }

  /**
   * 生成 HMACSHA256
   *
   * @param data 待处理数据
   * @param appSecret 密钥
   * @return 加密结果
   */
  public static String HMACSHA256(String data, String appSecret) {

    Mac sha256_HMAC = null;
    StringBuilder sb = new StringBuilder();

    try {
      sha256_HMAC = Mac.getInstance("HmacSHA256");
      SecretKeySpec secret_key = new SecretKeySpec(appSecret.getBytes("UTF-8"), "HmacSHA256");
      sha256_HMAC.init(secret_key);
      byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));

      for (byte item : array) {
        sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return sb.toString().toUpperCase();
  }

  /**
   * 3DES解密
   *
   * @param data 数据
   * @param iv 向量
   * @param secretKey 密钥
   */
  public static String desDecode(String data, String secretKey, String iv) {
    try {
      DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
      SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
      Key deskey = keyfactory.generateSecret(spec);
      Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
      IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
      cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
      byte[] decryptData = cipher.doFinal(Base64.getUrlDecoder().decode(data.getBytes()));
      return new String(decryptData, encoding);
    } catch (Exception e) {
      logger.info("des decode error encryptText:{};iv:{};secretKey:{}", e, data, iv,
          secretKey);
      return "";
    }
  }

  /**
   * 3DES加密
   *
   * @param data 数据
   * @param secretKey 密钥
   * @param iv 向量
   */
  public static String desEncode(String data, String secretKey, String iv) {
    byte[] encryptData;
    try {
      DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
      SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
      Key deskey = keyfactory.generateSecret(spec);
      Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
      IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
      cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
      encryptData = cipher.doFinal(data.getBytes(encoding));
      return Base64.getUrlEncoder().encodeToString(encryptData);
    } catch (Exception e) {
      logger.info("des encode error", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * 3DES解密
   *
   * @param data 数据
   * @param secretKey 密钥
   */
  public static String desDecode(String data, String secretKey) {
    return desDecode(data, secretKey, default_iv);
  }

  /**
   * 3DES加密
   *
   * @param data 数据
   * @param secretKey 密钥
   */
  public static String desEncode(String data, String secretKey) {
    return desEncode(data, secretKey, default_iv);
  }

  /**
   * 3DES解密
   *
   * @param data 数据
   */
  public static String desDecode(String data) {
    return desDecode(data, default_key, default_iv);
  }

  /**
   * 3DES加密
   *
   * @param data 数据
   * @return MD5Utils.MD5(data)
   */
  public static String desEncode(String data) {
    return desEncode(data, default_key, default_iv);
  }


  /**
   * 获取支付名称
   */
  public static String getPayTypeName(short rechargeType) {
    switch (rechargeType) {
      case 1:
        return "支付宝";
      case 2:
        return "微信";
      case 3:
        return "银行卡";
      case 4:
        return "数字货币";
      default:
        throw new ResultErrException("不支持的收款方式");
    }
  }

  public static Integer getPayType(String rechargeTypeName) {
    switch (rechargeTypeName) {
      case "支付宝":
        return 1;
      case "微信":
        return 2;
      case "银行卡":
        return 3;
      case "数字货币":
        return 4;
      default:
        throw new ResultErrException("不支持的收款方式");
    }
  }

  public static void main(String[] args) {
    Map<String, String> p = new HashMap<>();

    p.put("zcddh", "12321");
    p.put("zcfee", "100");
    p.put("notifyurl", "http://pay.zcaibao.com/pay/notify.html");
    p.put("backurl", "http://pay.zcaibao.com/success.html");
    p.put("zcname", "充值");
    p.put("zcdesc", "充值");
    p.put("number_id", "616");
    p.put("geway_code", "1");
    p.put("time", "2019-11-07 21:17:20");
    p.put("zcip", "127.0.0.1");
    p.put("version", "1.1");
    p.put("sign", "1");
    p.put("smstyle", "1");
    System.out.println(generateSignature(p, "7P2VVRMCB13A24TPC2WW1QRG3GCSSE1U9CTL2PXNSHHJ0Z07A2B7WBYCP33C9T7OXIENG8JLDT9854ONDZSO0QRR703GTKFORI9EDBI92OA3GSD78U9B2WZ9ORQ7LGSL"));
    ;
  }
}
