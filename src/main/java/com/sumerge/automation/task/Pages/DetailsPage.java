package com.sumerge.automation.task.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DetailsPage  extends MainPage{
    public DetailsPage(WebDriver driver) {
        super(driver);
    }

    private final By te = By.xpath("//table/tbody/tr[1]/td[5]/div");


    public String confirmResrvation()
    {

        try {




            selectFromDropDownMenu(te,"1");

            return "Pass";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return  "Fail";
        }


    }


}
