package org.kaoden;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kaoden.module.Data;
import org.kaoden.module.Employee;
import org.kaoden.module.Post;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler {

    public static final Logger LOGGER = LogManager.getLogger(FileHandler.class);
    private static Map<UUID, Post> employeePositions;
    private static final Pattern employeePattern = Pattern.compile(
            "firstName:\\s?(?<firstName>.+)" +
                    "lastName:\\s?(?<lastName>.+)" +
                    "description:\\s?(?<description>.+|)" +
                    "characteristics:\\s?(?<characteristics>.+)" +
                    "postId:\\s?(?<postId>[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12})");

    public static List<Employee> getEmployeeList(File file) throws IOException {
        List<Employee> employeeList = new ArrayList<>();
        employeePositions = Data.getEmployeePositions();
        List<String> infoAboutEmployee = readingFileWithEmployees(file);

        fillOutEmployeeList(employeeList, infoAboutEmployee);

        return employeeList;
    }

    private static List<String> readingFileWithEmployees(File file) throws IOException {
        String line;
        StringBuilder employeeInfo = new StringBuilder();
        List<String> listOfEmployeeInfo = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(file, StandardCharsets.UTF_8));

        while ((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                listOfEmployeeInfo.add(employeeInfo.toString());
                employeeInfo.setLength(0);
            }
            employeeInfo.append(line);
        }

        return listOfEmployeeInfo;
    }

    private static void fillOutEmployeeList(List<Employee> employeeList, List<String> listOfEmployeeInfo) {
        for (String info : listOfEmployeeInfo) {
            Matcher matcher = employeePattern.matcher(info);

            if (!matcher.find()) {
                LOGGER.info("Incorrect employee information.\n" + info + "\n");
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
                    .post(employeePositions.get(UUID.fromString(matcher.group("postId")))).build();

            if (allFieldsAreFilledIn(employee)) {
                LOGGER.info("Not all required fields are filed in.\n" + info + "\n");
                continue;
            }
            employeeList.add(employee);
        }
    }

    private static boolean allFieldsAreFilledIn(Employee employee) {
        return employee.getFirstName().isBlank() ||
                employee.getLastName().isBlank() ||
                employee.getCharacteristics().isEmpty() ||
                employee.getPost().postName().isBlank();
    }
}
