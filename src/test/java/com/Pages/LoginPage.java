package com.Pages;

import com.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//input[@name='email']")
    public WebElement emailInput;

    @FindBy(css = "input[name='password']")
    public WebElement passwordInput;

    @FindBy (css = "button[class='button is-dark']")
    public WebElement signInButton;


}
