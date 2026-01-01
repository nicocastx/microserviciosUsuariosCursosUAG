FROM maven:3.9.8-amazoncorretto-21 AS build

ARG MSVC_NAME=msvcusuarios

WORKDIR /app/$MSVC_NAME

COPY ./pom.xml /app
COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .

#RUN mvn clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target
RUN mvn dependency:go-offline

COPY ./$MSVC_NAME/src ./src

RUN mvn clean package -DskipTests

FROM amazoncorretto:21.0.8

ARG TARGET_FOLDER=app/msvcusuarios/target
WORKDIR /app

COPY --from=build $TARGET_FOLDER/msvcusuarios-0.0.1-SNAPSHOT.jar .

ENV PORT=8081

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "msvcusuarios-0.0.1-SNAPSHOT.jar"]