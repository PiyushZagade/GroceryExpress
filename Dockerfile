FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /app/target/GroceryExpress-0.0.1-SNAPSHOT.jar GroceryExpress.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "GroceryExpress.jar"]