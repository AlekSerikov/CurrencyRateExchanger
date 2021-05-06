FROM openjdk:11
CMD ["mkdir", "app"]
WORKDIR app/
COPY target/currency-exchanger-0.0.1-SNAPSHOT.jar app/app.jar
#EXPOSE 8082
CMD ["java", "-jar", "app/app.jar"]