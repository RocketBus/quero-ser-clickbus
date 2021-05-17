FROM openjdk:14-jdk-alpine
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/group-by-interval-1.0.0.jar
ENTRYPOINT ["java","-jar","/app/group-by-interval-1.0.0.jar"]