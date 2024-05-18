#FROM eclipse-temurin:22-jre-alpine
#WORKDIR /app
#COPY target/GatewayApplication-0.0.1-SNAPSHOT.jar app.jar
#
#ENTRYPOINT ["java", "-jar","app.jar"]

FROM maven:3.9.6-eclipse-temurin-22-alpine AS build
WORKDIR /app
COPY src /app/src
COPY pom.xml /app
RUN mvn --file /app/pom.xml clean package -DskipTests
RUN mkdir -p /app/target/dependency && (cd /app/target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:22-jre-alpine
ARG DEPENDENCY=/app/target/dependency
EXPOSE 8081
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","/app:/app/lib/*","org.example.gatewayapplication.GatewayApplication"]
