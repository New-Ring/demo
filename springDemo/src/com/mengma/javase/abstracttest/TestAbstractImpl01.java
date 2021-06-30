package com.mengma.javase.abstracttest;

/**
 * @author fankai
 * @date 2021年06月30日 9:33
 */
public class TestAbstractImpl01 extends TestAbstract{

    @Override
    protected boolean waitFish() {
        System.out.println("一个小时满了");
        return true;
    }
}
