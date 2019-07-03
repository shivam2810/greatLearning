package com.operative.demo.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
public class MongoConfig {
	public static final String DB_NAME = "TicTac";
	public static final String MONGO_HOST = "localhost";
	public static final int MONGO_PORT = 27017;

//	private MongoClient getMongoClient() {
//		if (mClient == null) {
//			mClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//		}
//		return mClient;
//	}
//
//	// Utility method to get database instance
//	private MongoDatabase getDB() {
//		return getMongoClient().getDatabase("Test");
//	}
//
//	// Utility method to get user collection
//	public MongoCollection<Document> getEmployeCollection() {
//		return getDB().getCollection("employee");
//	}

	public MongoOperations getMongoTemplate() {
//		final MongoClientURI mongoUri = new MongoClientURI("mongodb://localhost:27017/Test");
//		return new MongoTemplate(new MongoClient(mongoUri), mongoUri.getDatabase());

		final MongoClient mongo = new MongoClient(MONGO_HOST, MONGO_PORT);
		final MongoOperations mongoOps = new MongoTemplate(mongo, DB_NAME);
		return mongoOps;
	}

}
