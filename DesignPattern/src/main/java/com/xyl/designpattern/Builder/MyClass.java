package com.xyl.designpattern.Builder;

public class MyClass {
    public static void main(String[] args) {
        Person person = new Person.Builder()
                .age(27)
                .name("ptg")
                .weight(100)
                .height(170)
                .build();
    }
}
