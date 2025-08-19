#!/bin/bash -l

mkdir -p /home/docker/maven/repository
docker run --rm \
-v "$(pwd)":/app \
-w /app \
-v /home/docker/maven/repository:/root/.m2 \
maven:3.9.11-amazoncorretto-21 \
mvn clean package -DskipTests
