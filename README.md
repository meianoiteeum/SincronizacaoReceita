# Sincronização Receita

## Pré-Requisito
 * Java 11
 * Maven
 * Arquivo CSV
 
## Projeto
Projeto standalone para leitura de arquivo CSV no formato:  
receita.csv
```csv
agencia;conta;saldo;status
0101;12225-6;100,00;
0101;1226-5;-100,00;  
...
```
 
Será processado e enviado a Receita Federal utilizando um serviço genérico.  
Quando enviado, o retorno do método trará um booleano informando se o envio ocorreu com sucesso.  
Esse retorno será gerado um novo arquivo, no mesmo formato, acrescentando essa atualização.  
O nome do arquivo irá retornar com uma sequência de número para representar o dia e hora que foi gerando esse arquivo.  
formato: (nome_do_arquivo)(timestamp).csv  
receita1605100536.csv
```csv
agencia;conta;saldo;status;atualizacao
0101;12225-6;100,00;true
0101;1226-5;-100,00;false
...
``` 

## Lógica
Dividi em pacotes para cada processo do Batch, utilizei duas classes de modelo, um DTO para a classe de entrada e  
uma classe modelo para gerar o objeto que contenha os requisitos necessários para o envio a Receita Federal, pois o objeto de  
entrada não contem o padrão necessário para o envio a Receita.

Criei um CustomReader para receber o DTO e fazer o mapper para o Modelo, após o processamento devido dos atributos, o  
processor irá enviar a Receita Federal o modelo e logo após utilizo o Writer para gerar um novo arquivo e voltar ao  
formato original do arquivo.

Utilizei de log para salvar em arquivo lagumas INFOs e casos de teste unitários.


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