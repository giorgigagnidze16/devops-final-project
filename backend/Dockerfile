FROM alpine:3.21.3
RUN apk add --no-cache openjdk21
COPY build/libs/backend-0.0.1.jar /app/
WORKDIR /app/
ENTRYPOINT ["java"]
CMD ["-jar", "-Duser.timezone=UTC", "/app/backend-0.0.1.jar"]
