package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.DataProviders;

public class TheScoreAppTest extends BaseTest{

    @Test(dataProvider = "Leagues", dataProviderClass = DataProviders.class)
    public void theScoreApp(String League, String LeagueShort) {
        sampleScreenPage.selectFavLeague(League,LeagueShort);
        sampleScreenPage.selectFavTeams();
        sampleScreenPage.signUpLater();
        sampleScreenPage.notificationPreference("Allow");
        sampleScreenPage.clickOnTorRaptors();
        sampleScreenPage.clickOnSubTab();
        sampleScreenPage.navigateBack();
        softAssert.assertAll();
    }
}
