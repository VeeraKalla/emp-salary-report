package com.swissre.esr.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Salary {

    private boolean underPaid;
    private boolean overPaid;
    private double Salary;
    private double difference;

    @Override
    public String toString() {
        return "Salary{" +
                "underPaid=" + underPaid +
                ", overPaid=" + overPaid +
                ", Salary=" + Salary +
                ", difference=" + difference +
                '}';
    }


}
