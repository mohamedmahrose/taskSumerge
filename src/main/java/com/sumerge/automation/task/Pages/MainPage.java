package com.sumerge.automation.task.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Select select;


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    private final By locationToReserve = By.name("ss");
    private final By calender = By.xpath("//div[@data-testid='searchbox-dates-container']");

    private final By calenderFrom = By.cssSelector("span[aria-label='31 March 2024']");
    private final By calenderTo = By.cssSelector("span[aria-label='16 April 2024'] span");
    private final By exitButton = By.xpath("//button[@aria-label='Dismiss sign-in info.']");

    private final By submitButton = By.xpath("//button[@type='submit']");

    private final By hotelName = By.xpath("//div[contains(text(),'Tolip Hotel')]");


    public String reserverHotel() {
        try {
            driver.get("https://www.booking.com/");

            click(exitButton);

            sendKeys(locationToReserve, "Alexandria");

            click(calender);

            click(calenderFrom);
            Thread.sleep(2000);
            click(calenderTo);
            Thread.sleep(2000);
            click(submitButton);
            click(hotelName);
            Thread.sleep(10000);
            //scrollToElement(driver,selectAmountList);
            //click(selectAmountList);
            //Thread.sleep(10000);
            return "Pass";
        } catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }

    }


    public void verifyElementExist(WebElement element) {
        try {
            waitUntilElementVisible(element);
            // Log.info(element.getText() + " Object is Visible");
        } catch (Exception e) {
            //Log.info("Object is not Visible");
        }
    }

    public void waitUntilElementVisible(WebElement element) {

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(5))
                .until(ExpectedConditions.visibilityOf(element));


    }

    public void sendKeys(By locator, String Text) {
        for (int i = 0; i < 10; i++)

            try {
                // Wait for the element to be present and visible on the page
                wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                wait.until(ExpectedConditions.and(
                        ExpectedConditions.presenceOfElementLocated(locator),
                        ExpectedConditions.visibilityOfElementLocated(locator)));
                scrollToElement(driver, driver.findElement(locator));
                driver.findElement(locator).sendKeys(Text);
                break;

            } catch (NoSuchElementException | StaleElementReferenceException e) {
                e.printStackTrace();
            }
    }

    public void click(By locator) {
        for (int i = 0; i < 10; i++)

            try {
                // Wait for the element to be present and visible on the page
                wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                wait.until(ExpectedConditions.and(
                        ExpectedConditions.presenceOfElementLocated(locator),
                        ExpectedConditions.elementToBeClickable(locator),
                        ExpectedConditions.visibilityOfElementLocated(locator)));
                scrollToElement(driver, driver.findElement(locator));
                driver.findElement(locator).click();
                break;

            } catch (NoSuchElementException | StaleElementReferenceException | ElementClickInterceptedException e) {
                e.printStackTrace();
            }
    }

    public void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void selectFromDropDownMenu(By locator,String number) {
    select = new Select(driver.findElement(locator));
    select.selectByValue(number);
    }
}