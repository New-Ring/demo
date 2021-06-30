package com.mengma.javase.abstracttest;

/**
 * @author fankai
 * @date 2021年06月30日 9:33
 */
public class Test01 {
    public static void main(String[] args) {
        TestAbstract t1 = new TestAbstractImpl01();
        t1.getFish();

        System.out.println("-------------------");
        TestAbstract t2 = new TestAbstractImpl02();
        t2.getFish();
    }
}
