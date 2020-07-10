package kz.gaziza.emdikmed.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Value("${spring.data.mongodb.defaultbucket}")
    private String defaultbucket;

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://" + host + ":" + port + "/"+database);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    public String getDefaultBucket() {
        return defaultbucket;
    }

    @Bean
    public GridFsTemplate gridFsTemplate() {
        try {
            return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter(), defaultbucket);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

