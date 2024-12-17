# main.tf

# Referenciando VPC existente
data "aws_vpc" "selected" {
  filter {
    name   = "tag:Name"
    values = ["${var.vpc_name}-${var.environment}"]
  }
}

# Referenciando Subnets da VPC
data "aws_subnets" "selected" {
 filter {
    name   = "vpc-id"
    values = [data.aws_vpc.selected.id]
  }
}

# Referenciando Security Group existente
data "aws_security_group" "selected" {
  filter {
    name   = "tag:Name"
    values = ["${var.security_group_name}-${var.environment}"]
  }

  vpc_id = data.aws_vpc.selected.id
}

# Lambda Function
resource "aws_lambda_function" "authentication_lambda" {
  function_name = "${var.lambda_function_name}-${var.environment}"
  runtime       = var.runtime
  role          = aws_iam_role.lambda_execution_role.arn
  handler       = var.lambda_handler
  memory_size   = var.lambda_memory_size
  timeout       = var.lambda_timeout

  filename         = var.jar_path
  source_code_hash = filebase64sha256(var.jar_path)

  environment {
    #variables = var.environment_variables
     variables = {
      DB_URL                 = "jdbc:postgresql://rds-postgres-dev-nvirginia-ezfastfood.cr6w0cgk4uiy.us-east-1.rds.amazonaws.com:5432/postgres?currentSchema=ez_fastfood"
      DB_USER                = var.db_username
      DB_PASSWORD            = var.db_password
      JWT_SECRET_KEY         = var.jwt_secret_key
      JWT_TOKEN_EXPIRATION   = var.jwt_expiration_token
    }
  }

  vpc_config {
    subnet_ids         = data.aws_subnets.selected.ids
    security_group_ids = [data.aws_security_group.selected.id]
  }

 tags = {
    Name        = "${var.lambda_function_name}-${var.environment}"
    Environment = var.environment
    Project     = var.project
  }
}

# IAM Role for Lambda
resource "aws_iam_role" "lambda_execution_role" {
  name = "${var.lambda_role_name}-${ver.environment}"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Principal = {
          Service = "lambda.amazonaws.com"
        }
      }
    ]
  })
}

# Attach Basic Execution Policy to Lambda
resource "aws_iam_role_policy_attachment" "lambda_logs_policy" {
  role       = aws_iam_role.lambda_execution_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
}

resource "aws_iam_role_policy_attachment" "lambda_vpc_policy" {
  role       = aws_iam_role.lambda_execution_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaVPCAccessExecutionRole"
}

# API Gateway HTTP API
resource "aws_apigatewayv2_api" "http_api" {
  name          = "${var.api_name}-${var.environment}"
  protocol_type = "HTTP"
}

# API Gateway Integration with Lambda
resource "aws_apigatewayv2_integration" "lambda_integration" {
  api_id                  = aws_apigatewayv2_api.http_api.id
  integration_type        = "AWS_PROXY"
  integration_uri         = aws_lambda_function.authentication_lambda.invoke_arn
  payload_format_version  = "2.0"
}

# API Gateway Route
resource "aws_apigatewayv2_route" "auth_route" {
  api_id    = aws_apigatewayv2_api.http_api.id
  route_key = "POST /auth" # Ensures the /auth path is added
  target    = "integrations/${aws_apigatewayv2_integration.lambda_integration.id}"
}

# API Gateway Stage
resource "aws_apigatewayv2_stage" "default_stage" {
  api_id      = aws_apigatewayv2_api.http_api.id
  name        = "$default"
  auto_deploy = true
}

# Lambda Permission for API Gateway
resource "aws_lambda_permission" "allow_apigateway" {
  statement_id  = "AllowAPIGatewayInvoke"
  action        = "lambda:InvokeFunction"
  function_name = aws_lambda_function.authentication_lambda.function_name
  principal     = "apigateway.amazonaws.com"
  source_arn    = "${aws_apigatewayv2_api.http_api.execution_arn}/*/*"
}