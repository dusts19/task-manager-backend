FROM maven:3.9.8-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean install -DskipTests



FROM eclipse-temurin:21-jdk-jammy

RUN addgroup --system appuser && adduser --system --ingroup appuser appuser

RUN mkdir -p /home/appuser/app

RUN chown -R appuser:appuser /home/appuser

USER appuser:appuser

WORKDIR /home/appuser/app

COPY --from=builder /app/target/task-manager-0.0.1-SNAPSHOT.jar app.jar
#COPY target/*.jar app.jar

ENV PORT=8080

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/home/appuser/app/app.jar"]


