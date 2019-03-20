package com.xyl.designpattern.Observer;

/**
 * 观察者
 *
 * @author xyl on 2019/3/20.
 */
public interface Observer<T> {
    void onUpdate(Observable<T> observable, T data);
}
