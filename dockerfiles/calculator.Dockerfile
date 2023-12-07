FROM alpine

LABEL maintainer="scroogemcfawk" \
      version="1"

RUN apk update && apk --no-cache add bash
RUN apk --no-cache add openjdk17-jre-headless

WORKDIR /service

ENTRYPOINT java -jar -Dspring.kafka.bootstrap-servers="kafka:9092" "calculator-service.jar"
