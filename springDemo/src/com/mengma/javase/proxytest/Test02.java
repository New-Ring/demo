package com.mengma.javase.proxytest;

/**
 * @author fankai
 * @date 2021年06月30日 10:14
 */
public class Test02 {
    public static void main(String[] args) {
        LiuDeHua star = new LiuDeHua();
        LiuDeHuaProxy liuDeHuaProxy = new LiuDeHuaProxy(star);
        liuDeHuaProxy.sign(); //静态代理，实际是刘德华唱歌
    }
}
