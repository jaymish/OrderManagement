FROM openjdk:16-jdk-alpine

COPY . /usr/src/app/
WORKDIR /usr/src/app/
RUN ./gradlew clean build -x test
RUN mv build/libs/consumerOrder-0.0.1-SNAPSHOT.jar consumerOrder.jar

ENTRYPOINT java -jar consumerOrder.jar
