aws_region = "us-east-1"
vpc_name = "vpc-dev-nvirginia-ezfastfood-vpc"
security_group_name = "rds-dev-nvirginia-ezfastfood-sg"
lambda_function_name = "new-authentication-lambda"
lambda_memory_size = 128
lambda_timeout = 30
lambda_handler = var.handler
runtime = "java11"
jar_path = target/ez-fastfood-authentication.jar
environment = "dev"
allocated_storage = 20
storage_type = "gp2"
engine = "postgres"
engine_version = "13.17"
instance_class = "db.t4g.micro"
parameter_group_name = "default.postgres13"
publicly_accessible = false
skip_final_snapshot = true
environment_variables = {
  VAR1 = "dev"
}
lambda_tags = {
  Name = "LambdaFunction"
}
api_name = "ez-fastfood-authentication"
