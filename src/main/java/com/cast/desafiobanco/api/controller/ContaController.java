package com.cast.desafiobanco.api.controller;

import com.cast.desafiobanco.api.domain.Conta;
import com.cast.desafiobanco.api.domain.DepositoEntreContas;
import com.cast.desafiobanco.api.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
@CrossOrigin(origins = "*")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping
    public ResponseEntity<List<Conta>> listarContas() {
        return ResponseEntity.ok(contaService.findAll());
    }

    @PostMapping
    public ResponseEntity<Conta> criarConta(@RequestBody Conta conta) {
        if (contaService.vereficadorDeLimiteAoCriarConta(conta)){
            return ResponseEntity.status(HttpStatus.CREATED).body(contaService.create(conta));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/depositos/{id}/{valor}")
    public ResponseEntity<Conta> depositar(@PathVariable Long id, @PathVariable Double valor ) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(contaService.depositar(id,valor));
    }

    @PutMapping("/saques/{id}/{valor}")
    public ResponseEntity<Conta> sacar(@PathVariable Long id, @PathVariable Double valor ) {
        if(valor > 500) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(contaService.sacar(id,valor));
    }

    @PutMapping("/depositos")
    public ResponseEntity depositarEntreContas(@RequestBody DepositoEntreContas depositoEntreContas) {

        Optional<Conta> contaSolicitante = contaService.findById(depositoEntreContas.getContaSolicitante());

        Optional<Conta> contaBeneficiario = contaService.findById(depositoEntreContas.getContaDoBeneficiario());

        if (contaService.vereficadorDeLimiteDeDeposito(depositoEntreContas.getValor())) {
            return ResponseEntity.badRequest().build();
        }

        if ( !contaSolicitante.isPresent() || !contaBeneficiario.isPresent() ) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(contaService
                .depositarEntreContas(contaSolicitante.get(),contaBeneficiario.get(), depositoEntreContas.getValor()));
    }
}
