package test;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.*;
import pageObjects.SamplePage;
import runner.TestRunner;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    protected TestRunner testRunner;
    public AppiumDriver driver;
    public SoftAssert softAssert;

    public SamplePage sampleScreenPage;
    @BeforeClass(alwaysRun = true)
    public void initialize() {
        softAssert =  new SoftAssert();
    }



    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        testRunner = new TestRunner();
        this.driver = testRunner.getDriver();
        initPages();
    }


    @AfterMethod(alwaysRun = true)
    public void refreshApp() {
        driver.quit();
    }

    private void initPages() {
        sampleScreenPage = new SamplePage(driver);
    }


}
