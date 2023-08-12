package org.kaoden;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.kaoden.module.Employee;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestEmployeeReader {

    @Test
    @DisplayName("Test file with valid information")
    void getEmployeeListValidFile() {
        // Arrange
        SoftAssertions softly = new SoftAssertions();
        File testFile = getTestFile("employeeValid.txt");

        // Act
        List<Employee> employeeList = EmployeeReader.getEmployeeList(testFile);

        // Assert
        softly.assertThat(employeeList.get(0).getFirstName()).isEqualTo("John");
        softly.assertThat(employeeList.get(0).getLastName()).isEqualTo("Doe");
        softly.assertThat(employeeList.get(0).getDescription()).isEqualTo("A software engineer");
        softly.assertThat(employeeList.get(0).getCharacteristics()).isEqualTo(List.of("Dedicated", "Hardworking", "Intelligent"));
        softly.assertThat(employeeList.get(0).getPost().name()).isEqualTo("Data Analyst");
        softly.assertAll();
    }

    @Test
    @DisplayName("Test file with invalid information")
    void getEmployeeListInvalidFile() {
        // Arrange
        File testFile = getTestFile("employeeInvalid.txt");

        // Act
        List<Employee> employeeList = EmployeeReader.getEmployeeList(testFile);

        // Assert
        assertTrue(employeeList.isEmpty());
    }

    @Test
    @DisplayName("Test file with invalid post ID")
    void getEmployeeListInvalidPostID() {
        // Arrange
        File testFile = getTestFile("employeeInvalidPostID.txt");

        // Act
        List<Employee> employeeList = EmployeeReader.getEmployeeList(testFile);

        // Arrange
        assertTrue(employeeList.isEmpty());
    }

    File getTestFile(String fileName) {
        return new File("src\\test\\resources\\" + fileName);
    }
}