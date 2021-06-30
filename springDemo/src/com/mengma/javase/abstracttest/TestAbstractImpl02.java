package com.mengma.javase.abstracttest;

/**
 * @author fankai
 * @date 2021年06月30日 9:46
 */
public class TestAbstractImpl02 extends TestAbstract{

    /*public void isFishsEat() {
        System.out.println("我没调到鱼，但是我买鱼吃了");
    }*/

    public void isNoFishGoHome(){
        System.out.println("我没调到鱼，但是我买鱼吃了");
        super.isFishsEat();
//        this.isFishsEat();
    }

    @Override
    protected boolean waitFish() {
        System.out.println("我只等了一天一夜都没鱼");
        return false;
    }
}
