IMAGE_NAME=modelex-ai
CONTAINER_NAME=modelex-ai

PORT=8080

clean:
	rm -rf build/
	rm -rf modelex-ai-be.tar

build:
	./gradlew build -x test

image:
	docker build -t modelex-ai-be .

run:
	docker run -dit -p 8080:8080 --name modelex-ai-be modelex-ai-be:latest

run-dev:
	docker run -dit -p 8080:8080 -e SPRING_PROFILES_ACTIVE=dev --name modelex-ai-be modelex-ai-be:latest

save:
	docker save -o modelex-ai-be.tar modelex-ai-be:latest

copy:
	scp modelex-ai-be.tar root@165.227.99.185:/root/data

load:
	docker load -i /root/data/modelex-ai-be.tar

stop:
	docker stop $(CONTAINER_NAME) || true
	docker rm $(CONTAINER_NAME) || true
	docker rmi $(IMAGE_NAME) || true


remove:
	docker rm -f modelex-ai-be


all:
	clean image save copy