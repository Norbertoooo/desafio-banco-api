#Desafio-banco-api

Esse projeto consiste em um conjunto de features para serem implementadas por candidatos interessados em participar do processo de seleção da empresa.

### O desafio
O desafio consiste em implementar os requisitos funcionais ou features de um banco digital fictício que contemple as operações de criação de novas contas, depósitos, saques e transferências entre contas.

Será exigido do candidato: 
1. Implementar uma API Rest baseada em Spring Boot utilizando Java 8 ou superior seguindo as melhores práticas de qualidade de código. 

2. Implementar (no mínimo uma das duas abaixo): 
  
    2.1. Um conjunto de testes automáticos, que interaja com a API criada no item 1, utilizando o framework Cucumber e as metodologias de desenvolvimento guiado por teste TDD e BDD. 
  
    2.2. Um gateway, que interaja com a API criada no item 1, utilizando a Angular.
        
        https://github.com/Norbertoooo/desafio-banco-gateway

Seguem os requisitos funcionais ou features:

* [Criar Conta](src/test/resources/features/criar_conta.feature) - Feature de criação de uma nova conta no banco
* [Depósito](src/test/resources/features/deposito.feature) - Feature de realização da operação de depósito em conta.
* [Saque](src/test/resources/features/saque.feature) - Feature de realização da operação de saque em conta.
* [Transferência](src/test/resources/features/transferencia.feature) - Feature de realização da operação transferência de valores entre contas.