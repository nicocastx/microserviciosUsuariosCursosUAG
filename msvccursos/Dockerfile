FROM maven:3.9.8-amazoncorretto-21 AS build

WORKDIR /app/msvccursos

COPY ./pom.xml /app
COPY ./msvccursos/.mvn ./.mvn
COPY ./msvccursos/mvnw .
COPY ./msvccursos/pom.xml .

#Utilizo esta opcion, que es basicamente lo mismo que hace el comando comentado de abajo
RUN mvn clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target
#RUN mvn dependency:go-offline

COPY ./msvccursos/src ./src

RUN mvn clean package -DskipTests

FROM amazoncorretto:21.0.8

WORKDIR /app

COPY --from=build app/msvccursos/target/msvccursos-0.0.1-SNAPSHOT.jar .

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "msvccursos-0.0.1-SNAPSHOT.jar"]