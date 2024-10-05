package Testpkg;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Excelutils.Excelutils;
import pagepkg.PageSauceDemo;

public class TestSauceDemo {
    private WebDriver driver;
    private static final String URL = "https://www.saucedemo.com/";
    private static final String EXCEL_PATH = "D:\\Saucedemo.xlsx";
    private static final String SHEET_NAME = "Sheet1";
    private static final String SUCCESS_URL = "https://www.saucedemo.com/inventory.html";

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @Test
    public void test() {
        PageSauceDemo p1 = new PageSauceDemo(driver);
        int row = Excelutils.getRowCount(EXCEL_PATH, SHEET_NAME);
        
        for (int i = 1; i <= row; i++) {
            String username = Excelutils.getCellValue(EXCEL_PATH, SHEET_NAME, i, 0);
            String password = Excelutils.getCellValue(EXCEL_PATH, SHEET_NAME, i, 1);
            
            System.out.println("Attempting login with Username: " + username + " and Password: " + password);
            p1.setValues(username, password);
            p1.logIn();
            
            String acturl = driver.getCurrentUrl();
            if (SUCCESS_URL.equals(acturl)) {
                System.out.println("Login Successful");
                addItemToCart(p1);
            } else {
                System.out.println("Login Unsuccessful");
                resetLoginFields();
            }
        }
    }

    private void addItemToCart(PageSauceDemo p1) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='add-to-cart-sauce-labs-backpack']")));
        p1.addToCart();
        p1.goToCart();
        p1.purchase("nimil", "sunil", "123456");
    }

    private void resetLoginFields() {
        driver.findElement(By.xpath("//*[@id='user-name']")).clear();
        driver.findElement(By.xpath("//*[@id='password']")).clear();
        driver.navigate().refresh();
    }
}

