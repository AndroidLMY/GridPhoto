package com.lmy.gridphotolibrary.utils;


import java.util.Random;

public class UUIDUtils {
    public static String genRandomNum() {
        byte maxNum = 36;
        int count = 0;
        char[] str = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < 8) {
            int i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                ++count;
            }
        }
        return pwd.toString();
    }

    public static String getRandomNumNumber() {
        byte maxNum = 36;
        int count = 0;
        char[] str = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < 10) {
            int i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                ++count;
            }
        }
        return pwd.toString();
    }
}
