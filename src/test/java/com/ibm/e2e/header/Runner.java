package com.ibm.e2e.header;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, plugin = {"pretty"}, features = "src/test/java/com/ibm/e2e/header/features")
public class Runner {
}
