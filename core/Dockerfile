FROM java:8-jre
MAINTAINER Gaziza Khamidulina

ADD ./target/core.jar /app/
CMD ["java", "-Xmx200m", "-Dspring.profiles.active=docker", "-jar", "/app/core.jar"]

EXPOSE 8767
