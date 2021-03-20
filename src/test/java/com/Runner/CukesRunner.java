package com.Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
        "html:target/cucumber-report.html",
        "json:target/cucumber.json",
        "rerun:target/rerun.txt"},
        features = "src/test/resources/Features",
        glue = "com/Step_Definitions",
        dryRun = false,
        tags = "@myinfo"
)

public class CukesRunner {
}
