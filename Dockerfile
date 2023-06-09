FROM gradle:7.2.0-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17-alpine

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/votacaoexecicio-0.0.1-SNAPSHOT.jar /app/spring-boot-application.jar

ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]