FROM eclipse-temurin:21-jdk-jammy

RUN addgroup --system appuser && adduser --system --ingroup appuser appuser

RUN mkdir -p /home/appuser/app

RUN chown -R appuser:appuser /home/appuser

USER appuser:appuser

WORKDIR /home/appuser/app

COPY target/task-manager-0.0.1-SNAPSHOT.jar app.jar
#COPY target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/home/appuser/app/app.jar"]


