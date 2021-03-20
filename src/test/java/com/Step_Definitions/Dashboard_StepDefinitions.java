package com.Step_Definitions;

import com.Pages.DashBoardPage;
import com.Pages.LoginPage;
import com.utility.BrowserUtils;
import com.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Dashboard_StepDefinitions {
   LoginPage loginPage = new LoginPage();
   DashBoardPage dashBoardPage = new DashBoardPage();

    @Given("User is on dashboard")
    public void user_is_on_dashboard() {
        loginPage.logInToBookIt();

        BrowserUtils.wait(5);

    }
    @Then("User can see all rooms")
    public void user_can_see_all_rooms(List<String> rooms) {

        for (WebElement eachRoom: dashBoardPage.Rooms){
            System.out.println("eachRoom = " + eachRoom.getText());
        }

    }
}
