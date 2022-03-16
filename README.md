# JOGO DOS OITO

Implementação OO em Java do jogo dos 8.

## O Jogo
O jogo consiste em ordenar corretamente os números de uma matriz 3x3 
fazendo movimentos para qualquer um dos lados que estiverem disponíveis, 
ou seja, qualquer número que fizer fronteira com o espaço vazio da matriz
poderá trocar de lugar com o espaço vazio.

Ex:

```
8 5 6                        8 5 6                      1 2 3 
7 2 3  Após mover número 3:  7 2     Finalizando jogo:  4 5 6
4 1         -------->        4 1 3      -------->       7 8
```

### Rodando o jogo utilizando o Maven e Java 11

- Primeiro faça o clone do projeto: 
```
git clone https://github.com/andersoncrocha/jogo-dos-oito
```

- Compile e gere o jar do projeto:
```
mvn clean install
```

- Executar o jar:
```
java -jar target/jogo-dos-oito-1.0-SNAPSHOT.jar
```