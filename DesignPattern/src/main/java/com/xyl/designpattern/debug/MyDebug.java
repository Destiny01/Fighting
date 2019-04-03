package com.xyl.designpattern.debug;

/**
 * @author xyl on 2019/4/3.
 */
public class MyDebug {
    public static void f1() {
        System.out.println("one");
        System.out.println("two");
        System.out.println("three");
    }

    public static void f2() {
        for (int c = 0; c < 128; c++) {
            if (Character.isLowerCase(c)) {
                System.out.println("value: " + (int) c + " character: " + c);
            }
        }
        f1();
    }

    public static void main(String[] args) {
        f1();
        f2();
    }
}
