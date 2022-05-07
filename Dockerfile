
#
# Build stage
#
FROM gradle:jdk17 AS build
COPY src /opt/app/src
COPY build.gradle /opt/app
WORKDIR /opt/app
RUN gradle build


#
# Package stage
#
FROM openjdk:17
COPY --from=build /opt/app/build/libs/app-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]


