# Etapa 1: Construcción (Build)
# Usamos una imagen de Maven que aún sea válida
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Run)
# Cambiamos openjdk por eclipse-temurin, que sí está disponible y es más estable
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]