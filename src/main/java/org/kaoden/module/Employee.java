package org.kaoden.module;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Builder
public class Employee implements Comparable<Employee> {

    private String firstName;
    private String lastName;
    private String description;
    private List<String> characteristics;
    private Post post;

    public String getName() {
        return lastName + " " + firstName;
    }

    public Employee setFirstName(String firstName) {
        Employee.this.firstName = firstName;
        return this;
    }

    @Override
    public String toString() {
        return "СОТРУДНИК: " + "\n" +
                "ФИО: " + firstName + " " + lastName + "\n" +
                "Описание: " + description + "\n" +
                "Характеристика: " + characteristics + "\n" +
                "Должность: " + post.postName() + "\n";
    }

    @Override
    public int compareTo(Employee o) {
        return getName().compareTo(o.getName());
    }
}
