FROM amazoncorretto:21
LABEL authors="lcl94"

ENTRYPOINT ["java", "-jar", "/app/app.jar"]