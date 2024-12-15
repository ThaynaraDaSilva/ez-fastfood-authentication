variable "aws_region" {
  description = "Região onde os recursos da AWS serão criados"
  default     = "us-east-1"
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
  default     = "com.example.Handler::handleRequest"
}

variable "runtime" {
  description = "Runtime utilizado na função Lambda"
  type        = string
}

variable "jar_path" {
  description = "Caminho do arquivo JAR a ser enviado para a Lambda"
  type        = string
  default     = "target/ez-fastfood-authentication.jar"
}

variable "environment_variables" {
  description = "Variáveis de ambiente para a função Lambda"
  type        = map(string)
}

variable "lambda_tags" {
  description = "Tags associadas à função Lambda"
  type        = map(string)
}

variable "api_name" {
  description = "Nome da API no API Gateway"
  type        = string
}
