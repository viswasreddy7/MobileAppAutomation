package pageObjects;

import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.time.Duration;
import java.util.List;

public class SamplePage extends Listeners{

    public AppiumDriver driver;
    WebElement selectLeague;
    WebElement selectedLeague;
    WebElement notifyPrefer;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Get Started']")
    public WebElement getStartedButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='NFL Football']")
    public WebElement nflFootball;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.fivemobile.thescore:id/label']")
    public WebElement containerLeagueSelected;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Continue']")
    public WebElement continueButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Choose your favorite teams']")
    public WebElement chooseFavTeamsText;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/btn_disallow")
    public WebElement disallowLocationBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Toronto Raptors']")
    public WebElement torontoRaptors;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='TOR']//parent::android.view.ViewGroup")
    public WebElement torRaptorsSelected;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Done']")
    public WebElement doneButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Maybe Later']")
    public WebElement maybeLtrSignupBtn;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/dismiss_modal")
    public WebElement closeIcon;

    @AndroidFindBy(id = "com.fivemobile.thescore:id/team_name")
    public WebElement teamName;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Allow']")
    public WebElement allowNotify;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='TEAM STATS']")
    public WebElement teamStatsTab;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='STATS']")
    public WebElement statsHeading;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='com.fivemobile.thescore:id/view_progress']")
    public List<WebElement> progressBar;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    public WebElement backButton;

    public SamplePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void selectFavLeague(String league, String LeagueShort) {
        waitUntilVisible(getStartedButton);
        getStartedButton.click();
        waitUntilVisible(nflFootball);
        selectLeague = driver.findElement(By.xpath("//android.widget.TextView[@text='"+league+"']"));
        selectLeague.click();
        waitUntilVisible(containerLeagueSelected);
        selectedLeague = driver.findElement(By.xpath("//android.widget.TextView[@text='"+LeagueShort+"']//parent::android.view.ViewGroup"));
        if (selectedLeague.isDisplayed()) {
            ExtentReporter.getTest().log(Status.PASS,"NFL football is selected.");
        }else {
            ExtentReporter.getTest().log(Status.FAIL,"NFL football is not selected.");
        }
        continueButton.click();
        waitUntilVisible(disallowLocationBtn);
        if (disallowLocationBtn.isDisplayed()) {
            disallowLocationBtn.click();
        }
        if (chooseFavTeamsText.isDisplayed()) {
            ExtentReporter.getTest().log(Status.PASS,"Successfully selected all favorite leagues.");
        }else {
            ExtentReporter.getTest().log(Status.FAIL,"Failed to select all favorites leagues.");
        }
    }

    public void selectFavTeams() {
        torontoRaptors.click();
        waitUntilVisible(torRaptorsSelected);
        if (torRaptorsSelected.isDisplayed()) {
            ExtentReporter.getTest().log(Status.PASS,"Toronto Raptors is selected.");
        }else {
            ExtentReporter.getTest().log(Status.FAIL,"Toronto raptors is not selected.");
        }
        continueButton.click();
        if(doneButton.isDisplayed()) {
            doneButton.click();
            ExtentReporter.getTest().log(Status.PASS,"successfully selected all favorite teams.");
        }else {
            ExtentReporter.getTest().log(Status.FAIL,"Failed to select all favorite teams.");
        }
    }

    public void signUpLater() {
        waitUntilVisible(maybeLtrSignupBtn);
        if (maybeLtrSignupBtn.isDisplayed()) {
            maybeLtrSignupBtn.click();
            ExtentReporter.getTest().log(Status.PASS,"Clicked on may be later signup button.");
            waitUntilVisible(allowNotify);
        }
    }

    public void notificationPreference(String Preference) {
        notifyPrefer = driver.findElement(By.xpath("//android.widget.Button[@text='"+Preference+"']"));
        notifyPrefer.click();
        ExtentReporter.getTest().log(Status.INFO,"Notification preference is "+Preference);
        waitUntilVisible(closeIcon);
        if (closeIcon.isDisplayed()) {
            closeIcon.click();
            ExtentReporter.getTest().log(Status.PASS,"Advertisement modal is closed.");
        }
    }

    public void clickOnTorRaptors() {
        torRaptorsSelected.click();
        waitUntilVisible(teamName);
        if (teamName.getText().equals("Toronto Raptors")) {
            ExtentReporter.getTest().log(Status.PASS,"Toronto Raptors page is displayed.");
        }else {
            ExtentReporter.getTest().log(Status.FAIL,"Toronto Raptors page is not displayed.");
        }
    }

    public void clickOnSubTab() {
        teamStatsTab.click();
        waitUntilVisible(statsHeading);
        Reporter.log("Team stats tab is selected.");
        if (progressBar.size()>5) {
            ExtentReporter.getTest().log(Status.PASS,"Team stats tab is displaying correct data.");
        }else {
            ExtentReporter.getTest().log(Status.FAIL,"Team stats tab is not displaying correct data.");
        }
    }

    public void navigateBack() {
        backButton.click();
        Reporter.log("Clicked on back button.");
        waitUntilVisible(torRaptorsSelected);
        if (torRaptorsSelected.isDisplayed()) {
            ExtentReporter.getTest().log(Status.PASS,"Successfully navigated back.");
        }else {
            ExtentReporter.getTest().log(Status.FAIL,"Failed to navigate back.");
        }

    }

    public void waitUntilVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
