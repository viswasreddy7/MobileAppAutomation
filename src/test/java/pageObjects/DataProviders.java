package pageObjects;


import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "Leagues")
    public Object[][] LeagueNameData() {
        return new Object[][]{
                {"NBA Basketball","NBA"},
                {"NFL Football","NFL"}
        };
    }
}

