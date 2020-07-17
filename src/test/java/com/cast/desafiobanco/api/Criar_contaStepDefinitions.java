package com.cast.desafiobanco.api;

import com.cast.desafiobanco.api.dto.ContaDto;
import com.cast.desafiobanco.api.exception.Exception;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class Criar_contaStepDefinitions {

    private static final Logger log = Logger.getLogger(Criar_contaStepDefinitions.class.getName());

    private String url = "http://localhost:8081";
    private ContaDto contaDto = new ContaDto();
    private Exception exception = new Exception();
    private RestTemplate restTemplate = new RestTemplate();

    @Dado("^que seja solicitada a criação de uma nova conta com os seguintes valores$")
    public void que_seja_solicitada_a_criação_de_uma_nova_conta_com_os_seguintes_valores(DataTable dataTable) {
        List<String> list = dataTable.asList();
        contaDto.setSaldo(Double.parseDouble(list.get(5)));
        contaDto.setNome(list.get(3));
        contaDto.setCpf(list.get(4));
        log.info(contaDto.toString());
        log.info("Dado:");
    }
    @Quando("^for enviada a solicitação de criação de nova conta$")
    public void for_enviada_a_solicitação_de_criação_de_nova_conta() {
        log.info("Quando:");
        exception = restTemplate.postForObject(url+"/contas",contaDto, Exception.class);
        assertEquals(exception.getStatus().intValue(),400);
    }
    @Então("^deverá ser apresentada a seguinte mensagem de erro (.+)$")
    public void deverá_ser_apresentada_a_seguinte_mensagem_de_erro(String mensagemDeErro) {
        log.info("Então:");
        assertEquals( exception.getTitulo(), mensagemDeErro);
    }
}
