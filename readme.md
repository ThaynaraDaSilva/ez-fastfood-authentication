
# EZ FASTFOOD AUTHENTICATION
Detalhar sobre este projeto aqui .........


# Terraform commands

```
# realiza preparação dos plugins necessários e configurará o ambiente terraform
terraform init

# valida os arquivos a fim de garantir que não há erros de sintaxe ou configs inválidas
terraform validate

# exibe resumo detalhado de todas as alterações que o terraform aplicará
terraform plan

# aplica as alterações para provisionar os recursos na AWS
terraform apply 
// terraform apply -auto-approve

# remover todos os recursos
terraform destroy

# destruir um recurso especifico
terraform state list
terraform destroy -target=<nome-recurso>
Eg: terraform destroy -target=aws_lambda_function.authentication_lambda

```


