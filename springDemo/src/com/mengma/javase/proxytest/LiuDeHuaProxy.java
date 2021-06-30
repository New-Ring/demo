package com.mengma.javase.proxytest;

/**
 * @author fankai
 * @date 2021年06月30日 10:11
 */
public class LiuDeHuaProxy implements Star{

    private LiuDeHua LiuDeHua;

    public LiuDeHuaProxy(LiuDeHua LiuDeHua){
        this.LiuDeHua = LiuDeHua;
    }

    @Override
    public void sign() {
        work();
        LiuDeHua.sign();
        System.out.println("代理不回唱歌");
    }

    private void work() {
        System.out.println("我是刘德华工作人员，保安");
    }
}
