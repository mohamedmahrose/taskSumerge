package com.sumerge.automation.task.test;

import com.sumerge.automation.task.Pages.ConfirmationPage;
import com.sumerge.automation.task.Pages.DetailsPage;
import com.sumerge.automation.task.Pages.MainPage;
import com.sumerge.automation.task.dataModel.ExcelReader;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.xml.datatype.DatatypeFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class BaseTest {


    public static WebDriver driver;
    ArrayList MainTestData = new ArrayList();
    String actual;

    String [] Data;


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

 @Test(dataProvider = "TestData")
public void reserveHotel(String DateFrom,String DateTo, String Location)
 {


     MainPage main = new MainPage(driver,DateFrom,DateTo,Location);
     actual = main.reserverHotel();
     Assert.assertEquals(actual,"Pass");
     DetailsPage details = new DetailsPage(driver,DateFrom,DateTo,Location);

     actual = details.confirmResrvation();
     Assert.assertEquals(actual,"Pass");
     ConfirmationPage confirm = new ConfirmationPage(driver,DateFrom,DateTo,Location);
     actual = confirm.reserve();
     Assert.assertEquals(actual,"Tolip Hotel Alexandria");
 }
    @AfterSuite(description = "Closing all active drivers", alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name="TestData")
    public static Object[][] TestData()
    {
        ExcelReader Er = new ExcelReader();
        return Er.getExcelData();
    }




}
