package Zadania29_05_2022;

import Zadania18_05_2022.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Zadanie01 extends TestBase {

    @Test
    public void shouldPick31August2022OnDatePicker(){

        String targetDate= "08/31/2022";
        String targetYear = "2022";
        String targetMonth = "August";
        String space = " ";

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://seleniumui.moderntester.pl/datepicker.php");
        driver.findElement(By.cssSelector("#datepicker")).click();

        String year = driver.findElement(By.cssSelector(".ui-datepicker-year")).getText();
        String month = driver.findElement(By.cssSelector(".ui-datepicker-month")).getText();

        String targetMonthYear = targetMonth+space+targetYear;
        String selectedMonthYear = month+space+year;

        while (!selectedMonthYear.equals(targetMonthYear)){
            if (Integer.parseInt(targetYear)<Integer.parseInt(year)){
                driver.findElement(By.cssSelector(".ui-datepicker-prev"));
            } else {
                driver.findElement(By.cssSelector(".ui-datepicker-next")).click();
            }
            month = driver.findElement(By.cssSelector(".ui-datepicker-month")).getText();
            year = driver.findElement(By.cssSelector(".ui-datepicker-year")).getText();
            selectedMonthYear = month+space+year;
        }
        List<WebElement> days = driver.findElements(By.xpath("//a[contains(text(),'31')]"));
        if (days.size()>1){
            days.get(1).click();
        } else {
            days.get(0).click();
        }
        String finalDate = driver.findElement(By.cssSelector("#datepicker")).getAttribute("value");
        Assert.assertEquals(finalDate,targetDate);
    }

// to przeczytałem https://www.lambdatest.com/blog/how-to-automate-calendar-using-selenium-webdriver-for-testing/
}
