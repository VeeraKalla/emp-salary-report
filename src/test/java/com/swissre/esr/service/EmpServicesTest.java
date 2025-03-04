package com.swissre.esr.service;

import com.swissre.esr.model.Employee;
import com.swissre.esr.util.FileUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmpServicesTest {

    List<Employee> employeeList;
    EmpServices empServices;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        URL url = getClass().getClassLoader().getResource("employee.csv");
        Path path = Paths.get(url.toURI());
        String filePath = path.toString();
        employeeList = FileUtility.readCSVFile(filePath);
        empServices = new EmpServices();
    }

    @Test
    void testGetCEO() {
        Employee employee = empServices.getCEO(employeeList);
        assertNotNull(employee);
        assertEquals("123",employee.getId());
    }

    @Test
    void testEmpHeirarchyLength() {
        empServices.setEmpHeirarchyLength(employeeList);
        int heirarchyLength = employeeList.stream()
                .filter(employee -> employee.getId().equalsIgnoreCase("300"))
                .mapToInt(Employee::getHierarchyLength).findFirst().orElse(0);
        assertEquals(2, heirarchyLength);
    }
}