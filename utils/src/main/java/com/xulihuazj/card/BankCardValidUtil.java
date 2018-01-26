package com.xulihuazj.card;/*
 * CardValidUtil.java 1.0.0 2017年7月19日 下午1:43:44
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017年7月19日 下午1:43:44 created by xulihua
 */

/**
 * 信用卡验证工具类
 *
 * @author xulihua
 */
public class BankCardValidUtil {


    /**
     * Luhn算法 校验银行卡卡号
     *
     * @param cardNo
     * @return
     */
    public static boolean luhnUtil(String cardNo) {

        if (cardNo.length() < 9 || cardNo.length() > 12) {
            return false;
        }
        char bit = getBankCardCheckCode(cardNo.substring(0, cardNo.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardNo.charAt(cardNo.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    private static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0 || !nonCheckCodeCardId.matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

    public static boolean matchLuhn(String cardNo) {
        if (cardNo.length() < 15 || cardNo.length() > 19)
            return false;
        Integer[] cardNoArr = new Integer[cardNo.length()];
        for (int i = 0; i < cardNoArr.length; i++) {
            cardNoArr[i] = Integer.valueOf(String.valueOf(cardNo.charAt(i)));
        }
        for (int i = cardNoArr.length - 2; i >= 0; i -= 2) {
            cardNoArr[i] <<= 1;
            cardNoArr[i] = cardNoArr[i] / 10 + cardNoArr[i] % 10;
        }
        int sum = 0;
        for (Integer aCardNoArr : cardNoArr) {
            sum += aCardNoArr;
        }
        return sum % 10 == 0;
    }

}

