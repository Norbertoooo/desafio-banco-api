package com.cast.desafiobanco.api.controller;

import com.cast.desafiobanco.api.domain.Conta;
import com.cast.desafiobanco.api.dto.ContaDto;
import com.cast.desafiobanco.api.dto.ResponseDto;
import com.cast.desafiobanco.api.dto.TransferenciaDto;
import com.cast.desafiobanco.api.service.ContaService;
import com.cast.desafiobanco.api.shared.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/contas")
@CrossOrigin(origins = "*")
public class ContaController {

    @Autowired
    private ContaService contaService;

    private static final Logger log = Logger.getLogger(ContaController.class.getName());

    private final Constantes constantes = new Constantes();

    @GetMapping
    public ResponseEntity<List<Conta>> listar() {
        log.info("Request to get all contas");
        return ResponseEntity.ok(contaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarPorId(@PathVariable Long id) {
        Conta conta = contaService.findById(id);
        return ResponseEntity.ok().body(conta);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> criar(@Valid @RequestBody ContaDto contaDto) {
       contaService.vereficadorDeLimiteAoCriarConta(toEntity(contaDto));
       Conta conta =contaService.create(toEntity(contaDto));
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(new ResponseDto(constantes.MENSAGEM_DE_SUCESSO_AO_CRIAR_CONTA, conta));
    }

    @PutMapping("/depositos/{id}/{valor}")
    public ResponseEntity<ResponseDto> depositar(@PathVariable Long id, @PathVariable Double valor ) {
        contaService.depositar(id,valor);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseDto(constantes.MENSAGEM_DE_SUCESSO_AO_DEPOSITAR));
    }

    @PutMapping("/saques/{id}/{valor}")
    public ResponseEntity<ResponseDto> sacar(@PathVariable Long id, @PathVariable Double valor ) {
        Conta conta = contaService.findById(id);
        contaService.vereficadorDeLimite(valor);
        contaService.vereficardoDeSaldoNaConta(conta,valor);
        contaService.sacar(id,valor);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body( new ResponseDto(constantes.MENSAGEM_DE_SUCESSO_AO_SACAR));
    }

    @PutMapping("/transferencias")
    public ResponseEntity<ResponseDto> tranferir(@Valid @RequestBody TransferenciaDto transferenciaDto) {
        Conta contaSolicitante = contaService.findById(transferenciaDto.getContaDoSolicitante());
        Conta contaBeneficiario = contaService.findById(transferenciaDto.getContaDoBeneficiario());
        contaService.vereficadorDeLimite(transferenciaDto.getValor());
        contaService.vereficardoDeSaldoNaConta(contaSolicitante,transferenciaDto.getValor());
        contaService.transferir(contaSolicitante,contaBeneficiario, transferenciaDto.getValor());
        return ResponseEntity.ok().body(new ResponseDto(constantes.MENSAGEM_DE_SUCESSO_AO_TRANSFERIR));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deletar(@PathVariable Long id) {
        contaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(constantes.MENSAGEM_DE_SUCESSO_AO_EXCLUIR));
    }

    private Conta toEntity(ContaDto contaDto) {
        Conta conta = new Conta();
        conta.setId(contaDto.getNumeroDaConta());
        conta.setSaldo(contaDto.getSaldo());
        conta.setNome(contaDto.getNome());
        conta.setCpf(contaDto.getCpf());
        return conta;
    }
}
