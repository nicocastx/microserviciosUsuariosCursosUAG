FROM maven:3.9.8-amazoncorretto-21 as build

WORKDIR /app/msvcusuarios

COPY ./pom.xml /app
COPY ./msvcusuarios/.mvn ./.mvn
COPY ./msvcusuarios/mvnw .
COPY ./msvcusuarios/pom.xml .

#RUN mvn clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target
RUN mvn dependency:go-offline

COPY ./msvcusuarios/src ./src

RUN mvn clean package -DskipTests

FROM amazoncorretto:21.0.8

WORKDIR /app

COPY --from=build app/msvcusuarios/target/msvcusuarios-0.0.1-SNAPSHOT.jar .

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "msvcusuarios-0.0.1-SNAPSHOT.jar"]