FROM amazoncorretto:23-alpine-jdk AS base
WORKDIR /app
EXPOSE 8080

FROM maven:3.9.9-amazoncorretto-23-alpine AS build
WORKDIR /app

COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn dependency:resolve dependency:resolve-plugins -B

COPY src ./src
COPY scripts ./scripts

ARG DEV=false
RUN if [ "$DEV" = "true" ]; then \
    echo "Development mode enabled"; \
#    export MAVEN_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"; \
    fi

RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests -B

FROM build AS publish
RUN mv target/*.jar /app/app.jar

FROM base AS final
WORKDIR /app

RUN addgroup -S springgroup && adduser -S springuser -G springgroup
USER springuser

COPY --from=publish /app/app.jar .

ADD scripts/entrypoint.sh /

ENTRYPOINT ["/bin/sh", "/entrypoint.sh"]