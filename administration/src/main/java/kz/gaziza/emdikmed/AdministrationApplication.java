package kz.gaziza.emdikmed;


import kz.gaziza.emdikmed.repository.ResourceUtilRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableMongoRepositories(repositoryBaseClass = ResourceUtilRepositoryImpl.class)
@EnableHystrixDashboard
@EnableMongoAuditing
public class AdministrationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdministrationApplication.class, args);
    }
}
