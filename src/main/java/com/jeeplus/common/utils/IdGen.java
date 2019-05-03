package com.jeeplus.common.utils;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.UUID;

public class IdGen {
    private static SecureRandom random = new SecureRandom();

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    /**
     * 基于Base62编码的SecureRandom随机生成bytes.
     */
    public static String randomBase62(int length) {
        byte[] randomBytes = new byte[length];
        random.nextBytes(randomBytes);
        return Encodes.encodeBase62(randomBytes);
    }


//    @Override
//    public Serializable generateId(Session session) {
//        return IdGen.uuid();
//    }

    public static void main(String[] args) {
		/*System.out.println(IdGen.uuid());
		System.out.println(IdGen.uuid().length());
		for (int i=0; i<1000; i++){
			System.out.println(IdGen.randomLong() + "  " + IdGen.randomBase62(5));
		}*/
        System.out.println(getOrderNum("MI"));

    }

    /**
     * 訂單號生成工具
     * @author Allan
     */
    public static String getOrderNum(String type){
        int hashCodeV = ((System.currentTimeMillis() + "").substring(1) +
                (System.nanoTime() + "").substring(7, 10).hashCode()).hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        String orderId=type + String.format("%013d", hashCodeV);
        // System.out.println(orderId);
        return orderId;
    }
}
