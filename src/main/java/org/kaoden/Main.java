package org.kaoden;

import org.kaoden.module.Employee;

import java.io.File;
import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String dataFilePath = getDataFilePath(args);
        List<Employee> employeeList = EmployeeReader.getEmployeeList(new File(dataFilePath));

        sortedOutputListOfEmployee(employeeList);
    }

    private static String getDataFilePath(String[] args) {
        Pattern pattern = Pattern.compile("(?<filePath>.+\\.txt$)");
        Matcher matcher = pattern.matcher(args[0]);

        return matcher.find() ? matcher.group("filePath") : "";
    }

    private static void sortedOutputListOfEmployee(List<Employee> employees) {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getFirstName))
                .sorted(Comparator.comparing(Employee::getLastName))
                .forEach(System.out::println);
    }
}
