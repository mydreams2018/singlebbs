package cn.kungreat.singlebbs.secure;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

/*
        工作模式其实主要是针对分组密码.分组密码是将明文消息编码表示后的数字 简称明文数字序列
        划分成长度为n的组（可看成长度为n的矢量），每组分别在密钥的控制下变换成等长的输出数字（简称密文数字）序列。
        工作模式的出现主要基于下面原因：
        当需要加密的明文长度十分大(例如文件内容)，由于硬件或者性能原因需要分组加密。
        多次使用相同的密钥对多个分组加密，会引发许多安全问题。
        从本质上讲，工作模式是一项增强密码算法或者使算法适应具体应用的技术，例如将分组密码应用于数据块组成的序列或者数据流。目前主要包括下面五种由NIST定义的工作模式：

        模式	名称	描述	典型应用
        电子密码本(ECB)	Electronic CodeBook	用相同的密钥分别对明文分组独立加密	单个数据的安全传输(例如一个加密密钥)
        密码分组链接(CBC)	Cipher Block Chaining	加密算法的输入是上一个密文组合下一个明文组的异或	面向分组的通用传输或者认证
        密文反馈(CFB)	Cipher FeedBack	一次处理s位，上一块密文作为加密算法的输入，产生的伪随机数输出与明文异或作为下一单元的密文	面向分组的通用传输或者认证
        输出反馈(OFB)	Output FeedBack	与CFB类似，只是加密算法的输入是上一次加密的输出，并且使用整个分组	噪声信道上的数据流的传输(如卫星通信)
        计数器(CTR)	    Counter	每个明文分组都与一个经过加密的计数器相异或。对每个后续分组计数器递增	面向分组的通用传输或者用于高速需求
        上面五种工作模式可以用于3DES和AES在内的任何分组密码，至于选择哪一种工作模式需要结合实际情况分析。
        */

/*      NoPadding	不采用填充模式
        填充模式
        Padding指的是：块加密算法要求原文数据长度为固定块大小的整数倍，如果原文数据长度大于固定块大小，
        则需要在固定块填充数据直到整个块的数据是完整的。例如我们约定块的长度为128，
        但是需要加密的原文长度为129，那么需要分成两个加密块，第二个加密块需要填充127长度的数据
        ，填充模式决定怎么填充数据。
        */
public enum CipherUtils {

    DEFAULTA(new byte[]{-5, 58, -34, -105, -63, -109, 27, -52, -3, 20, 16, -97, 64, -47, 41, 19, 37, -20, 0, 52, -38, -105, 13, -88, 70, 101, 46, -27, 79, -1, -124, 55},
            "AES","swww.kungreat.cn");

    CipherUtils(byte[] bytes, String algorithmname, String pmspec){
        this.algorithmbytes = bytes;
        this.algorithmname = algorithmname;
        this.pmspec = pmspec;
    }
    //SecretKey算法的byte数组
    public byte[] algorithmbytes;
    //SecretKey 算法名称
    public String algorithmname;
    //初始化向量
    public String pmspec;
    /*
    加密算法	密匙字节长度	向量字节长度
    AES	    16/32	     16
    */


    /**
     * content: 加密内容
     * transformation  返回实施指定转换的对象。Cipher
     */
    public String encryptByIV(String content,String transformation) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKey secretKey = new SecretKeySpec(algorithmbytes,algorithmname);
        //此类指定初始化向量（IV）
        IvParameterSpec iv = new IvParameterSpec(pmspec.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE,secretKey,iv);
        byte[] encrypted = cipher.doFinal(content.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * content: 加密内容
     * transformation  返回实施指定转换的对象。Cipher
     */
    public String encryptNoIV(String content,String transformation) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKey secretKey = new SecretKeySpec(algorithmbytes,algorithmname);
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        byte[] encrypted = cipher.doFinal(content.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }


    /**
     * base64Content: 解密内容(base64编码格式)
     * transformation  返回实施指定转换的对象。Cipher
     */
    public String decryptByIV(String base64Content,String transformation) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKey secretKey = new SecretKeySpec(algorithmbytes,algorithmname);
        //此类指定初始化向量（IV）
        IvParameterSpec iv = new IvParameterSpec(pmspec.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] content = Base64.getDecoder().decode(base64Content);
        byte[] encrypted = cipher.doFinal(content);
        return new String(encrypted);
    }

    /**
     * base64Content: 解密内容(base64编码格式)
     * transformation  返回实施指定转换的对象。Cipher
     */
    public String decryptNoIV(String base64Content,String transformation) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKey secretKey = new SecretKeySpec(algorithmbytes,algorithmname);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] content = Base64.getDecoder().decode(base64Content);
        byte[] encrypted = cipher.doFinal(content);
        return new String(encrypted);
    }


    /** wrap方法的作用是把原始的密钥通过某种加密算法包装为加密后的密钥，这样就可以避免在传递密钥的时候泄漏了密钥的明文。
     * key  包装一个密钥
     * transformation  返回实施指定转换的对象。Cipher
     */
    public byte[] onWrap(Key key,String transformation) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKey secretKey = new SecretKeySpec(algorithmbytes,algorithmname);
        IvParameterSpec IV = new IvParameterSpec(pmspec.getBytes());
        cipher.init(Cipher.WRAP_MODE, secretKey,IV);
        return cipher.wrap(key);
    }
    /** 方法的作用是把包装(加密)后的密钥解包装为原始的密钥
     * transformation  返回实施指定转换的对象。Cipher
     * wrappedKey 要拆开包装的密钥。
     * wrappedKeyAlgorithm- 与包裹密钥相关的算法
     * wrappedKeyType- 包裹钥匙的类型。  Cipher.SECRET_KEY, PRIVATE_KEY,  PUBLIC_KEY.
     */
    public Key deWrap(String transformation, byte[] wrappedKey, String wrappedKeyAlgorithm, int wrappedKeyType) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKey secretKey = new SecretKeySpec(algorithmbytes,algorithmname);
        IvParameterSpec IV = new IvParameterSpec(pmspec.getBytes());
        cipher.init(Cipher.UNWRAP_MODE,secretKey,IV);
        return cipher.unwrap(wrappedKey,wrappedKeyAlgorithm,wrappedKeyType);
    }
}
