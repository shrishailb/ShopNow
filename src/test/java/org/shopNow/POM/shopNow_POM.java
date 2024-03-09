package org.shopNow.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class shopNow_POM {
    public shopNow_POM(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@type='text']")
    private WebElement flipkart_searchfiled;
    public WebElement getFlipkart_searchfiled(){
        return flipkart_searchfiled;
    }

    @FindBy(xpath = "((//div[@class='_13oc-S'])[3]//div//div)[15]")
    private WebElement flipkart_laptop;
    public WebElement getFlipkart_laptop(){
        return flipkart_laptop;
    }
    @FindBy(xpath = "(//div[@class='aMaAEs']//child::span[1])[1]")
    private WebElement item_text;
    public WebElement getItem_text(){
        return item_text;
    }
    @FindBy(xpath = "//div[@class='_1p3MFP dTTu2M']//child::li[1]")
    private WebElement AddtoCart;
    public WebElement getFlipkart_addtocart(){
        return AddtoCart;
    }

    @FindBy(xpath = "(//div[@class='_3fSRat']//child::div[1])[1]")
    private WebElement CartItem_text;
    public WebElement getCartItem_text(){
        return CartItem_text;
    }

    @FindBy(xpath = "//span[contains(text(),'Place Order')]")
    private WebElement PlaceOrder_button;
    public WebElement getPlaceOrder_button(){
        return PlaceOrder_button;
    }
    @FindBy(xpath = "//input")
    private WebElement EnterEmail_Mobile_input;
    public WebElement getEnterEmail_Mobile_input(){
        return EnterEmail_Mobile_input;
    }

    @FindBy(xpath = "//button")
    private WebElement Continue_or_Login_button;
    public WebElement getContinue_or_Login_button(){
        return Continue_or_Login_button ;
    }

    @FindBy(xpath = "(//input)[2]")
    private WebElement OTP_input;
    public WebElement getOTP_input(){
        return OTP_input;
    }

    @FindBy(xpath = "//div[contains(text(),'Add a')]")
    private WebElement Add_new_address;
    public WebElement getAdd_new_address(){
        return Add_new_address;
    }

    @FindBy(xpath="//div[@class='N5Ijry']//input")
    private List<WebElement> Address_fields;;
    public List<WebElement> getAddress_fields(){
        return Address_fields;
    }

    @FindBy(xpath = "//textarea")
    private WebElement Address_textbox;
    public WebElement getAddress_textbox(){
        return Address_textbox;
    }

    @FindBy(xpath = "//select")
    private WebElement state_dropdown;
    public WebElement getState_dropdown(){
        return state_dropdown;
    }


    @FindBy(xpath="//button[@class='_2KpZ6l _1JDhFS _3AWRsL']")
    private WebElement Save_DeliveryHere_button;
    public WebElement getSave_DeliveryHere_button(){
        return Save_DeliveryHere_button;
    }

    @FindBy(xpath="//div[@class='_2-uG6-']")
    private WebElement OrderSummmary_item;
    public WebElement getOrderSummmary_item(){
        return OrderSummmary_item;
    }

    @FindBy(xpath = "//span[@id='to-payment']")
    private WebElement OrderSummary_continue_button;
    public WebElement getOrderSummary_continue_button(){
        return OrderSummary_continue_button;
    }

    @FindBy(xpath="//button[contains(text(),'Accept')]")
    private WebElement openboxDelivery_accept_button;
    public WebElement getOpenboxDelivery_accept_button(){
        return openboxDelivery_accept_button;
    }

    @FindBy(xpath = "//label[@for='CREDIT']")
    private WebElement Creditcard;
    public WebElement getCreditcard(){
        return Creditcard;
    }










}
