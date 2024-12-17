variable "aws_region" {
  description = "Região onde os recursos da AWS serão criados"
  default     = "us-east-1"
}
variable "access_key" {
  description = "AWS Access Key ID"
  type        = string
  sensitive   = true
}

variable "secret_key" {
  description = "AWS Secret Access Key"
  type        = string
  sensitive   = true
}


variable "db_username" {
  description = "The username for the database"
  type        = string
  sensitive   = true
}

variable "db_password" {
  description = "The password for the database"
  type        = string
  sensitive   = true
}

variable "jwt_secret_key" {
  description = "jwt secret"
  type        = string
  sensitive   = true
}

variable "jwt_expiration_token" {
  description = "jwt token"
  type        = string
  default     = "3600000"
}


variable "vpc_name" {
  description = "Nome da VPC existente"
  type        = string
}

variable "security_group_name" {
  description = "Nome do Security Group existente"
  type        = string
}

variable "lambda_function_name" {
  description = "Nome da função Lambda"
  type        = string
}

variable "lambda_memory_size" {
  description = "Quantidade de memória alocada para a função Lambda"
  type        = number
}

variable "lambda_timeout" {
  description = "Tempo máximo de execução da função Lambda (em segundos)"
  type        = number
}

variable "lambda_handler" {
  description = "Handler da função Lambda"
  type        = string
}

variable "runtime" {
  description = "Runtime utilizado na função Lambda"
  type        = string
}

variable "jar_path" {
  description = "Caminho do arquivo JAR a ser enviado para a Lambda"
  type        = string
}

# variable "environment_variables" {
#   description = "Variáveis de ambiente para a função Lambda"
#   type        = map(string)
# }

# variable "lambda_tags" {
#   description = "Tags associadas à função Lambda"
#   type        = map(string)
# }

variable "environment" {
  default = "dev"
}

variable "project" {
  description = "Project name for tagging"
  type        = string
  default     = "ez-fastfood"
}

variable "api_name" {
  description = "Nome da API no API Gateway"
  type        = string
}
