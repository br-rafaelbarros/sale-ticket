# Sale ticket

## Índice
- [Descrição](#descrição)
- [Definições do projeto](#definições-do-projeto)
- [Executando o projeto](#executando-o-projeto)
- [Verificando](#verificando)
- [Pontos que podem ser observados no código para avaliação](#pontos-que-podem-ser-observados-no-código-para-avaliação)
- [Considerações finais](#considerações-finais)

## Descrição
Este é um projeto demonstração de um sistema de venda de ingressos, o intuito é simular o fluxo básico de venda de ingressos para servir como portfólio para avaliação de conhecimentos de backend e arquitetura de software.
Durante este projeto, será utilizado alguns conhecimentos adquiridos durante o tempo de experiência profissional, este ``Readme`` será atualizado conforme o andamento do projeto com documentação e informações relevantes para o entendimento do projeto e avaliação do mesmo.

## Definições do projeto
Com proposíto de levantar pontos importantes para o entendimento do projeto, apenas com a breve descrição acima, foram levantados as informações acessando o [documento de definições](./docs/definitions.md).

Caso queira visualizar especificaçoes por t'opico, clique nos links abaixo:

- [Funcionalidades](./docs/definitions.md#funcionalidades)
- [Fluxo principal](./docs/definitions.md#fluxo-principal)
- [Banco de dados](./docs/definitions.md#banco-de-dados)
- [Arquitetura](./docs/definitions.md#arquitetura)
- [Tecnologias](./docs/definitions.md#tecnologias)


## Executando o projeto

Para executar o projeto, podemos ter algumas opções de configurações, tudo rodando no docker ou rodando na máquina local. Neste tutorial vamos abordar a execução do projeto rodando no docker. De qualquer forma aqui vai as ferramentas necessárias para rodar o projeto independente da forma que escolher.

- Para verificar as ferramentas necessárias para rodar o projeto, clique [aqui](./docs/run-project.md#ferramentas).

- Para rodar o projeto, clique [aqui](./docs/run-project.md#rodando-o-projeto).

## Verificando

Para verificar se esta tudo rodando corretamente, siga os passos no link [aqui](./docs/run-project.md#verificando).

Se estiver tudo rodando corretamente, acesse a documentação da API no endereço `http://localhost:<PORT>/swagger-ui.html`, onde `<PORT>` é a porta que a aplicação está rodando, por padrão é a porta 8080 ou 8082.

> 📝 **Obs.:** Esta documentação pode também ser usada para testes fique á vontade para validar!

## Pontos que podem ser observados no código para avaliação
- Padrões de projetos:
  - Foi utilizado neste projeto conceitos como DDD, SOLID, Clean Code entre outros.
- Testes:
  - Foram aplicados exemplos básicos de testes unitários somente como demonstração.
- Design do sistema:
  - A arquitetura foi pensada para ser escalável e de fácil manutenção, sendo assim foi separada em camadas mas não aplicado muitos padroes de projeto que poderiam ser aplicados mas neste caso poderia ser um overengineering para uma simples demonstração, padrões estes como Chain of Responsability, Builder, mas foram aplicados conceitos como Factory, Proxy, Repository.
- Versionamento:
  - Foi aplicado commit semântico para versionamento do código.
  - As entregas (commits) foram feitas separadamente por segregação de tarefas.
  
** Além dos pontos elicitados acima, outros pontos podem ser observados no código, fique a vontade para explorar o código e verificar outros pontos que podem ser relevantes para avaliação.
Também foi gasto um tempo para documentar o código, tando no contrato da API quanto nas definicões do projeto, então fique a vontade para explorar a documentação e verificar a qualidade da documentação.

# Considerações finais
Acredito que este projeto tenha alguns dos conhecimentos adquiridos durante o tempo de experiência profissional, com certeza não todos, mas acredito que este projeto possa ser um bom exemplo para avaliação de conhecimentos de backend e arquitetura de software.
Agredeço a oportunidade de avaliação e estou a disposição para esclarecer qualquer dúvida que possa surgir durante a avaliação.





