package com.Pages;

import com.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Self_Page {
    public Self_Page() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

@FindBy(xpath = "//p[@class='title is-6']")
    public List<WebElement> myInfo;

}
