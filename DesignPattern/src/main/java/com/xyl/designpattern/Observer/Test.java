package com.xyl.designpattern.Observer;

/**
 * @author xyl on 2019/3/20.
 */
public class Test {
    public static void main(String[] args) {
        Observable<Weather> observable = new Observable<>();
        Observer<Weather> observer1 = new Observer<Weather>() {
            @Override
            public void onUpdate(Observable<Weather> observable, Weather data) {
                System.out.println("observer1:" + data.toString());
            }
        };
        Observer<Weather> observer2 = new Observer<Weather>() {
            @Override
            public void onUpdate(Observable<Weather> observable, Weather data) {
                System.out.println("observer2:" + data.toString());
            }
        };

        observable.register(observer1);
        observable.register(observer2);

        Weather weather = new Weather("qingzhuanduoyun");
        observable.notifyObservers(weather);

        Weather weather1 = new Weather("duoyuanzhuanyin");
        observable.notifyObservers(weather1);

        observable.unregister(observer1);

        Weather weather2 = new Weather("taifeng");
        observable.notifyObservers(weather2);

    }
}
