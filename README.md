# atendimentos-api

**atendimentos-api** é uma aplicação Java desenvolvida utilizando o Spring Framework, implementando uma API RESTful para facilitar a gestão de atendimentos da Scenario Automação.

## Objetivo do Projeto

O objetivo da **atendimentos-api** é criar uma ferramenta para o time de suporte técnico da Scenario Automação, que utiliza a plataforma Octadesk para realizar atendimentos. A API se conecta com a API de Tickets da Octadesk, obtém os tickets dos atendimentos realizados pelo time da Scenario, e salva esses dados em um banco de dados configurado de acordo com o modelo de negócio da Scenario Automação.

## Funcionalidades

- **Integração com Octadesk**: A API se conecta à API de Tickets da Octadesk para obter informações sobre os atendimentos realizados.
- **Persistência de Dados**: Os tickets são armazenados em um banco de dados PostgreSQL utilizando o Spring Data JPA.
- **Alimentação Automática**: O banco de dados é atualizado automaticamente todos os dias às 06:00 e 19:00 horas.
- **Consulta de Dados**: A API permite realizar requisições HTTP para obter dados dos tickets, possibilitando a criação de relatórios por uma aplicação front-end.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação utilizada para o desenvolvimento da aplicação.
- **Spring Boot**: Framework utilizado para criar a aplicação de forma simplificada.
- **Spring Data JPA**: Usado para realizar o mapeamento objeto-relacional e gerenciar a persistência de dados.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar os tickets.

Developed by Matheus Singh Cardoso.
