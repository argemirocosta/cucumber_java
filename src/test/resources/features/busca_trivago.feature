# language: pt
@trivago
Funcionalidade: Realizar busca
  Como um usuário
  Eu quero realizar busca
  Para encontra hoteis

Contexto:
  Dado que estou acessando o site do trivago

Esquema do Cenário: Realizar busca de hoteis com filtros
  E escolho a cidade "<cidade>"
  E seleciono o tipo de quarto "<tipoQuarto>"
  Quando clicar no botão Pesquisar
  E seleciono para ordenar por distância
  Então visualizo as acomodações encontradas

Exemplos:
  |     cidade      | tipoQuarto |
  |     Maceio      |   duplo    |
  |     Aracaju     | individual |