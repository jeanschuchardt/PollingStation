FROM ringcentral/maven:3.6.3-jdk17.0.6-alpine
VOLUME /tmp
COPY ./target/ItemService-0.0.1-SNAPSHOT.jar app.jar
ENV PARAMS=""
ENTRYPOINT exec java -jar app.jar $PARAMS