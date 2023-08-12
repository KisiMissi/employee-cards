package org.kaoden.module;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Employee {

    private String firstName;
    private String lastName;
    private String description;
    private List<String> characteristics;
    private Post post;

    @Override
    public String toString() {
        return "СОТРУДНИК: " + "\n" +
                "ФИ: " + firstName + " " + lastName + "\n" +
                "Описание: " + description + "\n" +
                "Характеристика: " + characteristics + "\n" +
                "Должность: " + post.name() + "\n";
    }
}
