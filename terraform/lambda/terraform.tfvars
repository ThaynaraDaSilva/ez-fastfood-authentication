aws_region           = "us-east-1"
vpc_name             = "vpc-dev-nvirginia-ezfastfood-vpc"
security_group_name  = "rds-dev-nvirginia-ezfastfood-sg"
api_name             = "api-gtw-dev-ezfastfood-authentication-lambda"
lambda_function_name = "ez-fastfood-auth-lambda-dev-new"
lambda_memory_size   = 512
lambda_timeout       = 15
lambda_handler       = "br.com.fiap.ez.fastfood.AuthenticationHandler::handleRequest"
runtime              = "java17"
jar_path             = "../../target/ez-fastfood-authentication.jar"


environment_variables = {
  DB_URL               = "jdbc:postgresql://rds-postgres-dev-nvirginia-ezfastfood.cr6w0cgk4uiy.us-east-1.rds.amazonaws.com:5432/postgres?currentSchema=ez_fastfood"
  DB_USER              = "postgres"
  DB_PASSWORD          = "postgres"
  JWT_SECRET_KEY       = "chave-secreta"
  JWT_TOKEN_EXPIRATION = "3600000"
}

lambda_tags = {
  Name         = "ez-fastfood-authentication"
  Environment  = "dev"
  Project      = "ez-fast-food"
}

