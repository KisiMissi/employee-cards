package org.kaoden.module;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Data {

    public static Map<UUID, Post> getEmployeePositions() {
        Map<UUID, Post> employeePositions = new HashMap<>();

        employeePositions.put(UUID.fromString("a8a8f0bb-77f6-44c1-a784-f30df058039a"),
                new Post(UUID.fromString("a8a8f0bb-77f6-44c1-a784-f30df058039a"), "Software Developer"));
        employeePositions.put(UUID.fromString("d0ee1fa7-90a8-4c16-b284-080d425fa328"),
                new Post(UUID.fromString("d0ee1fa7-90a8-4c16-b284-080d425fa328"), "System Administrator"));
        employeePositions.put(UUID.fromString("63d68fb2-8ed3-4e7b-98fa-01a7a8c1f782"),
                new Post(UUID.fromString("63d68fb2-8ed3-4e7b-98fa-01a7a8c1f782"), "Network Engineer"));
        employeePositions.put(UUID.fromString("0452c81a-491e-4a8f-a84c-529697d1af70"),
                new Post(UUID.fromString("0452c81a-491e-4a8f-a84c-529697d1af70"), "Software Developer"));
        employeePositions.put(UUID.fromString("03f6e895-d6c4-4b3c-87db-aa5ddc0e00b9"),
                new Post(UUID.fromString("03f6e895-d6c4-4b3c-87db-aa5ddc0e00b9"), "Data Analyst"));
        employeePositions.put(UUID.fromString("b6a36124-1ef9-43ea-9648-325d51a2cc19"),
                new Post(UUID.fromString("b6a36124-1ef9-43ea-9648-325d51a2cc19"), "Network Engineer"));
        employeePositions.put(UUID.fromString("42a1721f-55a7-4f02-a3c7-8d82ab4de4c4"),
                new Post(UUID.fromString("42a1721f-55a7-4f02-a3c7-8d82ab4de4c4"), "Web Developer"));
        employeePositions.put(UUID.fromString("5b5a5f5a-97c8-48f3-af28-b36a17f9df0e"),
                new Post(UUID.fromString("5b5a5f5a-97c8-48f3-af28-b36a17f9df0e"), "System Administrator"));
        employeePositions.put(UUID.fromString("680f83b7-9682-4419-951c-2d2c79c61db3"),
                new Post(UUID.fromString("680f83b7-9682-4419-951c-2d2c79c61db3"), "IT Consultant"));
        employeePositions.put(UUID.fromString("8dd2d2f2-1a7a-4838-bca7-18150df0efb3"),
                new Post(UUID.fromString("8dd2d2f2-1a7a-4838-bca7-18150df0efb3"), "Software Developer"));
        employeePositions.put(UUID.fromString("9ec17db7-735b-46f8-92f4-4e4b98ed3c2e"),
                new Post(UUID.fromString("9ec17db7-735b-46f8-92f4-4e4b98ed3c2e"), "System Administrator"));
        employeePositions.put(UUID.fromString("0133b3cb-2159-4b8d-93d7-7de90fbf16d4"),
                new Post(UUID.fromString("0133b3cb-2159-4b8d-93d7-7de90fbf16d4"), "IT Architect"));

        return employeePositions;
    }
}
