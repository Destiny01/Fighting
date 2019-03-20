package com.xyl.designpattern.Clone;

import java.util.ArrayList;

/**
 * 原型模式
 * 用原型实例指定创建对象的种类，并通过拷贝这些原型创建新的对象。
 * 1、实现Cloneable接口
 * 2、重写Object的clone方法
 * 3、实现clone方法中的拷贝逻辑
 * 注意点：浅拷贝和深拷贝
 * 只进行了浅拷贝，也就是只拷贝了引用，最终两个对象指向的引用是同一个，一个发生变化另一个也会发生变换，显然解决方法就是使用深拷贝。
 *
 * @author xyl on 2019/3/20.
 */
public class Person implements Cloneable {
    private String name;
    private int age;
    private double height;
    private double weight;
    private ArrayList<String> hobbies = new ArrayList<String>();

    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }

    @Override
    protected Object clone() {
        Person person = null;
        try {
            person = (Person) super.clone();
            person.name = this.name;
            person.weight = this.weight;
            person.height = this.height;
            person.age = this.age;
            //浅拷贝
            //person.hobbies = this.hobbies;
            //深拷贝
            person.hobbies = (ArrayList<String>) this.hobbies.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return person;
    }
}
