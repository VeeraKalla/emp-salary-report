package com.swissre.esr.service;

import com.swissre.esr.exception.ESRException;
import com.swissre.esr.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmpServices {

    public Employee getCEO(List<Employee> employeeList) {
        return employeeList.stream()
                .filter(employee -> employee.getManagerId().isEmpty() || employee.getManagerId() == null)
                .findFirst()
                .orElseThrow(() -> new ESRException("CEO Not Found"));
    }

    public void setEmpHeirarchyLength(List<Employee> employeeList) {
        Map<String, Employee> map = prepareEmployeeMap(employeeList);
        for(Map.Entry<String, Employee> entry : map.entrySet()) {
            Employee emp = entry.getValue();
            Employee temp = entry.getValue();
            int length = 0;
            while(temp.getManagerId() !=null && !temp.getManagerId().isEmpty()) {
                length++;
                temp = map.get(temp.getManagerId());
            }
            emp.setHierarchyLength(length);
        }
    }

    private Map<String, Employee> prepareEmployeeMap(List<Employee> employeeList) {
       return employeeList.stream().collect(Collectors.toMap(Employee::getId, Function.identity()));
    }
}
