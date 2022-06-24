FROM openjdk:11 AS build

COPY . .
RUN chmod +x gradlew
RUN ./gradlew build

# actual container
FROM openjdk:11
WORKDIR secondhand
COPY --from=build build/libs/*.jar secondhand.jar
ENTRYPOINT ["java","-jar","secondhand.jar"]


