package Zadania18_05_2022;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class MyStoreSignIn extends TestBase {

    String URL = "http://146.59.32.4/index.php";

    @Test
    public void shouldSignInWithSuccess() {

        driver.get(URL);
        driver.findElement(By.cssSelector(".user-info")).click();
        driver.findElement(By.cssSelector(".no-account a")).click();

        List<WebElement> radioButtonsList = driver.findElements(By.cssSelector(".radio-inline"));
        clickRandomRadioButton(radioButtonsList);

        driver.findElement(By.cssSelector("[name='firstname']")).sendKeys("Adrian");
        driver.findElement(By.cssSelector("[name='lastname']")).sendKeys("Tester");
        driver.findElement(By.cssSelector("[name='email']")).sendKeys(createRandomEmail());
        driver.findElement(By.cssSelector("[name='password']")).sendKeys("Testpassword2022!");
        driver.findElement(By.cssSelector("[name='birthday']")).sendKeys(addOptionalBirthdateOrLeftItEmpty());

        List<WebElement> checkboxList = driver.findElements(By.cssSelector("[type='checkbox']"));
        for (WebElement checkbox:checkboxList){
            checkbox.click();
        }

        driver.findElement(By.cssSelector("footer.form-footer button[type='submit']")).click();

        String login = driver.findElement(By.cssSelector(".account span")).getText();
        Assert.assertEquals(login,"Adrian Tester");

    }

    public void clickRandomRadioButton(List<WebElement> list) {
        int randomNumber = (int) (Math.random() * list.size());
        list.get(randomNumber).click();
    }

    public String createRandomEmail() {
        int randomNumber = (int) (Math.random() * 9999) + 1;
        String email = "randomemail" + randomNumber + "@test.com";
        return email;
    }

    public String addOptionalBirthdateOrLeftItEmpty() {
        String birthdate = "";
        int randomNumber = (int) (Math.random() * 2);
        switch (randomNumber) {
            case 0:
                birthdate = "";
                break;
            case 1:
                birthdate = "05/31/1970";
                break;
        }
        return birthdate;
    }

}
