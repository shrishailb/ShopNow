package org.shopNow.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.shopNow.POM.shopNow_POM;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class shopNow_step {

    WebDriver driver;
    public shopNow_POM shop;
    public String item_text;

    @Given("User opens the Flipkart website {string} {string}")
    public void user_opens_the_flipkart_website(String URL, String Browser) {
        switch (Browser){ // here we are launching the browser
            case "Chrome":
                WebDriverManager.chromedriver().setup(); // Chrome browser initialization
                ChromeOptions ch= new ChromeOptions();
                ch.addArguments("--remote-allow-origins=*");
                driver=new ChromeDriver(ch);
                driver.get(URL);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.manage().window().fullscreen();
                break;
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();

                DesiredCapabilities capabilities = new DesiredCapabilities();
               // capabilities.setCapability("marionette", true);
                capabilities.acceptInsecureCerts();


                FirefoxOptions options = new FirefoxOptions();
                options.merge(capabilities);
                options.addPreference("browser.link.open_newwindow", 3);
                options.addPreference("browser.link.open_newwindow.restriction", 0);
                driver=new FirefoxDriver(options);
                driver.get(URL);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                driver.manage().window().fullscreen();
                break;
            case "Edge":
                WebDriverManager.edgedriver().setup();   // Edge browser initialization
                EdgeOptions ed= new EdgeOptions();
                ed.addArguments("--remote-allow-origins=*");
                driver=new EdgeDriver(ed);
                driver.get(URL);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.manage().window().fullscreen();
                break;
            case "IE":
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions ie= new InternetExplorerOptions();   //IE browser initialization
                ie.addCommandSwitches("--remote-allow-origins=*");
                driver=new InternetExplorerDriver(ie);
                driver.get(URL);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                driver.manage().window().fullscreen();
                break;

            default:
                System.out.println("Please provide correct browser name");
                break;
        }

    }
    @When("User search and add to cart")
    public void user_search_and_add_to_cart() throws InterruptedException {
        shop= new shopNow_POM(driver);  // POM class object created

        Assert.assertTrue(shop.getFlipkart_searchfiled().isDisplayed()); // Assertion for checking item is present or not
        shop.getFlipkart_searchfiled().sendKeys("laptop");  // Searching laptop in search field
        shop.getFlipkart_searchfiled().sendKeys(Keys.ENTER);     // Enter action is performed
        fluentWait_visible(driver,shop.getFlipkart_laptop());  // Fluentwait is used to wait until element is visible
        String currentWindow = driver.getWindowHandle();

        fluentWait_untilClickable(driver,shop.getFlipkart_laptop());  //Fluentwait is used to wait until element is clickable
       
        Action_mouseover(driver,shop.getFlipkart_laptop()); // Click action is performed
        //shop.getFlipkart_laptop().click();

        Set<String> window = driver.getWindowHandles(); // Window switch is performed

        for(String w:window){
            System.out.println(w);
           
            driver.switchTo().window(w);
            }

        item_text=shop.getItem_text().getText(); // Getting item text field for future validation
        }



    @Then("proceed to checkout")
    public void proceed_to_checkout() throws InterruptedException {
        try {
            shop = new shopNow_POM(driver);  // POM class object created
            fluentWait_visible(driver, shop.getFlipkart_addtocart());  // Fluentwait is used to wait until element is visible
            Assert.assertTrue(shop.getFlipkart_addtocart().isDisplayed()); // Assertion for checking item is present or not
            Action_mouseover(driver,shop.getFlipkart_addtocart());   // Click action is performed

            fluentWait_visible(driver, shop.getCartItem_text());// Fluentwait is used to wait until element is visible
            Assert.assertTrue(shop.getCartItem_text().isDisplayed()); // Assertion for checking item is present or not
            String Cart_Item = shop.getCartItem_text().getText();
            Assert.assertTrue(item_text.contains(Cart_Item));
            fluentWait_untilClickable(driver,shop.getPlaceOrder_button());

            Action_mouseover(driver,shop.getPlaceOrder_button()); // Click action is performed
        }catch (Exception e){
            driver.navigate().refresh();
            fluentWait_visible(driver, shop.getFlipkart_addtocart());
            Assert.assertTrue(shop.getFlipkart_addtocart().isDisplayed());  // Assertion for checking item is present or not
            fluentWait_untilClickable(driver,shop.getFlipkart_addtocart());

            Action_mouseover(driver,shop.getFlipkart_addtocart());  // Click action is performed
            fluentWait_visible(driver, shop.getCartItem_text());
            Assert.assertTrue(shop.getCartItem_text().isDisplayed());  // Assertion for checking item is present or not
            String Cart_Item = shop.getCartItem_text().getText();
            Assert.assertTrue(item_text.contains(Cart_Item));
            fluentWait_visible(driver, shop.getPlaceOrder_button());
            Assert.assertTrue(shop.getPlaceOrder_button().isDisplayed());  // Assertion for checking item is present or not
            fluentWait_untilClickable(driver,shop.getPlaceOrder_button());
            
            Action_mouseover(driver,shop.getPlaceOrder_button()); // Click action is performed

        }

    }
    @Then("verify the user authentication {string}")
    public void verify_the_user_authentication(String Username)  {
        try {
            shop= new shopNow_POM(driver);
            fluentWait_visible(driver,shop.getEnterEmail_Mobile_input());
            Assert.assertTrue(shop.getEnterEmail_Mobile_input().isDisplayed());  // Assertion for checking item is present or not
            Thread.sleep(5000);
            shop.getEnterEmail_Mobile_input().sendKeys(Username); //entering username
            fluentWait_untilClickable(driver,shop.getContinue_or_Login_button());

            Assert.assertTrue(shop.getContinue_or_Login_button().isDisplayed()); // Assertion for checking item is present or not
            Action_mouseover(driver,shop.getContinue_or_Login_button()); // Click action is performed


            System.out.println();
            System.out.println("\033[0;1m" +"Please enter the OTP Manually");
            Thread.sleep(15000);

            fluentWait_visible(driver,shop.getContinue_or_Login_button());
            fluentWait_untilClickable(driver,shop.getContinue_or_Login_button());
            Assert.assertTrue(shop.getContinue_or_Login_button().isDisplayed());  // Assertion for checking item is present or not
            shop.getContinue_or_Login_button().click(); // Click action is performed
        } catch (Exception e) {
            System.out.println("\033[0;1m" +"The Application is not behaving as expected and its an application issue");
        }

    }
    @Then("add the shipping information")
    public void add_the_shipping_information() {
        shop= new shopNow_POM(driver);
        fluentWait_visible(driver,shop.getAdd_new_address()); //fluent wait
        Assert.assertTrue(shop.getAdd_new_address().isDisplayed());  // Assertion for checking item is present or not
        fluentWait_untilClickable(driver,shop.getAdd_new_address());
        shop.getAdd_new_address().click();  //click action is performed
        List<WebElement> fields = shop.getAddress_fields(); // Getting all address fields

        fields.get(1).sendKeys("Shrishail");
        fields.get(2).sendKeys("8660484759");
        fields.get(3).sendKeys("560099");
        fields.get(4).sendKeys("Hebbagudi");
        fields.get(8).click();

        fluentWait_visible(driver,shop.getSave_DeliveryHere_button());
        fluentWait_untilClickable(driver,shop.getSave_DeliveryHere_button());
        shop.getSave_DeliveryHere_button().click(); // Click action is performed


    }
    @Then("proceed to payment method")
    public void proceed_to_payment_method() {
        shop= new shopNow_POM(driver);
        fluentWait_visible(driver,shop.getCreditcard());
        Assert.assertTrue(shop.getCreditcard().isDisplayed()); // Assertion for checking item is present or not
        shop.getCreditcard().click(); // click action is performed

    }
    @Then("review the order")
    public void review_the_order() {
        shop= new shopNow_POM(driver);
        fluentWait_visible(driver,shop.getOrderSummmary_item()); // fluent wait for element to visible
        String item =shop.getOrderSummmary_item().getText();  // getting text of element
        Assert.assertTrue(item_text.contains(item)); //verifying the element is the one which we have added to the cart
        fluentWait_visible(driver,shop.getOrderSummary_continue_button());
        fluentWait_untilClickable(driver,shop.getOrderSummary_continue_button());
        shop.getOrderSummary_continue_button().click();  // click action is performed

        fluentWait_visible(driver,shop.getOpenboxDelivery_accept_button()); //fluentwait
        fluentWait_untilClickable(driver,shop.getOpenboxDelivery_accept_button());
        shop.getOpenboxDelivery_accept_button().click();  //click action is performed

    }

    public static void fluentWait_visible(WebDriver driver, WebElement ele){
        FluentWait wait = new FluentWait(driver);
        wait.pollingEvery(Duration.ofSeconds(2));
        wait.withTimeout(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(ele));

    }

    public static void fluentwait_invisiblity(WebDriver driver, WebElement ele){
        FluentWait wait = new FluentWait(driver);
        wait.pollingEvery(Duration.ofSeconds(2));
        wait.withTimeout(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public static void fluentWait_untilClickable(WebDriver driver, WebElement ele){
        FluentWait wait = new FluentWait(driver);
        wait.pollingEvery(Duration.ofSeconds(2));
        wait.withTimeout(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }


    public static void Action_mouseover(WebDriver driver, WebElement ele){
        Actions ac= new Actions(driver);
        ac.moveToElement(ele).click().perform();
    }



}
