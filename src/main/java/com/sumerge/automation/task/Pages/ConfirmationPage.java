package com.sumerge.automation.task.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage extends MainPage{


    public ConfirmationPage(WebDriver driver, String DateFrom, String DateTo, String HotelName) {
        super(driver, DateFrom, DateTo, HotelName);
            }

    private final By HotelName = By.xpath("//h1[contains(text(),'Tolip Hotel Alexandria')]");
    public String reserve()
    {
        String nameOfHotel = driver.findElement(HotelName).getText();

        return nameOfHotel;
    }
}
