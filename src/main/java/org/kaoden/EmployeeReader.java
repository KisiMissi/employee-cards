package org.kaoden;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kaoden.module.Data;
import org.kaoden.module.Employee;
import org.kaoden.module.Post;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeReader {

    public static final Logger LOGGER = LogManager.getLogger(FileHandler.class);
    private static final Pattern employeePattern = Pattern.compile(
            "firstName:\\s?(?<firstName>.+)" +
                    "lastName:\\s?(?<lastName>.+)" +
                    "description:\\s?(?<description>.+|)" +
                    "characteristics:\\s?(?<characteristics>.+)" +
                    "postId:\\s?(?<postId>[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12})");

    public static List<Employee> getEmployeeList(File file) {
        List<Employee> employeeList = new ArrayList<>();
        Map<UUID, Post> employeePositions = Data.getEmployeePositions();
        List<String> infoAboutEmployee = readingFileWithEmployees(file);
        fillOutEmployeeList(employeeList, infoAboutEmployee, employeePositions);

        return employeeList;
    }

    private static List<String> readingFileWithEmployees(File file) {
        List<String> listOfEmployeeInfo = new ArrayList<>();

        if (! file.exists()) {
            LOGGER.error("File doesn't exist\n" + file.toPath());
            System.exit(-1);
        }

        try {
            List<String> employeeInformation = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            StringBuilder employeeInfo = new StringBuilder();
            for (String info : employeeInformation) {
                if (info.isEmpty()) {
                    listOfEmployeeInfo.add(employeeInfo.toString());
                    employeeInfo.setLength(0);
                }
                employeeInfo.append(info);
            }
        } catch (IOException ioe) {
            LOGGER.error("File reading error", ioe);
            System.exit(-1);
        }

        return listOfEmployeeInfo;
    }

    private static void fillOutEmployeeList(List<Employee> employeeList, List<String> listOfEmployeeInfo, Map<UUID, Post> employeePositions) {
        for (String info : listOfEmployeeInfo) {
            Matcher matcher = employeePattern.matcher(info);

            if (!matcher.find()) {
                LOGGER.info("Incorrect employee information.\n" + info);
                continue;
            }

            Employee employee = Employee.builder()
                    .firstName(matcher.group("firstName"))
                    .lastName(matcher.group("lastName"))
                    .description(matcher.group("description"))
                    .characteristics(Arrays.stream(matcher.group("characteristics")
                                    .split(", "))
                            .sorted()
                            .toList())
                    .post(employeePositions.get(UUID.fromString(matcher.group("postId"))))
                    .build();

            if (allFieldsAreFilledIn(employee)) {
                LOGGER.info("Not all required fields are filed in.\n" + info);
                continue;
            }
            employeeList.add(employee);
        }
    }

    private static boolean allFieldsAreFilledIn(Employee employee) {
        return employee.getFirstName().isBlank() ||
                employee.getLastName().isBlank() ||
                employee.getCharacteristics().isEmpty() ||
                employee.getPost().name().isBlank();
    }
}
