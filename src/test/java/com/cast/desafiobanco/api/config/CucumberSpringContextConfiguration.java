package com.cast.desafiobanco.api.config;

import com.cast.desafiobanco.api.DesafioBancoApplication;
import com.cast.desafiobanco.api.domain.Conta;
import com.cast.desafiobanco.api.dto.ContaDto;
import com.cast.desafiobanco.api.dto.TransferenciaDto;
import com.cast.desafiobanco.api.repository.ContaRepository;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;

@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@CucumberContextConfiguration
@ContextConfiguration(classes = DesafioBancoApplication.class, loader = SpringBootContextLoader.class)
@Slf4j
public class CucumberSpringContextConfiguration {

    @Autowired
    ContaRepository contaRepository;

    @DataTableType
    public ContaDto contaDtoDataTable(Map<String,String> map) {
        return new ContaDto(
            map.get("Nome"),
            map.get("Cpf"),
            Double.parseDouble(map.get("Saldo"))
        );
    }
    @DataTableType
    public Conta contaDataTable(Map<String, String> map) {
        return new Conta(
                "teste",
                Double.parseDouble(map.get("Saldo")),
                "0532266644",
                Long.parseLong(map.get("Numero Conta"))
                );
    }
    @DataTableType
    public TransferenciaDto tranferenciaDataTable(Map<String,String> map) {
        return new TransferenciaDto(
          Long.parseLong(map.get("Conta do Solicitante")),
          Double.parseDouble(map.get("Valor")),
          Long.parseLong(map.get("Conta do Beneficiário"))
        );
    }

    @Before
    public void setUp() {
        log.info("Cleaning database");
        contaRepository.deleteAll();
        log.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
    }
}
