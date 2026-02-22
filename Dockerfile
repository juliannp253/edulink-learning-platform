# Etapa 1: Construcción (Build)
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app
# Copiamos el pom y el código fuente
COPY pom.xml .
COPY src ./src
# Compilamos saltando los tests para agilizar el deploy
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Run)
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copiamos solo el JAR generado desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar
# Exponemos el puerto que usa Render por defecto
EXPOSE 8080
# Comando para arrancar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]