# Usamos una imagen de Maven para compilar el código
FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Usamos una imagen ligera de Java para ejecutarlo
FROM openjdk:17-jdk-slim
COPY --from=build /target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]