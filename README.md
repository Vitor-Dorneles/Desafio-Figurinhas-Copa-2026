# Figurinhas Copa 26

Programa em Java para gerenciar a troca de figurinhas de álbum entre duas pessoas, é possível cadastrar as figurinhas repetidas e desejadas, persiste tudo em arquivos CSV, e identifica automaticamente possíveis trocas ao comparar sua lista com a de outra pessoa.

Projeto acadêmico final do terceiro semestre (disciplina de Estruturas de Dados), reorganizado para aplicar separação de responsabilidades (padrão MVC) e boas práticas de Programação Orientada a Objetos.

## Funcionalidades

- Cadastro de figurinhas repetidas e desejadas, com persistência automática em CSV
- Listagem das figurinhas cadastradas
- Comparação com o arquivo CSV de outra pessoa, identificando automaticamente as figurinhas que podem ser trocadas entre as duas partes
- Os dados são recarregados automaticamente toda vez que o programa é iniciado

## Separação MVC

**Por que essa separação:** cada camada tem uma responsabilidade só. `model` sabe comparar e descrever uma figurinha; `persistence` sabe ler e escrever arquivo, sem saber nada de regra de negócio; `view` só mostra informação e lê entrada, só interage com o usuário. `controller` decide o que fazer a cada opção do menu, chamando as outras camadas. Isso deixa cada parte fácil de entender (e de testar) isoladamente.

## Como rodar

Requer Java 17+.

## **Pelo terminal**
### Para compilar:
javac -d out -sourcepath src/main/java src/main/java/figurinhas/app/Main.java

### Para executar:
java -cp out figurinhas.app.Main

Execute a partir da raiz do projeto (os CSVs são lidos da pasta `dados/`).
