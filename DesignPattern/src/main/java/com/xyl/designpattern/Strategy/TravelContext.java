package com.xyl.designpattern.Strategy;

/**
 * @author xyl on 2019/3/20.
 */
public class TravelContext {
    Strategy strategy;

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void travel() {
        if (strategy != null) {
            strategy.travel();
        }
    }
}
