FROM openjdk:17-jdk-slim

WORKDIR /app

COPY CompoDoker/prueba_1_0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]