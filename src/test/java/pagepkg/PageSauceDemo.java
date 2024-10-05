package pagepkg;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageSauceDemo {
    private WebDriver driver;
    private WebDriverWait wait;

    // Login elements
    @FindBy(id = "user-name")
    private WebElement email;
    
    @FindBy(id = "password")
    private WebElement pass;
    
    @FindBy(id = "login-button")
    private WebElement loginButton;

    // Add to cart elements
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement add1;
    
    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement add2;
    
    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    private WebElement add3;
    
    @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    private WebElement add4;
    
    @FindBy(id = "add-to-cart-sauce-labs-onesie")
    private WebElement add5;
    
    @FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")
    private WebElement add6;

    // Cart, checkout, and continue shopping
    @FindBy(xpath = "//*[@id='shopping_cart_container']/a")
    private WebElement cartIcon;
    
    @FindBy(id = "checkout")
    private WebElement checkout;

    // Purchase details
    @FindBy(id = "first-name")
    private WebElement firstName;
    
    @FindBy(id = "last-name")
    private WebElement lastName;
    
    @FindBy(id = "postal-code")
    private WebElement postalCode;
    
    @FindBy(id = "continue")
    private WebElement continueButton;
    
    @FindBy(xpath="//*[@id=\"finish\"]")
    private WebElement finishButton;
    
    @FindBy(id = "back-to-products")
    private WebElement backButton;

    // Hamburger and logout
    @FindBy(id = "react-burger-menu-btn")
    private WebElement hamburger;
    
    @FindBy(id = "logout_sidebar_link")
    private WebElement logout;

    public PageSauceDemo(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);    
    }

    public void setValues(String username, String password) {
        email.sendKeys(username);
        pass.sendKeys(password);
    }

    public void logIn() {    
        loginButton.click();
    }

    public void scrollAndClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void addToCart() {
        scrollAndClick(add1);
        scrollAndClick(add2);
        scrollAndClick(add3);
        scrollAndClick(add4);
        scrollAndClick(add5);
        scrollAndClick(add6);
    }

    public void goToCart() {
        cartIcon.click();
        checkout.click();
    }

    public void purchase(String firstName, String lastName, String postalCode) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.postalCode.sendKeys(postalCode);
        continueButton.click();
        backButton.click();
        hamburger.click();
        waitUntilVisible(logout).click();
    }

    private WebElement waitUntilVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
