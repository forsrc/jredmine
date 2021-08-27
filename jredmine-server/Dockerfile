FROM maven:3.6.3-jdk-11 as builder

COPY . /app

WORKDIR /app
RUN mvn -B package -DskipTests

FROM openjdk:11-jdk-alpine

RUN mkdir /app/

WORKDIR /app

ARG JAR_FILE=target/*.jar
ARG APP_PORT=8080

#COPY ${JAR_FILE} /app/app.jar

COPY --from=builder /app/target/*.jar /app/app.jar


EXPOSE $APP_PORT

ENTRYPOINT ["/bin/sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]