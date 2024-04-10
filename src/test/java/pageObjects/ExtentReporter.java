package pageObjects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExtentReporter {
    private static ExtentReports extent;
    public static String reportDirectory = "";
    @Getter @Setter
    private static ExtentTest test;

    private ExtentReporter(){}

    @SneakyThrows
    public static ExtentReports getExtentReporter() {
        if (extent == null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = dateFormat.format(new Date());
            reportDirectory = System.getProperty("user.dir") + "/reports/"+timestamp+"/";
            File directory = new File(reportDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String path = reportDirectory + "testReport"+".html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
            reporter.config().setReportName("MobileAutomationResults");
            reporter.config().setDocumentTitle("TestResults");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", System.getProperty("user.name"));
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));

        }
        return extent;
    }

}
