package com.cast.desafiobanco.api.config;

import com.cast.desafiobanco.api.runner.CucumberRunnerTest;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = CucumberRunnerTest.class)
public class CucumberSpringConfiguration {
}
