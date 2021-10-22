FROM openjdk:11.0-jre
COPY target/todo-cosmosdb-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]