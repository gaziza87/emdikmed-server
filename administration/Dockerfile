FROM java:8-jre
MAINTAINER Gaziza Khamidulina

ADD ./target/administration.jar /app/
CMD ["java", "-Xmx200m", "-Dspring.profiles.active=docker", "-jar", "/app/administration.jar"]

EXPOSE 8776
