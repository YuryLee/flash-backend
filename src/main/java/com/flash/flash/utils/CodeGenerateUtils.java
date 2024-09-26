package com.flash.flash.utils;

import java.util.Random;
import java.util.UUID;

/**
 * Description:
 *
 * @author Yury
 * @date 2024/07/11 12:27
 */

public class CodeGenerateUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    //定义一个方法返回一个随机验证码
    public static String creatCode(int n) {

        StringBuilder code = new StringBuilder();
        Random r = new Random();
        //2.在方法内部使用for循环生成指定位数的随机字符，并连接起来
        for (int i = 1; i <= n; i++) {
            //生成一个随机字符：大写 ，小写 ，数字（0  1  2）
            int type = r.nextInt(3);
            switch (type) {
                case 0:
                    char ch = (char) (r.nextInt(26) + 65);
                    code.append(ch);
                    break;
                case 1:
                    char ch1 = (char) (r.nextInt(26) + 97);
                    code.append(ch1);
                    break;
                case 2:
                    code.append(r.nextInt(10));
                    break;
            }

        }
        return code.toString();
    }
//    public static void main(String[] args) {
//
//        System.out.println(getUUID());
//        logger.debug("test");
//    }
}
