FROM gradle:5.5.1-jdk8 as compile
COPY . /home/source/java
WORKDIR /home/source/java
USER root
RUN chown -R gradle /home/source/java
USER gradle
RUN gradle clean assemble

FROM adoptopenjdk:8u212-b04-jre-hotspot-bionic
WORKDIR /home/application/java
COPY --from=compile "/home/source/java/build/libs/spring-cloud-security-gateway-gs-0.0.1-SNAPSHOT.jar" .
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/home/application/java/spring-cloud-security-gateway-gs-0.0.1-SNAPSHOT.jar"]