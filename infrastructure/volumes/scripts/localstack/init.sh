#!/bin/bash

echo "Starting to initialize SSM Parameter in localstack"

aws --endpoint-url=http://localstack:4566 ssm put-parameter --name "/my/parameter" --value "my-value" --type "String" --overwrite
aws --endpoint-url=http://localstack:4566 s3 mb s3://my-bucket &&

echo "Initialized SSM Parameter in localstack successfully"