package com.maxlogic.mongodb.javadriver;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoFactory {
	private static MongoClient client = null;
	
	public static MongoClient getClient() {
		if(client == null) {
			try {
				client = new MongoClient("localhost", 27017);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	
	public static DB getDatabase(String dbName) {
		MongoClient client = getClient();
		return client.getDB(dbName);
	}
}
