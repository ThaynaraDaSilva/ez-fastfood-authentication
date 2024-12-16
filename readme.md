# EZ-Fast-Food Lambda Authentication Service

Este repositório contém a implementação de uma função Lambda para autenticação de usuários no sistema EZ-Fast-Food. A função valida credenciais de login e gera tokens JWT para acesso seguro a serviços protegidos.

## Tecnologias Utilizadas
- **Java 17**
- **Amazon Lambda**
- **Amazon RDS (PostgreSQL)**
- **Biblioteca JWT (auth0)**
- **Biblioteca Jackson para manipulação JSON**

## Estrutura do Projeto
```
br.com.fiap.ez.fastfood
├── AuthenticationHandler.java  # Função Lambda principal
├── TokenJWTService.java        # Serviço para geração e validação de tokens JWT
├── User.java                   # Entidade representando um usuário
├── UserRepository.java         # Repositório de usuários usando JDBC
```

## Configuração
### Variáveis de Ambiente
- **DB_URL**: URL de conexão com o banco de dados
- **DB_USER**: Usuário do banco de dados
- **DB_PASSWORD**: Senha do banco de dados
- **JWT_SECRET_KEY**: Chave secreta para assinar tokens JWT
- **JWT_TOKEN_EXPIRATION**: Tempo de expiração do token em milissegundos

### Banco de Dados
A função espera uma tabela `customer` no banco de dados com as colunas:
- `cpf` (VARCHAR)
- `password` (VARCHAR)

## Deploy
1. Compile o projeto com Maven:
   ```sh
   mvn clean package
   ```
2. Faça upload do arquivo JAR gerado para a AWS Lambda.
3. Configure as variáveis de ambiente no console da AWS Lambda.

## Endpoints Suportados
- **POST /login**: Autentica o usuário e retorna um token JWT.

### Exemplo de Requisição:
```json
{
  "cpf": "12345678901",
  "password": "senha123"
}
```

### Exemplo de Resposta Bem-Sucedida:
```json
{
  "message": "Authentication successful",
  "token": "eyJhbGciOiJIUzI1NiIsIn..."
}
```

### Exemplo de Erro:
```json
{
  "error": "Invalid credentials"
}
```

## Melhoria Contínua
- Implementar logs estruturados.
- Adicionar suporte a múltiplos ambientes.
- Configurar CI/CD com AWS CodePipeline.

## Links dos demais repositórios:
- APIs: https://github.com/ThaynaraDaSilva/ez-fastfood-api
- EKS: https://github.com/ThaynaraDaSilva/ez-fastfood-eks
- Network: https://github.com/ThaynaraDaSilva/ez-fastfood-network
- RDS: https://github.com/ThaynaraDaSilva/ez-fastfood-database

## Desenvolvido por:
@tchfer : RM357414<br>
@ThaynaraDaSilva : RM357418<br>

