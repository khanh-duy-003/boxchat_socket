package com.duypk.socket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(
    basePackages = "com.duypk.socket.repositoryMongo",
    mongoTemplateRef = "mongoTemplate"
)
public class MongoConfig {
	
	@Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }

    @Bean
    MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "BOXCHAT_SOCKET");
    }

}
