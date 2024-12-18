package com.practicas.administrarPersona.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Class to create customer.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
