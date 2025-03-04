package com.swissre.esr.service;

import com.swissre.esr.model.Employee;
import com.swissre.esr.model.Salary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalaryCalculator {

    private Map<Employee, Double> getManagersDirectReportsSalaryAvg(List<Employee> employeeList) {
        Map<Employee, Double> map = new HashMap<>();
        for(Employee emp : employeeList) {
            double sal = 0;
            int count = 0;
            for (Employee reportee : employeeList) {
                if (emp.getId().equalsIgnoreCase(reportee.getManagerId())) {
                    sal += reportee.getSalary().getSalary();
                    count++;
                }
            }
            if(sal > (double) 0) { // To keep only managers and their direct reportees avg salary
                map.put(emp, sal / count);
            }
        }
        return map;
    }

    public void findUnderPaidManagers(List<Employee> employeeList) {
        Map<Employee, Double> map = getManagersDirectReportsSalaryAvg(employeeList);
        for(Map.Entry<Employee,Double> entry : map.entrySet()) {
            Employee manager = entry.getKey();
            Salary salary = manager.getSalary();
            Double reporteesAvgSal = entry.getValue();
            if(salary.getSalary() < reporteesAvgSal * 1.2) {
                salary.setUnderPaid(true);
                salary.setDifference(Math.abs(salary.getSalary() - (reporteesAvgSal * 1.2)));
            }
        }
    }

    public void findOverPaidManagers(List<Employee> employeeList) {
        Map<Employee, Double> map = getManagersDirectReportsSalaryAvg(employeeList);
        for(Map.Entry<Employee,Double> entry : map.entrySet()) {
            Employee manager = entry.getKey();
            Salary salary = manager.getSalary();
            Double reporteesAvgSal = entry.getValue();
            if(salary.getSalary() > reporteesAvgSal * 1.5) {
                salary.setDifference(Math.abs(salary.getSalary() - (reporteesAvgSal * 1.5)));
                salary.setOverPaid(true);
            }
        }
    }

}
