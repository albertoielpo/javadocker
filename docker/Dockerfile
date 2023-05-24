# STEP 1 - Find a desired docker image. In this case we need a linux-java docker image
# https://hub.docker.com/_/openjdk
FROM openjdk:17-jdk-slim
# STEP 2 - Configuration part
RUN mkdir -p /opt/javadocker
COPY javadocker.jar /opt/javadocker/javadocker.jar
WORKDIR /opt/javadocker
EXPOSE 8080
# Setting a common environment variable
# ENV COMMON_ENV_VAR=PIPPO
# STEP 3 - Start command
ENTRYPOINT java -jar javadocker.jar