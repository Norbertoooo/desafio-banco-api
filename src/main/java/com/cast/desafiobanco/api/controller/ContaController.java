package com.cast.desafiobanco.api.controller;

import com.cast.desafiobanco.api.domain.Conta;
import com.cast.desafiobanco.api.dto.ContaDto;
import com.cast.desafiobanco.api.dto.ResponseDto;
import com.cast.desafiobanco.api.dto.TransferenciaDto;
import com.cast.desafiobanco.api.service.ContaService;
import com.cast.desafiobanco.api.shared.Constantes;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ContaController extends Constantes{

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
        Conta conta = toEntity(contaDto);
        conta.setNumeroConta(contaService.geradorDeNumeroDaConta());
        contaService.vereficadorDeLimiteAoCriarConta(conta);
        contaService.create(conta);
        return ResponseEntity.status(HttpStatus.CREATED)
               .body(new ResponseDto(MENSAGEM_DE_SUCESSO_AO_CRIAR_CONTA, conta));
    }

    @PutMapping("/depositos/{numeroDaConta}/{valor}")
    public ResponseEntity<ResponseDto> depositar(@PathVariable Long numeroDaConta, @PathVariable Double valor ) {
        contaService.depositar(numeroDaConta, valor);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ResponseDto(MENSAGEM_DE_SUCESSO_AO_DEPOSITAR));
    }

    @PutMapping("/saques/{numeroDaConta}/{valor}")
    public ResponseEntity<ResponseDto> sacar(@PathVariable Long numeroDaConta, @PathVariable Double valor ) {
        Conta conta = contaService.findByNumeroConta(numeroDaConta);
        contaService.vereficadorDeLimite(valor);
        contaService.vereficardoDeSaldoNaConta(conta,valor);
        contaService.sacar(numeroDaConta,valor);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body( new ResponseDto(MENSAGEM_DE_SUCESSO_AO_SACAR));
    }

    @PutMapping("/transferencias")
    public ResponseEntity<ResponseDto> tranferir(@Valid @RequestBody TransferenciaDto transferenciaDto) {
        Conta contaSolicitante = contaService.findByNumeroConta(transferenciaDto.getContaDoSolicitante());
        Conta contaBeneficiario = contaService.findByNumeroConta(transferenciaDto.getContaDoBeneficiario());
        contaService.vereficardoDeSaldoNaConta(contaSolicitante,transferenciaDto.getValor());
        contaService.vereficadorDeLimite(transferenciaDto.getValor());
        contaService.transferir(contaSolicitante,contaBeneficiario, transferenciaDto.getValor());
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
