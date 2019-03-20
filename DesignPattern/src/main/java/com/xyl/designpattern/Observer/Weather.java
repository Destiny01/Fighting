package com.xyl.designpattern.Observer;

/**
 * 观察者模式
 * 定义对象间的一种一对多(包含一对一)的依赖关系，当一个对象的状态发送改变时，所有依赖于它的对象都能得到通知并被自动更新
 *
 * @author xyl on 2019/3/20.
 */
public class Weather {
    private String description;

    public Weather(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "description='" + description + '\'' +
                '}';
    }
}
