FROM amazoncorretto:21
LABEL authors="lcl94"
WORKDIR /root
COPY target/*.jar /root/app.jar
EXPOSE 8080
ENV JAVA_OPTS="-Xmx512m -Xms512m -Xmn256m -XX:+UseZGC -XX:MaxGCPauseMillis=200 -XX:ParallelGCThreads=1 -XX:ConcGCThreads=1"
CMD ["sh", "-c", "java $JAVA_OPTS -jar /root/app.jar"]