package com.fullstackapp.joblisting.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class MongoConfig {
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb+srv://root:root@cluster0.wggenn1.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");
    }

    @Bean
    public MongoConverter mongoConverter(MongoDatabaseFactory mongoDbFactory, MongoMappingContext context) {
        return new MappingMongoConverter(mongoDbFactory, context);
    }
}
