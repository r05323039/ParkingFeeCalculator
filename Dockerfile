FROM maven:3.9.4 as build-stage

WORKDIR /app

COPY pom.xml pom.xml
COPY parking-entity parking-entity
COPY parking-repository parking-repository
COPY parking-web parking-web

RUN mvn clean package

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build-stage /app/parking-web/target/parking-web-1.0.0.jar parking-web-1.0.0.jar

COPY rootfs /

RUN ls -R /app

EXPOSE 8080

ENTRYPOINT ["init"]