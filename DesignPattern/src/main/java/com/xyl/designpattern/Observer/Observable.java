package com.xyl.designpattern.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 *
 * @author xyl on 2019/3/20.
 */
public class Observable<T> {
    List<Observer<T>> observers = new ArrayList<>();

    /**
     * 注册观察者
     *
     * @param observer
     */
    public void register(Observer<T> observer) {
        if (observer == null) {
            throw new NullPointerException("observer == null");
        }
        synchronized (this) {
            if (!observers.contains(observer)) {
                observers.add(observer);
            }
        }
    }

    /**
     * 取消订阅
     *
     * @param observer
     */
    public synchronized void unregister(Observer<T> observer) {
        observers.remove(observer);
    }

    /**
     * 通知观察者
     *
     * @param data
     */
    public void notifyObservers(T data) {
        for (Observer<T> observer : observers) {
            observer.onUpdate(this, data);
        }
    }
}
