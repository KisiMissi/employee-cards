package org.kaoden;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import org.kaoden.module.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @TempDir
    static Path tempDir;

    @BeforeAll
    static void setTempDir() throws IOException {
        tempDir = Files.createTempDirectory(null);
    }

    @Test
    @DisplayName("Test file with valid information")
    public void getEmployeeList_VALID_FILE() throws IOException {
        Path tempFile = tempDir.resolve("employeeValid.txt");
        Files.writeString(tempFile, "firstName: John\nlastName: Doe\ndescription: A software engineer\ncharacteristics: Hardworking, Intelligent, Dedicated\npostId: 03f6e895-d6c4-4b3c-87db-aa5ddc0e00b9\n\n");

        List<Employee> employeeList = FileHandler.getEmployeeList(tempFile.toFile());

        assertAll(
                () -> assertEquals("John", employeeList.get(0).getFirstName()),
                () -> assertEquals("Doe", employeeList.get(0).getLastName()),
                () -> assertEquals("A software engineer", employeeList.get(0).getDescription()),
                () -> assertEquals(List.of("Dedicated", "Hardworking", "Intelligent"), employeeList.get(0).getCharacteristics()),
                () -> assertEquals("Data Analyst", employeeList.get(0).getPost().postName())
        );
    }

    @Test
    @DisplayName("Test file with invalid information")
    public void getEmployeeList_INVALID_FILE() throws IOException {
        Path tempFile = tempDir.resolve("employeeInvalid.txt");
        Files.writeString(tempFile, "firstName: John\nlastName: Doe\ncharacteristics: Hardworking, Intelligent, Dedicated\n\n");

        List<Employee> employeeList = FileHandler.getEmployeeList(tempFile.toFile());

        assertTrue(employeeList.isEmpty());
    }

    @Test
    @DisplayName("Test file with invalid post ID")
    public void getEmployeeList_INVALID_POST_ID() throws IOException {
        Path tempFile = tempDir.resolve("employeeNoPostID.txt");
        Files.writeString(tempFile, "firstName: John\nlastName: Doe\ndescription: A software engineer\ncharacteristics: Hardworking, Intelligent, Dedicated\npostId: 03f6e895-d6c4");

        List<Employee> employeeList = FileHandler.getEmployeeList(tempFile.toFile());

        assertTrue(employeeList.isEmpty());
    }
}