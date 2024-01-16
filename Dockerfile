FROM gradle:jdk17-alpine AS build

# 设置语言，支持中文
ENV LANG C.UTF-8

COPY --chown=gradle:gradle . /opt/gradle/src
WORKDIR /opt/gradle/src
RUN gradle clean build -x test --no-daemon

FROM eclipse-temurin:17-jdk-jammy

COPY --from=build /opt/gradle/src/build/libs/*.jar /usr/app/

WORKDIR /usr/app/

RUN sh -c 'touch micro-weather-backend-1.0.0-RELEASE.jar'

ENTRYPOINT ["java", "-jar", "micro-weather-backend-1.0.0-RELEASE.jar"]