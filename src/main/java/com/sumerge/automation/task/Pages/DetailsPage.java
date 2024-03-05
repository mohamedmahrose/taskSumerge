package com.sumerge.automation.task.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DetailsPage  extends MainPage{
    protected WebDriver driver;


    private final By selectAmount = By.xpath("//table/tbody/tr[1]/td[5]//select");
    private final By reserveBtn = By.xpath("//button[@id='b_tt_holder_1']");

    public DetailsPage(WebDriver driver, String DateFrom, String DateTo, String HotelName) {
        super(driver, DateFrom, DateTo, HotelName);
    }


    public String confirmResrvation()
    {

        try {




            selectFromDropDownMenu(selectAmount,"1");
            click(reserveBtn);

            return "Pass";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return  "Fail";
        }


    }


}
