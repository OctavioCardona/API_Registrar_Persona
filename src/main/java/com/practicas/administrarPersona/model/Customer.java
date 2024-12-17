package com.practicas.administrarPersona.model;

import java.time.LocalDate;

/**
 * Class to create customer.
 */
public class Customer {
    /**
     * Name of the customer
     */
    private String name;

    /**
     * Age of customer.
     */
    private Integer age;

    /**
     * Birthday date of customer.
     */
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

    /**
     * Method to override the response of toString method
     *
     * @return name,age,birthday.
     */
    @Override
    public String toString() {
        return name + "," + age + ","+ birthday + System.lineSeparator();
    }
}
