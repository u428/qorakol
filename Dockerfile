FROM openjdk:11
COPY target/ /app/
WORKDIR /app/
EXPOSE 8080
#HEALTHCHECK --interval=30s --timeout=10s --start-period=10s \
#CMD curl --fail http://localhost:$SERVER_PORT/open/_healthcheck || exit 1
ENTRYPOINT exec java  $JAVA_OPTS -jar /app/*.jar



#ROM openjdk:8-jre-alpine
#
#EXPOSE 8080
#
#COPY ./build/libs/java-app-1.0-SNAPSHOT.jar /usr/app/
#WORKDIR /usr/app
#
#ENTRYPOINT ["java", "-jar", "java-app-1.0-SNAPSHOT.jar"]