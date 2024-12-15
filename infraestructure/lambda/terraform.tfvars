aws_region = "us-east-1"
vpc_name = "vpc-dev-nvirginia-ezfastfood-vpc"
rds_instance_name = "rds-dev-ez-fastfood"
rds_security_group_name = "rds-dev-nvirginia-ezfastfood-sg"
lambda_function_name = "new-authentication-lambda"
environment = "dev"
project = "ez-fastfood"
allocated_storage = 20
storage_type = "gp2"
engine = "postgres"
engine_version = "13.17"
instance_class = "db.t4g.micro"
parameter_group_name = "default.postgres13"
publicly_accessible = false
skip_final_snapshot = true

lambda_handler = "your.handler"
runtime = "java11"
jar_path = "path/to/your/lambda.jar"
environment_variables = {
  VAR1 = "value1"
  VAR2 = "value2"
}
lambda_tags = {
  Name = "LambdaFunction"
}
api_name = "ez-fastfood"                     
