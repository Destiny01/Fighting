package com.xyl.designpattern.Strategy;

/**
 * @author xyl on 2019/3/20.
 */
public class WalkStrategy implements Strategy {
    @Override
    public void travel() {
        System.out.println("walk");
    }
}
