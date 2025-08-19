mkdir -p /docker/maven/repository
docker run --rm -v "$(pwd)":/app -w /app -/docker/maven/repository:~/.m2 \
maven:3.9.11-amazoncorretto-21 \
mvn clean package -DskipTests
