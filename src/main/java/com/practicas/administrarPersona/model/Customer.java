package com.practicas.administrarPersona.model;

import java.time.LocalDate;

public class Customer {
    private String name;
    private Integer age;
    private LocalDate birthday;

    public Customer (){}

    public Customer (String name, Integer age, LocalDate birthday){
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return name + "," + age + ","+ birthday + System.lineSeparator();
    }
}
