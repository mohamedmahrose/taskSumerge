package com.sumerge.automation.task.test;

import com.sumerge.automation.task.Pages.DetailsPage;
import com.sumerge.automation.task.Pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;

public class BaseTest {


    public static WebDriver driver;
    ArrayList MainTestData = new ArrayList();
    String actual;




    @BeforeTest(description = "Setup the web Driver")
    public void setupDriver() {



        ChromeOptions options = new ChromeOptions();

        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to Windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.addArguments("--disable-in-process-stack-traces");
        options.addArguments("--disable-logging");
        options.addArguments("--log-level=3");
        options.addArguments("--remote-allow-origins=*");


        driver=new ChromeDriver(options);

    }

 @Test
public void reserveHotel()
 {
    // driver.get("https://www.booking.com/");
     MainPage main = new MainPage(driver);
     actual = main.reserverHotel();
     Assert.assertEquals(actual,"Pass");
     DetailsPage details = new DetailsPage(driver);

     actual = details.confirmResrvation();
     Assert.assertEquals(actual,"Pass");
 }
    @AfterSuite(description = "Closing all active drivers", alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "Main TestData")
    public Object[][] hotelInfoDP() {
        Object[][] DP = new Object[MainTestData.size()][1];
        for (int i = 0; i < MainTestData.size(); i++)
            DP[i][0] = MainTestData.get(i);

        return DP;
    }
}
