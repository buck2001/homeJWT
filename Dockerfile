FROM openjdk:8-jre
VOLUME /tmp
ARG JAR_FILE=target/pt-user-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar","env"]
