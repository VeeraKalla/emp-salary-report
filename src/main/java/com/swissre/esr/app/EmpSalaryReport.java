package com.swissre.esr.app;

import com.swissre.esr.exception.ESRException;
import com.swissre.esr.report.ReportGenerator;

import java.util.Scanner;

public class EmpSalaryReport {

    public static void main(String[] args) {
        String filePath;
        String reportingLineThreshold;
        if(args.length > 0) {
            filePath = args[0];
            System.out.println("File Path provided : " + filePath);
            reportingLineThreshold = args[1];
            System.out.println("Employees reporting line threshold provided : " + reportingLineThreshold);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the file path : ");
            filePath = scanner.nextLine();
            System.out.println("Please enter the employee reporting line threshold : ");
            reportingLineThreshold = scanner.nextLine();
            scanner.close();
        }

        try {
            ReportGenerator reportGenerator = new ReportGenerator();
            reportGenerator.generateReport(filePath, Integer.parseInt(reportingLineThreshold));
        } catch (ESRException e) {
            System.err.println(e.getMessage());
        }
    }
}
