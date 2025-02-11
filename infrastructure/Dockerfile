FROM amazoncorretto:21 AS builder

WORKDIR /app
COPY . .
RUN ./gradlew build -x test

FROM amazoncorretto:21 AS runtime
WORKDIR /app
COPY --from=builder /app/build/libs/ms-rest-base-1.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
