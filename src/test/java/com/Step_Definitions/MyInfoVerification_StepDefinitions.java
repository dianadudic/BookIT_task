package com.Step_Definitions;

import com.Pages.DashBoardPage;
import com.Pages.LoginPage;
import com.Pages.Self_Page;
import com.utility.BrowserUtils;
import com.utility.DB_Utility;
import com.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.hc.core5.util.Asserts;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MyInfoVerification_StepDefinitions {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    Self_Page self_page = new Self_Page();
    Actions actions = new Actions(Driver.getDriver());

    @Given("user is on the login page and logs in")
    public void user_Is_On_The_Login_Page_And_Logs_In() {
        loginPage.logInToBookIt();
        BrowserUtils.wait(5);
    }

    @Then("user hover over to the my button and select self button")
    public void user_hover_over_to_the_my_button_and_select_self_button() {

        dashBoardPage.myDropdown.click();
        dashBoardPage.myDropdown_Self.click();

    }

    @Then("user sees myInfo")
    public void user_sees_my_info() {

        DB_Utility.createConnection();
        DB_Utility.runQuery("SELECT U.firstname || ' ' || U.lastname  AS Fullname,U.role,T.name,T.batch_number,C.location FROM users U INNER JOIN team T ON U.team_id = T.id INNER JOIN campus C ON T.campus_id = C.id WHERE firstname = 'Donia'");

        List<String> listOfExpectedDATA = new ArrayList<>();
        for (String expectedDATA : DB_Utility.getRowDataAsList(1)){
            listOfExpectedDATA.add(expectedDATA);
        }

        System.out.println("listOfExpectedDATA = " + listOfExpectedDATA);

        List<String> listOfActualDATA = new ArrayList<>();

        for (WebElement actualDATA: self_page.myInfo.subList(0,5)){
            listOfActualDATA.add(actualDATA.getText());
        }

        System.out.println("listOfActualDATA = " + listOfActualDATA);


       Assert.assertTrue(listOfActualDATA.contains(listOfExpectedDATA));


    }



}
