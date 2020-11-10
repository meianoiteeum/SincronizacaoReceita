# Sincronização Receita

## Pré-Requisito
 * Java 11
 * Maven
 * Arquivo CSV
 
## Projeto
 Projeto standalone para leitura de um arquivo CSV no formato (0101;12225-6;100,00;A) e envio a Receita Federal utilizando um serviço genérico.
 

### Gerar Build
 * Entre na pasta do projeto e execute o comando:
```sh
mvn package
```

### Run
 * Importante passar o parâmetro do arquivo com **input-file=file:**, conforme o exemplo abaixo;
```sh
java -jar SincronizacaoReceita.jar input-file=file:{path_file}
```