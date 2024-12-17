aws_region           = "us-east-1"
vpc_name             = "ez-fastfood-vpc"
security_group_name  = "ez-fastfood-rds-sg"
api_name             = "ez-fastfood-authentication-api"
lambda_function_name = "ez-fastfood-authentication-lambda"
lambda_role_name      = "ez-fastfood-lambda-role"
lambda_memory_size   = 512
lambda_timeout       = 15
lambda_handler       = "br.com.fiap.ez.fastfood.AuthenticationHandler::handleRequest"
runtime              = "java17"
jar_path             = "../target/ez-fastfood-authentication.jar"
environment  = "dev"
project      = "ez-fastfood"

