package com.sumerge.automation.task.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Select select;

    protected String DateFrom;
    protected String DateTo;
    protected String HotelName;

    public MainPage(WebDriver driver, String DateFrom, String DateTo, String HotelName) {

        this.driver = driver;
        this.DateFrom = DateFrom;
        this.DateTo = DateTo;
        ;
        this.HotelName = HotelName;
    }


    private final By locationToReserve = By.name("ss");
    private final By calender = By.xpath("//div[@data-testid='searchbox-dates-container']");

    //private final By calenderFrom = By.xpath("//span[@aria-label='"+this.DateFrom +"']"); //By.cssSelector("span[aria-label='"+DateFrom +"']");
    //private final By calenderTo = By.cssSelector("span[aria-label='"+this.DateTo +"'] span");
    private final By exitButton = By.xpath("//button[@aria-label='Dismiss sign-in info.']");

    private final By submitButton = By.xpath("//button[@type='submit']");

    private final By hotelName = By.xpath("//div[contains(text(),'Tolip Hotel Alexandria')]");


    public String reserverHotel() {
        try {



            driver.get("https://www.booking.com/");

            click(exitButton);

            sendKeys(locationToReserve, HotelName);

            click(calender);
            WebElement dateFrom = driver.findElement(By.xpath("//span[@data-date='" + this.DateFrom + "']"));
            dateFrom.click();
            //click();
            WebElement dateTo = driver.findElement(By.xpath("//span[@data-date='" + this.DateTo + "']"));
            dateTo.click();
            Thread.sleep(2000);
            //click(calenderTo);
            Thread.sleep(2000);
            click(submitButton);
            findHotelName();
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

    public void selectFromDropDownMenu(By locator, String number) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeSelected(locator));
        select = new Select(driver.findElement(locator));
        select.selectByValue(number);
    }


    public void findHotelName() {
        // Loop to search through pages
        boolean found = false;
        while (!found) {
            // Get all hotel names on current page
            java.util.List<WebElement> hotelElements = driver.findElements(By.xpath("//div[contains(text(),'Tolip Hotel Alexandria')]"));
            // Check each hotel name for specific name
            for (WebElement hotelElement : hotelElements) {
                String hotelName = hotelElement.getText();
                if (hotelName.contains("tolip")) {
                    System.out.println("Specific name found: " + hotelName);
                    found = true;
                    break;
                }
            }
            if (!found) {
                //WebElement nextPageButton = driver.findElement(By.xpath("//button[@aria-label='Next page']"));

                    click(By.xpath("//button[@aria-label='Next page']"));

            }

        }
    }
}