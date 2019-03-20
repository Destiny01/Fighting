package com.xyl.designpattern.Strategy;

/**
 * @author xyl on 2019/3/20.
 */
public class BikeStrategy implements Strategy {
    @Override
    public void travel() {
        System.out.println("bike");
    }
}
