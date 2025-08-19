FROM amazoncorretto:21
LABEL authors="lcl94"
RUN mkdir /app
WORKDIR /app
COPY target/*.jar /app/app.jar
EXPOSE 8080
ENV JAVA_OPTS="-Xmx256m -Xms256mm -Xmn192m -XX:+UseZGC -XX:MaxGCPauseMillis=200 -XX:ParallelGCThreads=1 -XX:ConcGCThreads=1"
CMD ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]