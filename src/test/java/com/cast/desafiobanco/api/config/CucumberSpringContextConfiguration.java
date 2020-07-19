package com.cast.desafiobanco.api.config;

import com.cast.desafiobanco.api.DesafioBancoApplication;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
@ContextConfiguration(classes = DesafioBancoApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringContextConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(CucumberSpringContextConfiguration.class);

    @Before
    public void setUp() {
        LOG.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
    }
}
