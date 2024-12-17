aws_region           = "us-east-1"
vpc_name             = "ez-fastfood-vpc"
security_group_name  = "ez-fastfood-rds-sg"
api_name             = "ez-fastfood-authentication-api"
lambda_function_name = "ez-fastfood-authentication-lambda"
lambda_memory_size   = 512
lambda_timeout       = 15
lambda_handler       = "br.com.fiap.ez.fastfood.AuthenticationHandler::handleRequest"
runtime              = "java17"
jar_path             = "../../target/ez-fastfood-authentication.jar"


# environment_variables = {
#   DB_URL               = "jdbc:postgresql://rds-postgres-dev-nvirginia-ezfastfood.cr6w0cgk4uiy.us-east-1.rds.amazonaws.com:5432/postgres?currentSchema=ez_fastfood"
#   DB_USER              = "postgres"
#   DB_PASSWORD          = "postgres"
#   JWT_SECRET_KEY       = "chave-secreta"
#   JWT_TOKEN_EXPIRATION = "3600000"
# }

lambda_tags = {
  Name         = "ez-fastfood-authentication"
  Environment  = "dev"
  Project      = "ez-fastfood"
}

