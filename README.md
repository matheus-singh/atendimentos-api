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

## Como Executar o Projeto

1. Clone o repositório:

    ```bash
    git clone https://github.com/seu-usuario/atendimentos-api.git
    ```

2. Configure o banco de dados PostgreSQL e atualize as credenciais no arquivo `application.properties`.
   
3. Configure as variáveis de ambiente necessárias da Classe ConsumoOctadeskAPI.
   
4. Navegue até o diretório do projeto:

    ```bash
    cd atendimentos-api
    ```
5. Execute a aplicação:

    ```bash
    ./mvnw spring-boot:run
    ```

## Contribuições

Contribuições são bem-vindas! Se você encontrar problemas ou tiver sugestões, sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

Developed by Matheus Singh Cardoso.
