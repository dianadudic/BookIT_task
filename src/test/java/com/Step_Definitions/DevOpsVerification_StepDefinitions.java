package com.Step_Definitions;

import com.Pages.DashBoardPage;
import com.Pages.Team_Page;
import com.utility.BrowserUtils;
import com.utility.DB_Utility;
import com.utility.Driver;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class DevOpsVerification_StepDefinitions {

    DashBoardPage dashBoardPage = new DashBoardPage();
    Team_Page team_page = new Team_Page();


    @Then("user hover over to the my button and select team button")
    public void user_hover_over_to_the_my_button_and_select_team_button() {

        WebDriverWait driverWait = new WebDriverWait(Driver.getDriver(),10);
        driverWait.until(ExpectedConditions.visibilityOf(dashBoardPage.myDropdown));
        dashBoardPage.myDropdown.click();

        driverWait.until(ExpectedConditions.visibilityOf(dashBoardPage.myDropdown_Team));
        dashBoardPage.myDropdown_Team.click();
    }


    @Then("user sees team members")
    public void user_sees_team_members() {

        DB_Utility.createConnection();
        DB_Utility.runQuery("select firstname, role\n" +
                "from users\n" +
                "where firstname in  ( 'Tabor' ,'Ola','Donia','Opal')\n" +
                "order by (case  firstname when 'Tabor' then 1 when 'Ola' then 2 when 'Donia' then 3 when 'Opal' then 4 ELSE 100 END )asc");



        System.out.println("DB_Utility.getAllRowAsListOfMap() = " + DB_Utility.getAllRowAsListOfMap());

        List<String> listOfMembers = new ArrayList<>();
        for (WebElement eachMember: team_page.devopsTeam){
            listOfMembers.add(eachMember.getText());
        }

        System.out.println("listOfMembers = " + listOfMembers);

    }


}
