package com.alitpc25.twitterclone;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@EnableMongoRepositories
@EnableNeo4jRepositories
@EnableMongoAuditing
@SpringBootApplication
public class TwitterCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterCloneApplication.class, args);
		try {
					FirebaseOptions options = FirebaseOptions.builder()
					  .setCredentials(GoogleCredentials.fromStream(new ClassPathResource("firebase_config.json").getInputStream()))
					  .setStorageBucket("twitter-clone-app-d3449.appspot.com")
					  .build();

					FirebaseApp.initializeApp(options);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
