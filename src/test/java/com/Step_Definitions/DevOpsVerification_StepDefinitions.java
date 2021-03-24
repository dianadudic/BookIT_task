package com.Step_Definitions;

import com.Pages.DashBoardPage;
import com.Pages.Team_Page;
import com.utility.BrowserUtils;
import com.utility.DB_Utility;
import com.utility.Driver;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
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
                "where lastname in  ( 'Dullingham' ,'Grills','Fisby','Cave')\n" +
                "order by (case  firstname when 'Dullingham' then 1 when 'Grills' then 2 when 'Fisby' then 3 when 'Cave' then 4 ELSE 100 END )asc ;");

        List<String> devOpsMemberName = new ArrayList<>();

        for(WebElement eachName : team_page.teamMembersName){
            devOpsMemberName.add(eachName.getText());
            Collections.sort(devOpsMemberName);
        }
        System.out.println("devOpsMemberName = " + devOpsMemberName);


        List<String> devOpsMemberRole = new ArrayList<>();

        for(WebElement eachRole : team_page.teamMembersRole){
            devOpsMemberRole.add(eachRole.getText());
            Collections.sort(devOpsMemberRole);
        }
        System.out.println("devOpsMemberRole = " + devOpsMemberRole);


        System.out.println("=================================================================");


        List<String> memberNameBySql = new ArrayList<>();

       for(String nameFromSql : DB_Utility.getColumnDataAsList(1)){
           memberNameBySql.add(nameFromSql);
           Collections.sort(memberNameBySql);
       }
        System.out.println("memberNameBySql = " + memberNameBySql);


       List<String> memberRoleBySql = new ArrayList<>();

       for (String roleFromSql : DB_Utility.getColumnDataAsList(2)) {
           memberRoleBySql.add(roleFromSql);
           Collections.sort(memberRoleBySql);
       }
        System.out.println("memberRoleBySql = " + memberRoleBySql);

        Assert.assertTrue(devOpsMemberName.equals(memberNameBySql));

        Assert.assertTrue(devOpsMemberRole.equals(memberRoleBySql));





    }


}
