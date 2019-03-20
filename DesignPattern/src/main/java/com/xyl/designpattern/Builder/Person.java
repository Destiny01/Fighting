package com.xyl.designpattern.Builder;

/**
 * 此外Builder类中的成员函数返回Builder对象自身的另一个作用就是让它支持链式调用，使代码可读性大大增强。
 * Builder使用
 * 1、定义一个静态内部类Builder，内部的成员变量和外部类一样
 * 2、Builder类通过一系列的方法用于成员变量的赋值，并返回当前对象本身（this）
 * 3、Builder类提供一个build方法或者create方法用于创建对应的外部类，该方法内部调用了外部类的一个私有构造函数，该构造函数的参数就是内部类Builder
 * 4、外部类提供一个私有构造函数供内部类调用，在该构造函数中完成成员变量的赋值，取值为Builder对象中对应的值
 *
 * @author xyl on 2019/3/20.
 */
public class Person {
    private String name;
    private int age;
    private double height;
    private double weight;

    /**
     * 4、必须是私有构造
     *
     * @param builder
     */
    private Person(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.height = builder.height;
        this.weight = builder.weight;
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

    //1、定义一个静态内部类Builder，内部的成员变量和外部类一样
    public static class Builder {
        private String name;
        private int age;
        private double height;
        private double weight;

        //2、Builder类通过一系列的方法用于成员变量的赋值，并返回当前对象本身（this）
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder height(double height) {
            this.height = height;
            return this;
        }

        public Builder weight(double weight) {
            this.weight = weight;
            return this;
        }

        /**
         * 3、Builder类提供一个build方法或者create方法用于创建对应的外部类
         *
         * @return
         */
        public Person build() {
            return new Person(this);
        }
    }
}
