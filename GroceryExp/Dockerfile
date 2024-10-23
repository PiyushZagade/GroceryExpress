FROM maven:3.9.9-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar GroceryExp.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","GroceryExp.jar"]