FROM openjdk:11
COPY target/ /app/
WORKDIR /app/
EXPOSE $SERVER_PORT
#HEALTHCHECK --interval=30s --timeout=10s --start-period=10s \
#CMD curl --fail http://localhost:$SERVER_PORT/open/_healthcheck || exit 1
ENTRYPOINT exec java  $JAVA_OPTS -jar /app/*.jar
