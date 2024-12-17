# ez-fastfood-lambda

Este repositório contém a implementação de uma função Lambda para autenticação de usuários no sistema EZ-Fast-Food. A função valida credenciais de login e gera tokens JWT para acesso seguro a serviços protegidos.

## Funcionalidade
No geral, esta Lambda será responsável por buscar o cliente utilizando o CPF, verificar se a senha fornecida corresponde à senha cadastrada e, em caso de validação bem-sucedida, gerar um token JWT.

## Estrutura do projeto

Este projeto usa uma estrutura simples devido à natureza específica da função, que se concentra exclusivamente na verificação de CPF e senha e geração de JWT Token.

- `AuthenticationHandler.java`: Ponto de entrada da função Lambda.
- `TokenJWTService.java`: Geração e validação de tokens JWT.
- `UserRepository.java`: Consulta ao banco de dados.
- `User.java`: Representação da entidade de usuário.

## Exemplo de solicitação 
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
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI1MzAuMzM1LjYxMC04MCIsImNwZiI6IjUzMC4zMzUuNjEwLTgwIiwiaWF0IjoxNzM0NDY4MTIzLCJleHAiOjE3MzQ0NzE3MjN9.kc_9ZSjSJpk_cbyHl8SUXIqPgg8AYfVvhEH9ZbdOSp4"
}
```

**Erro:**
```json
{
  "error": "Invalid credentials"
}
```
## Evidência da Lambda executando conforme esperado: 
![image](https://github.com/user-attachments/assets/1664e7e1-408f-4b89-a87f-e1b8250c70d6)

## Tecnologias Utilizadas

- AWS Lambda
- Java 17
- Banco de Dados PostgreSQL
- Biblioteca JWT (Auth0)

## Instruções de Implantação

1. Configure as variáveis **AWS_ACCESS_KEY_ID**, **AWS_DB_PASSWORD**, **AWS_DB_USER**, **AWS_SECRET_ACCESS_KEY**, **JWT_SECRET_KEY** no GitHub secrets.
2. Implante a função usando sua ferramenta de implantação preferida.
3. Teste a função usando um serviço de API Gateway ou diretamente no console da AWS.

OBS..: para esta primeira versão, a URL do banco de dados está definida direto no Terraform.

## Links dos demais repositórios:
- APIs: https://github.com/ThaynaraDaSilva/ez-fastfood-api
- EKS: https://github.com/ThaynaraDaSilva/ez-fastfood-eks
- Network: https://github.com/ThaynaraDaSilva/ez-fastfood-network
- RDS: https://github.com/ThaynaraDaSilva/ez-fastfood-database

## Desenvolvido por:
@tchfer : RM357414<br>
@ThaynaraDaSilva : RM357418<br>