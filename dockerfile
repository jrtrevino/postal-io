FROM maven:3.8.3-openjdk-17 as maven
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml package

FROM openjdk:17

COPY --from=maven /usr/src/app/target/challenges-1.jar usr/app/challenges-1.jar
ENTRYPOINT ["java","-jar","/usr/app/challenges-1.jar"]  