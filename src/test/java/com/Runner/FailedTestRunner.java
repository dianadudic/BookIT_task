package com.Runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "target/rerun.txt",
        glue = "com/Step_Definitions"
)

public class FailedTestRunner {

}
