package org.kaoden;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kaoden.module.Employee;

import java.io.File;
import java.io.IOException;
import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        List<Employee> employeeList;
        try {
            String dataFilePath = getDataFilePath(args);
            System.out.println(dataFilePath);
            employeeList = FileHandler.getEmployeeList(new File(dataFilePath));

            sortedOutputListOfEmployee(employeeList);

        } catch (IOException ex) {
            LOGGER.error("File not found", ex);
        }
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
