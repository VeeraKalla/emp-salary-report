package com.swissre.esr.util;

import com.swissre.esr.exception.ESRException;
import com.swissre.esr.model.Employee;
import com.swissre.esr.model.Salary;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static com.swissre.esr.common.Constants.*;

/**
 * This class is responsible for performing utility operations on files
 */
public class FileUtility {

    /**
     * Reads the CSV file and convert the each row in to employee object
     *
     * @param filePath path of the file location
     * @return list of employee objects
     * @throws IOException
     */
    public static List<Employee> readCSVFile(String filePath) throws IOException {
        if(!filePath.endsWith(".csv")){
            throw new ESRException("Only CSV files are accepted");
        }
        List<Employee> list = new ArrayList<>();
        try (Reader reader = new FileReader(filePath)) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader()
                    .withIgnoreHeaderCase().withTrim());

            for (CSVRecord record : csvParser) {
                String id = record.get(EMPLOYEE_ID);
                String firstName = record.get(FIRST_NAME);
                String lastName = record.get(LAST_NAME);
                Double sal = Double.valueOf(record.get(SALARY));
                String managerId = record.get(MANAGER_ID);

                Salary salary = Salary.builder().Salary(sal).build();
                Employee emp = Employee.builder().Id(id).firstName(firstName)
                        .lastName(lastName).salary(salary)
                        .managerId(managerId)
                        .build();

                list.add(emp);
            }
            return list;
        } catch (FileNotFoundException e) {
            System.err.println("File was not found at this location :" + filePath);
            throw e;
        } catch (IOException e) {
            System.err.println("Some IOException occurred :" + e.getMessage());
            throw e;
        }
    }
}
