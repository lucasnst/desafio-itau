FROM openjdk:17-jdk-slim
COPY target/desafio-itau-1.0.0-SNAPSHOT.jar desafio-itau-1.0.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/desafio-itau-1.0.0-SNAPSHOT.jar"]