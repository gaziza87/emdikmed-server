FROM java:8-jre
MAINTAINER Khamidulina Gaziza

ADD ./target/eureka.jar /app/
CMD ["java", "-Xmx200m", "-Dspring.profiles.active=docker", "-jar", "/app/eureka.jar"]

EXPOSE 8761
