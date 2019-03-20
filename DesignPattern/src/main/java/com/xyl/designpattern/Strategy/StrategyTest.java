package com.xyl.designpattern.Strategy;

/**
 * 应用了策略模式后，如果我们想增加新的出行方式，完全不必要修改现有的类，我们只需要实现策略接口即可，这就是面向对象中的对扩展开放准则。假设现在我们增加了一种自行车出行的方式。只需新增一个类即可。
 *
 * @author xyl on 2019/3/20.
 */
public class StrategyTest {
    public static void main(String[] args) {
        TravelContext travelContext = new TravelContext();
        travelContext.setStrategy(new PlaneStrategy());
        travelContext.travel();
        travelContext.setStrategy(new WalkStrategy());
        travelContext.travel();
        travelContext.setStrategy(new SubwayStrategy());
        travelContext.travel();
        travelContext.setStrategy(new BikeStrategy());
        travelContext.travel();
    }
}
