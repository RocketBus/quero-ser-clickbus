FROM openjdk:8-jre-alpine

VOLUME /tmp
ENV TZ=America/Sao_Paulo
ADD ./target/place-0.0.1-SNAPSHOT.jar /place.jar
ENTRYPOINT ["java" , "-server", "-Xmx256m", "-jar", "/place.jar"]
