package com.Pages;

import com.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashBoardPage{

    public DashBoardPage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//h1[@class='title']")
    public WebElement DashBoardTitle;

    @FindBy(xpath = "//div[@class='map']")
    public  WebElement Map;

    @FindBy(xpath = "//span[contains(@class,'room-name')]")
    public List<WebElement> Rooms;

    @FindBy(xpath = "//div[@class='navbar-menu is-transparent']/div[1]/div[2]")
    public WebElement myDropdown;

    @FindBy(xpath = "//div[@class='navbar-menu is-transparent']/div[1]/div[2]/div/a[1]")
    public WebElement myDropdown_Self;


}
