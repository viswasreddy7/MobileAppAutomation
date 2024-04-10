package runner;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.MutableCapabilities;
import org.testng.Assert;

import java.io.FileReader;
import java.net.URL;
import java.util.Properties;

public class TestRunner {

    private static String url = "";
    public static AppiumDriver driver;
    private static AppiumDriverLocalService service;
    private boolean isLocalBuild = false;
    private boolean isAndroid = true;
    private String appLocation;

    private String androidDeviceName;
    private String androidDeviceVersion;
    private String iosDeviceName;
    private String iosDeviceVersion;
    private String iosDeviceUUID;

    public String getAndroidDeviceName() {
        return androidDeviceName;
    }

    public String getAndroidDeviceVersion() {
        return androidDeviceVersion;
    }

    public String getIosDeviceName() {
        return iosDeviceName;
    }

    public String getIosDeviceVersion() {
        return iosDeviceVersion;
    }

    public String getIosDeviceUUID() {
        return iosDeviceUUID;
    }

    public TestRunner() throws Exception {
        readTestProperties();
        initializeDriver();
    }
    public AppiumDriver getDriver(){
        return driver;
    }
    public boolean isAndroid(){
        return isAndroid;
    }
    public boolean isLocalBuild(){
        return isLocalBuild;
    }
    public void quitDriver(){
        driver.quit();
    }


    private void readTestProperties() throws Exception{
        Properties properties = null;
        FileReader reader = new FileReader("test.properties");
        properties = new Properties();
        properties.load(reader);
        Assert.assertNotNull(properties);
        reader.close();
        isAndroid = Boolean.parseBoolean(properties.getProperty("app.isAndroid"));
        isLocalBuild = Boolean.parseBoolean(properties.getProperty("app.isLocalBuild"));
        appLocation = properties.getProperty("app.appLocation");
        androidDeviceName = properties.getProperty("android.deviceName");
        androidDeviceVersion = properties.getProperty("android.deviceVersion");
        iosDeviceName = properties.getProperty("ios.deviceName");
        iosDeviceVersion = properties.getProperty("ios.deviceVersion");
        iosDeviceUUID = properties.getProperty("ios.deviceUUID");
    }

    private void initializeDriver() throws Exception {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
        caps.setCapability(MobileCapabilityType.NO_RESET, "false");
        if (isLocalBuild) {
            URL url = new URL(startAndGetURL());
            caps.setCapability(MobileCapabilityType.APP, appLocation);
            if (isAndroid) {
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, androidDeviceName);
                caps.setCapability("appium:platform", "android");
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, androidDeviceVersion);
                caps.setCapability(AndroidMobileCapabilityType.ADB_EXEC_TIMEOUT, 100000);
                caps.setCapability(AndroidMobileCapabilityType.APP_WAIT_DURATION, 100000);
                driver = new AndroidDriver(url, caps);
                System.out.println("Android Driver Initialized");
            } else {
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, iosDeviceName);
                caps.setCapability("appium:platform", "iOS");
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, iosDeviceVersion);
                caps.setCapability("udid", iosDeviceUUID);
                driver = new IOSDriver(url, caps);
                System.out.println("iOS Driver Initialized");
            }
        }
    }

    public static synchronized String startAndGetURL() {
        try {
            if (url.isEmpty()) {
                service = new AppiumServiceBuilder()
                        .withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/")
                        .usingAnyFreePort()
                        .build();
                url = service.getUrl().toString();
                service.start();
            }
            return url;
        } catch (AppiumServerHasNotBeenStartedLocallyException e) {
            throw new RuntimeException(e);
        }
    }
}
