# language: pt
@booking
Funcionalidade: Realizar busca
  Como um usuário
  Eu quero realizar busca
  Para encontra hoteis

Contexto:
  Dado que estou acessando o site do booking

Esquema do Cenário: Realizar busca de hoteis com filtros
  E seleciono a cidade "<cidade>"
  E seleciono a quantidade de adultos <qtdAdultos>
  E seleciono a quantidade de crianças <qtdCrianças>
  E seleciono a quantidade de quartos <qtdQuartos>
  Quando clicar em Pesquisar
  Então vejo as acomodações encontradas

Exemplos:
  |     cidade      | qtdAdultos | qtdCrianças | qtdQuartos |
  |     Maceio      |     3      |      1      |      2     |
  |     Aracaju     |     1      |      2      |      1     |
  |     Salvador    |     4      |      3      |      3     |