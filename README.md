# Sale ticket

## Descrição
Este é um projeto demonstração de um sistema de venda de ingressos, o intuito é simular o fluxo básico de venda de ingressos para servir como portfólio para avaliação de conhecimentos de backend e arquitetura de software.
Durante este projeto, será utilizado alguns conhecimentos adquiridos durante o tempo de experiência profissional, este Readme será atualizado conforme o andamento do projeto com documentação e informações relevantes para o entendimento do projeto e avaliação do mesmo.

## Funcionalidades
- Mostrar os ingressos disponíveis.
- Gerar um dado de pagamento (PIX), para simular o pagamento dos ingressos.
- Enviar o dado de pagamento para o email do usuário.
- Mostrar os ingressos comprados pelo usuário e seus respectivos status.

## Definições com base nas funcionalidades descritas
- **Fluxo principal:**
  A partir da descrição das funcionalidades, foi desenvolvido um diagrama de sequência para representar o fluxo principal do sistema e pode ser encontrado clicando [aqui](./docs/sequence-diagram.md).

- **Banco de dados:**
  Por se tratar se um fluxo simples e como demonstração não foi levantado muitas hipóteses de uso para melhor definir o banco de dados, o formato do banco de dados foi o relacional, para facilitar o entendimento da solução aplicada.
  > 📝 ***Obs.: Pelo mesmo motivo de ser um projeto de demonstração, não foi aplicado as melhores práticas de modelagem de banco de dados, como normalização e otimização de queries já que o foco é a demonstração do fluxo principal.***
  
  A solução de banco de dados pode ser encontrada clicando [aqui](./docs/database.md).

- **Arquitetura:**
  Sobre a a arquitetura, foi definida uma arquitetura relativamente simples mas bem divida em camadas, pois já conseguiremos com esse desenho separar as responsabilidades e facilitar a manutenção e evolução do sistema.
  > 📝 ***Obs.: Apesar de já saber que se fosse aplicar uma solução em um projeto real, deveria ser aplicado algumas medidas de segurança tando na base quanto na arquitetura como idempotência, rate limit, autenticação e autorização entre outros pontos que devem ser avaliado para o cenário, mas como o projeto é para fins de portifólio e didatico não nos preocuparemos com isso.***

  O desenho da arquitetura pode ser encontrado clicando [aqui](./docs/architecture.md).

- **Tecnologias**
  As tecnologias utilizadas para o desenvolvimento do projeto forma escolhidas para demonstrar conhecimentos em tecnologias atuais e que estão em alta no mercado.
de modo que podemos destacar algumas delas:
  - **Linguagem de programação:** Java 17
  - **Framework:** Spring Boot
  - **Banco de dados:** PostgreSQL
  - **Orquestrador de fila:** Apache Kafka
  - **Gateway de pagamento:** Nenhum, será mockado na camada de serviço externo.
  - **Email provider:** Nenhum, será mockado na camada de serviço externo.
  
  Além dessas tecnologias, estaremos utilizando outras ferramentas para facilitar o desenvolvimento e testes, como por exemplo, Docker, Docker Compose, Testcontainers, Junit, Mockito, entre outros, Lombok etc.

## Executando o projeto

### Requisitos
- Instalar o [Docker](https://docs.docker.com/get-docker/)
- Instalar o [Docker Compose](https://docs.docker.com/compose/install/)
- Se estiver usando o VsCode, instalar a DevContainer para facilitar o desenvolvimento ou
- Instalar o [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- Instalar o [Maven](https://maven.apache.org/download.cgi)
- Instalar o [PostgreSQL](https://www.postgresql.org/download/)
- Instalar o [Apache Kafka](https://kafka.apache.org/downloads)

### Executando o projeto
- Rodar o comando `docker-compose up` na raiz do projeto para subir o banco de dados, orquestrador de fila e outras ferramentas necessárias para o desenvolvimento.
- Se estiver usando o VsCode, digittar command + shift + p e selecionar a opção `Remote-Containers: Reopen in Container` para abrir o projeto na DevContainer.

- Verificando se o banco de dados está rodando, acessar o banco de dados com as seguintes credenciais:
  - **Host:** localhost
  - **Port:** 5432
  - **User:** postgres
  - **Password:** postgres
- Verificando se o orquestrador de fila está rodando, acessar o orquestrador de fila com as seguintes credenciais:
  - **URL:** localhost:9021