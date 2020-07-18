package com.cast.desafiobanco.api.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = {"src/test/resources/features"},
        glue = {"com.cast.desafiobanco.api.config","com.cast.desafiobanco.api.steps"})
public class CucumberRunnerTest {
}
