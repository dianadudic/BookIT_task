package com.Step_Definitions;

import com.Pages.DashBoardPage;
import com.Pages.LoginPage;
import com.utility.BrowserUtils;
import com.utility.ConfigurationReader;
import com.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Login_Step_Definitions {

    WebDriver driver;

    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("bookItUrl"));
    }

    @When("user enters {string} and {string}")
    public void user_enters_and(String username, String password) {

        loginPage.emailInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);

    }

    @Then("user click the login button")
    public void user_click_the_login_button() {
        loginPage.signInButton.click();
    }

    @Then("user on the {string}")
    public void userOnThe(String TitleOfDashboard) {

        BrowserUtils.wait(3);
        Assert.assertTrue(dashBoardPage.DashBoardTitle.getText().equals(TitleOfDashboard));


    }


}
