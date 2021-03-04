FROM openjdk:8-jdk-alpine as build
WORKDIR /dist

ARG JAR_FILE 
COPY target/${JAR_FILE} app.jar

FROM openjdk:8-jre-alpine
WORKDIR /app

COPY --from=build /dist/app.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]