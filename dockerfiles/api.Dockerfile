FROM alpine

LABEL maintainer="scroogemcfawk" \
      version="1"

RUN apk update && apk --no-cache add bash
RUN apk --no-cache add openjdk17-jre-headless
RUN apk --no-cache add docker

WORKDIR /service

COPY ../api-service/build/libs .

EXPOSE 8081

ENTRYPOINT java -jar "api-service-1.0-SNAPSHOT.jar"
