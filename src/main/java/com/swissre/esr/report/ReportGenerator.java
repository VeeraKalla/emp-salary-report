package com.swissre.esr.report;

import com.swissre.esr.exception.ESRException;
import com.swissre.esr.model.Employee;
import com.swissre.esr.service.EmpServices;
import com.swissre.esr.service.SalaryCalculator;
import com.swissre.esr.util.FileUtility;

import java.io.IOException;
import java.util.List;

public class ReportGenerator {

    public void generateReport(String filePath, int reportingLineThreshold) {
        try {
            List<Employee> employeeList = FileUtility.readCSVFile(filePath);
            EmpServices empServices = new EmpServices();
            SalaryCalculator salaryCalculator = new SalaryCalculator();

            salaryCalculator.findUnderPaidManagers(employeeList);
            salaryCalculator.findOverPaidManagers(employeeList);
            empServices.setEmpHeirarchyLength(employeeList);

            printReport(employeeList, reportingLineThreshold);
        } catch (IOException e) {
            throw new ESRException("Error in ReportGenerator : " + e.getMessage());
        }
    }

    private void printReport(List<Employee> employeeList, int reportingLenThreshold) {
        System.out.println("#UNDER_PAID EMPLOYEES :");
        for(Employee emp : employeeList) {
            if(emp.getSalary().isUnderPaid()) {
                System.out.println("\tEMP_ID       : " + emp.getId());
                System.out.println("\tNAME         : " + emp.getFirstName()+","+emp.getLastName());
                System.out.println("\tSAL_DIFF     : " + emp.getSalary().getDifference());
                System.out.println("\t-------------------------------------------------");
            }
        }

        System.out.println("#OVER_PAID EMPLOYEES :");
        for(Employee emp : employeeList) {
            if(emp.getSalary().isOverPaid()) {
                System.out.println("\tEMP_ID       : " + emp.getId());
                System.out.println("\tNAME         : " + emp.getFirstName()+","+emp.getLastName());
                System.out.println("\tSAL_DIFF     : " + emp.getSalary().getDifference());
                System.out.println("\t--------------------------------------------------");
            }
        }

        System.out.println("#TOO LONG REPORTING LINE OF EMPLOYEES :");
        for(Employee emp : employeeList) {
            if(emp.getHierarchyLength() >= reportingLenThreshold) {
                System.out.println("\tEMP_ID       : " + emp.getId());
                System.out.println("\tNAME         : " + emp.getFirstName()+","+emp.getLastName());
                System.out.println("\tREPORTING_LEN: " + emp.getHierarchyLength());
                System.out.println("\t--------------------------------------------------");
            }

        }
    }

}
