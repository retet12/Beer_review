FROM openjdk:11
LABEL maintainer="trotmihail1996@gmail.com"
COPY /target/*.jar /app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]