# Steam Games Dataset

## Instruções de Execução

1. Execute o Main.java
2. Após a execução, os arquivos estarão na pasta **dataset/resultados** e **dataset/arquivosCsv**.

## Observaçôes

- Se for executar com os 97444 elementos, vai demorar mais de 1 dia rodando.
- Para diminuir a quantidade de elementos vá em `csv/manuseadorCsv/obterDadosOrdenacao` e mudar a variavel `removerElementos` para o total que deseja remover `(Total de Elementos = 97444)`
- Arquivo Original -> [Aqui](https://www.kaggle.com/datasets/fronkongames/steam-games-dataset)

---

# Justificativas das Estrutura de Dados

## 1. Queue

Local: csv/ManuseadorCSV/converterDatas

1. Uma Queue segue o modelo FIFO, o que é ideal para processar itens em ordem sequencial, como linhas de um arquivo CSV
2. Arrays têm um tamanho fixo no momento da criação. Isso pode ser limitante, especialmente ao lidar com dados cujo tamanho total não é conhecido de antemão.

## 2. LinkedList

Local: Main

- Armazenar os algoritmos sem definir um valor fixo, já que arrays precisam de valores fixos para serem incializados.

## 3. HashMap

Local: csv/ManuseadorCSV/filtrarLinux

Redução de Redundância: Na versão original, são feitas duas passagens nos dados: uma para contar os jogos Linux (count) e outra para preencher o array final. Com o HashMap, essas duas tarefas são unificadas, reduzindo redundâncias.

Acesso Direto: O uso de HashMap permite armazenar e acessar diretamente os índices das linhas de interesse, facilitando a manipulação e possíveis extensões do código.

