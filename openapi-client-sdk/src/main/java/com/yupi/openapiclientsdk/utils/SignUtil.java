package com.yupi.openapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 *  签名工具
 * @author zuodong
 * @create 2023-08-05 23:32
 */

public class SignUtil {
    /**
     * MD5 摘要算法
     * @param body
     * @param secretKey
     * @return digestHex
     */
    public static String genSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String content = body.toString() + "." + secretKey;
        String digestHex = md5.digestHex(content);
        return digestHex;
    }
}
