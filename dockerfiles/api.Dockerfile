FROM alpine

LABEL maintainer="scroogemcfawk" \
      version="1"

RUN apk update && apk --no-cache add bash
RUN apk --no-cache add openjdk17-jre-headless

WORKDIR /service

EXPOSE 8081

ENTRYPOINT java -jar -Dserver.port=8081 -Dspring.kafka.bootstrap-servers="kafka:9092" "api-service.jar"
