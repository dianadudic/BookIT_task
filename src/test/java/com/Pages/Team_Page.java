package com.Pages;

import com.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team_Page {

    public Team_Page() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//p[.='name']/preceding-sibling::p")
    public List<WebElement> teamMembersName;

    @FindBy(xpath = "//p[.='role']/preceding-sibling::p")
    public List<WebElement> teamMembersRole;

    }

