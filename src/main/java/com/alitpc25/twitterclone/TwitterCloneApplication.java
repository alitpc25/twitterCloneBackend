package com.alitpc25.twitterclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@EnableMongoRepositories
@EnableNeo4jRepositories
@SpringBootApplication
public class TwitterCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterCloneApplication.class, args);
	}

}
