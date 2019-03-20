package com.xyl.designpattern.Singleton;

import java.util.concurrent.CountDownLatch;

/**
 * 单例模式：确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例。
 * 优点：
 * 1、对于那些比较耗内存的类，只实例化一次可以大大提高性能，尤其是在移动开发中。
 * 2、保持程序运行的时候该中始终只有一个实例存在内存中
 * <p>
 * <p>
 * 要保证单例，需要做一下几步
 * <p>
 * 1、必须防止外部可以调用构造函数进行实例化，因此构造函数必须私有化。
 * 2、必须定义一个静态函数获得该单例
 * 3、单例使用volatile修饰
 * 4、使用synchronized 进行同步处理，并且双重判断是否为null，我们看到synchronized (Singleton.class)里面又进行了是否为null的判断，
 * 这是因为一个线程进入了该代码，如果另一个线程在等待，这时候前一个线程创建了一个实例出来完毕后，另一个线程获得锁进入该同步代码，实例已经存在，没必要再次创建，因此这个判断是否是null还是必须的。
 *
 * @author xyl on 2019/3/20.
 */
public class Singleton {
    private static volatile Singleton instance = null;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        //单例的并发测试，可以使用CountDownLatch，使用await()等待锁释放，使用countDown()释放锁从而达到并发的效果
        final CountDownLatch latch = new CountDownLatch(1);
        int threadCount = 1000;
        for (int i = 0; i < threadCount; i++) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Singleton.getInstance().hashCode());
                }
            }.start();
        }
        latch.countDown();
    }
}
