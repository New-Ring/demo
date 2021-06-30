package com.mengma.javase.abstracttest;

/**
 * 设计模式：模板模式---固定的套路调用固定的方法，如钓鱼案例
 * @author fankai
 * @date 2021年06月30日 9:32
 */

abstract class TestAbstract {
    public void getFish(){
        buyYuju(); //准备钓具
        seekLake(); //找鱼塘
        if(waitFish()){
            isFishsEat(); // 有鱼吃鱼
        }else{
            isNoFishGoHome(); //没鱼空军回家
        }
    }

    public void isNoFishGoHome(){
        System.out.println("我是空军");
    }

    public void isFishsEat(){
        System.out.println("吃鱼了");
    }

    protected abstract boolean waitFish();

    public void seekLake(){
        System.out.println("找鱼塘");
    }

    public void buyYuju(){
        System.out.println("买渔具");
    }
}
