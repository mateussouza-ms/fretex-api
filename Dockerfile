FROM openjdk
WORKDIR /app
COPY target/fretex-api-0.0.1-SNAPSHOT.jar /app/fretex-api.jar
ENTRYPOINT ["java", "-jar", "fretex-api.jar"]