package com.fankai.controller;

/**
 * @author fankai
 * @date 2021年06月08日 11:07
 */
public class BYY_JXLC_Events {//extends ExecuteListener implements ExecuteListenerInterface

    public static void main(String[] args) {
        String str ="hello";
        change(str);
        System.out.println(str);
    }

    private static void change(String str) {
        str = "hhhh";
        System.out.println(str);
    }
}
