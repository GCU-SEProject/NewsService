FROM openjdk:17-jdk
VOLUME /tmp
WORKDIR /app
COPY target/newsservice-0.0.1-SNAPSHOT.jar newsservice.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "newsservice.jar"]