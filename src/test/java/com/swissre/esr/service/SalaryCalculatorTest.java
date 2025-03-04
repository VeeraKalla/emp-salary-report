package com.swissre.esr.service;

import com.swissre.esr.exception.ESRException;
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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SalaryCalculatorTest {

    List<Employee> employeeList;
    SalaryCalculator salaryCalculator;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        URL url = getClass().getClassLoader().getResource("employee.csv");
        Path path = Paths.get(url.toURI());
        String filePath = path.toString();
        employeeList = FileUtility.readCSVFile(filePath);
        salaryCalculator = new SalaryCalculator();
    }

    @Test
    void testUnderPaidManagers(){
        salaryCalculator.findUnderPaidManagers(employeeList);
        Employee underpaid = employeeList.stream().filter(employee -> employee.getSalary().isUnderPaid())
                .findFirst().orElseThrow(() -> new ESRException("Under paid employee not found"));
        assertEquals("124",underpaid.getId());
    }

    @Test
    void testOverPaidManagers(){
        salaryCalculator.findOverPaidManagers(employeeList);
        Employee underpaid = employeeList.stream().filter(employee -> employee.getSalary().isOverPaid())
                .findFirst().orElseThrow(() -> new ESRException("Over paid employee not found"));
        assertEquals("300",underpaid.getId());
    }

    @Test
    void testFairlyPaidManagers(){
        salaryCalculator.findUnderPaidManagers(employeeList);
        salaryCalculator.findOverPaidManagers(employeeList);
        List<Employee> fairlyPaid = employeeList.stream().filter(employee -> !employee.getSalary().isOverPaid()
                && !employee.getSalary().isUnderPaid()).collect(Collectors.toList());
        assertEquals(3,fairlyPaid.size());
    }
}