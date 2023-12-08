FROM alpine

LABEL maintainer="scroogemcfawk" \
      version="1"

RUN apk update && apk --no-cache add bash
RUN apk --no-cache add openjdk17-jre-headless

WORKDIR /service

COPY ../calculator-service/build/libs .

ENTRYPOINT java -jar "calculator-service-1.0-SNAPSHOT.jar"
