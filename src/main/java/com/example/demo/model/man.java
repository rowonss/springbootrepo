package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString

public class man {

    int age;

    String name;

    public man(String name, int age){
        this.name = name;
        this.age = age;
    }

}
