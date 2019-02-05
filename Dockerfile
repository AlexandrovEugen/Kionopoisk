FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/Kinopoisk-0.0.1.jar target/app.jar
RUN apk add --no-cache bash
RUN bash -c 'touch target/app.jar'
EXPOSE 8490
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","target/app.jar"]