package com.swissre.esr.model;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class Employee {

    private String Id;
    private String firstName;
    private String lastName;
    private String managerId;
    private Salary salary;

    private int hierarchyLength;

    @Override
    public String toString() {
        return "Employee{" +
                "Id='" + Id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", managerId='" + managerId + '\'' +
                ", salary=" + salary +
                ", hierarchyLength=" + hierarchyLength +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Id.equals(employee.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
