# EZ-Fast-Food Lambda Authentication Service

Este repositório contém a implementação de uma função Lambda para autenticação de usuários no sistema EZ-Fast-Food. A função valida credenciais de login e gera tokens JWT para acesso seguro a serviços protegidos.

## Funcionalidades

- Autenticação de usuários usando CPF e senha.
- Geração de token JWT para usuários autenticados.
- Consulta direta ao banco de dados para validação de credenciais.

## Estrutura do Projeto

Este projeto usa uma estrutura simples devido à natureza específica da função, que se concentra exclusivamente na validação de CPF e senha para autenticar o usuário.

- `AuthenticationHandler.java`: Ponto de entrada da função Lambda.
- `TokenJWTService.java`: Geração e validação de tokens JWT.
- `UserRepository.java`: Consulta ao banco de dados.
- `User.java`: Representação da entidade de usuário.

## Fluxo de Autenticação

1. A função Lambda recebe uma solicitação HTTP contendo um corpo JSON com `cpf` e `password`.
2. Consulta o banco de dados para verificar se há um usuário correspondente.
3. Se as credenciais forem válidas, um token JWT é gerado e retornado.
4. Se as credenciais forem inválidas, uma mensagem de erro é retornada.

## Exemplo de Solicitação

```json
{
  "cpf": "12345678900",
  "password": "senha123"
}
```

## Exemplo de Resposta

**Sucesso:**
```json
{
  "message": "Authentication successful",
  "token": "jwt-token-gerado"
}
```

**Erro:**
```json
{
  "error": "Invalid credentials"
}
```

## Configuração de Ambiente da Lambda

- `DB_URL`: URL de conexão com o banco de dados.
- `DB_USER`: Nome de usuário do banco de dados.
- `DB_PASSWORD`: Senha do banco de dados.
- `JWT_SECRET_KEY`: Chave secreta para geração de tokens JWT.
- `JWT_TOKEN_EXPIRATION`: Tempo de expiração dos tokens JWT (em milissegundos).

## Tecnologias Utilizadas

- AWS Lambda
- Java 17
- Banco de Dados PostgreSQL
- Biblioteca JWT (Auth0)

## Instruções de Implantação

1. Configure as variáveis de ambiente no AWS Lambda.
2. Implante a função usando sua ferramenta de implantação preferida.
3. Teste a função usando um serviço de API Gateway ou diretamente no console da AWS.

## Links dos demais repositórios:
- APIs: https://github.com/ThaynaraDaSilva/ez-fastfood-api
- EKS: https://github.com/ThaynaraDaSilva/ez-fastfood-eks
- Network: https://github.com/ThaynaraDaSilva/ez-fastfood-network
- RDS: https://github.com/ThaynaraDaSilva/ez-fastfood-database

## Desenvolvido por:
@tchfer : RM357414<br>
@ThaynaraDaSilva : RM357418<br>

