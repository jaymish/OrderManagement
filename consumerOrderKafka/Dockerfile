FROM openjdk:16-jdk-alpine

COPY . /usr/src/app/
WORKDIR /usr/src/app/
RUN ./gradlew clean build -x test
RUN mv build/libs/OrderKafka-0.0.1-SNAPSHOT.jar consumerOrderKafka.jar

ENTRYPOINT java -jar consumerOrderKafka.jar
