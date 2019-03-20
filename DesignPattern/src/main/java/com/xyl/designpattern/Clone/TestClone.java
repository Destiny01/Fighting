package com.xyl.designpattern.Clone;

import java.util.ArrayList;

/**
 * @author xyl on 2019/3/20.
 */
public class TestClone {
    public static void main(String[] args) {
        Person p = new Person();
        p.setAge(18);
        p.setName("张三");
        p.setHeight(178);
        p.setWeight(65);
        ArrayList<String> hobbies = new ArrayList<String>();
        hobbies.add("篮球");
        hobbies.add("编程");
        hobbies.add("长跑");
        p.setHobbies(hobbies);
        System.out.println(p);

        Person p1 = (Person) p.clone();
        System.out.println(p1);

        p1.setName("李四");
        p1.getHobbies().add("游泳");
        System.out.println(p);
        System.out.println(p1);
    }
}
