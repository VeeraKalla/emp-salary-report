package com.swissre.esr.report;

import com.swissre.esr.exception.ESRException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ReportGeneratorTest {

    String filePath;
    int reportingLineThreshold;

    @BeforeEach
    void setUp() throws URISyntaxException {
        URL url = getClass().getClassLoader().getResource("employee.csv");
        Path path = Paths.get(url.toURI());
        filePath = path.toString();
        reportingLineThreshold = 2;
    }

    @Test
    void testGenerateReport() {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generateReport(filePath, reportingLineThreshold);
        assertNotNull(reportGenerator);
    }

    @Test
    void testGenerateReportOnlyCSVFileAccepted() {
        ReportGenerator reportGenerator = new ReportGenerator();
        ESRException exception = assertThrows(ESRException.class, () -> reportGenerator.generateReport(filePath + "c", reportingLineThreshold));
        assertEquals("Only CSV files are accepted", exception.getMessage());
    }

    @Test
    void testFileNotFoundException() {
        ReportGenerator reportGenerator = new ReportGenerator();
        ESRException exception = assertThrows(ESRException.class, () -> reportGenerator.generateReport( "dummy.csv", reportingLineThreshold));
        assertEquals("Error in ReportGenerator : dummy.csv (The system cannot find the file specified)", exception.getMessage());
    }
}