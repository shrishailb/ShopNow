package org.shopNow.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "src//test//java//org//shopNow//featureFile", tags="@HTL123",dryRun = false,
glue = "org.shopNow.stepDefinitions",plugin = { "pretty", "html:target/cucumber-reports",
        "json:target/cucumber.json"},
        monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
