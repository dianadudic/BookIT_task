package com.Tests;

import com.utility.DB_Utility;
import org.junit.jupiter.api.Test;

public class Room_Test {

    @Test
    public void roomTest(){
        DB_Utility.createConnection();
        DB_Utility.runQuery("SELECT FIRSTNAME " +
                "FROM USERS WHERE FIRSTNAME = 'Donia'");
    }
}
