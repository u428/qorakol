#FROM openjdk:11
#COPY /app/ .
#WORKDIR /app/
#EXPOSE 8080
##HEALTHCHECK --interval=30s --timeout=10s --start-period=10s \
##CMD curl --fail http://localhost:$SERVER_PORT/open/_healthcheck || exit 1
#ENTRYPOINT exec java  $JAVA_OPTS -jar /app/*.jar



#ROM openjdk:8-jre-alpine
#
#EXPOSE 8080
#
#COPY ./build/libs/java-app-1.0-SNAPSHOT.jar /usr/app/
#WORKDIR /usr/app
#
#ENTRYPOINT ["java", "-jar", "java-app-1.0-SNAPSHOT.jar"]


FROM maven:3.6.3-jdk-11 AS MAVEN_BUILD

# copy the pom and src code to the container
COPY ./ ./

# package our application code
RUN #mvn clean package

# the second stage of our build will use open jdk 11 on alpine 3.9
FROM openjdk:11.0.7-jdk-slim

# copy only the artifacts we need from the first stage and discard the rest
COPY --from=MAVEN_BUILD /target/qorakol.jar /qorakol.jar

# set the startup command to execute the jar
CMD ["java", "-jar", "/qorakol.jar"]