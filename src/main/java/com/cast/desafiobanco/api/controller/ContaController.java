package com.cast.desafiobanco.api.controller;

import com.cast.desafiobanco.api.domain.Conta;
import com.cast.desafiobanco.api.dto.ContaDto;
import com.cast.desafiobanco.api.dto.ResponseDto;
import com.cast.desafiobanco.api.dto.TransferenciaDto;
import com.cast.desafiobanco.api.service.ContaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cast.desafiobanco.api.shared.Constantes.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contas")
@CrossOrigin(origins = "*")
@Slf4j
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping
    public ResponseEntity<List<Conta>> listar() {
        log.info("Request to get all contas");
        return ResponseEntity.ok(contaService.findAll());
    }

    @GetMapping("/{numeroDaConta}")
    public ResponseEntity<Conta> buscarPorNumeroDaConta(@PathVariable Long numeroDaConta) {
        log.info("Request to find conta by numero da conta: {}", numeroDaConta);
        Conta conta = contaService.findByNumeroConta(numeroDaConta);
        return ResponseEntity.ok().body(conta);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> criar(@Valid @RequestBody ContaDto contaDto) {
        log.info("Request to create conta: {}", contaDto);
        Conta conta = toEntity(contaDto);
        conta.setNumeroConta(contaService.gerarNumeroDaConta());
        contaService.vereficarLimiteAoCriarConta(conta);
        contaService.create(conta);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(MENSAGEM_DE_SUCESSO_AO_CRIAR_CONTA, conta));
    }

    @PutMapping("/depositos/{numeroDaConta}/{valor}")
    public ResponseEntity<ResponseDto> depositar(@PathVariable Long numeroDaConta, @PathVariable Double valor) {
        Conta conta = contaService.findByNumeroConta(numeroDaConta);
        contaService.depositar(conta, valor);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseDto(MENSAGEM_DE_SUCESSO_AO_DEPOSITAR));
    }

    @PutMapping("/saques/{numeroDaConta}/{valor}")
    public ResponseEntity<ResponseDto> sacar(@PathVariable Long numeroDaConta, @PathVariable Double valor) {
        Conta conta = contaService.findByNumeroConta(numeroDaConta);
        contaService.vereficarLimiteMinimo(valor);
        contaService.vereficarSaldoNaConta(conta, valor);
        contaService.sacar(conta, valor);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseDto(MENSAGEM_DE_SUCESSO_AO_SACAR));
    }

    @PutMapping("/transferencias")
    public ResponseEntity<ResponseDto> tranferir(@Valid @RequestBody TransferenciaDto transferenciaDto) {
        Conta contaSolicitante = contaService.findByNumeroConta(transferenciaDto.getContaDoSolicitante());
        Conta contaBeneficiario = contaService.findByNumeroConta(transferenciaDto.getContaDoBeneficiario());
        contaService.vereficarSaldoNaConta(contaSolicitante, transferenciaDto.getValor());
        contaService.vereficarLimiteMinimo(transferenciaDto.getValor());
        contaService.transferir(contaSolicitante, contaBeneficiario, transferenciaDto.getValor());
        return ResponseEntity.ok().body(new ResponseDto(MENSAGEM_DE_SUCESSO_AO_TRANSFERIR));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deletar(@PathVariable Long id) {
        contaService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(MENSAGEM_DE_SUCESSO_AO_EXCLUIR));
    }

    private Conta toEntity(ContaDto contaDto) {
        Conta conta = new Conta();
        conta.setSaldo(contaDto.getSaldo());
        conta.setNome(contaDto.getNome());
        conta.setCpf(contaDto.getCpf());
        return conta;
    }
}
