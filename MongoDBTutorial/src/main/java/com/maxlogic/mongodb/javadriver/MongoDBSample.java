package com.maxlogic.mongodb.javadriver;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class MongoDBSample {
	public static void main(String[] args) {
		MongoDBSample dbSample = new MongoDBSample();
		
		System.out.println("Insert single document");
		long startTime = System.currentTimeMillis();
		dbSample.insertRecordsSimple();
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken to insert 1k simple documents");
		
		
	}

	private void insertRecordsSimple() {
		DB db = MongoFactory.getDatabase("oto_embbeded_doc");
		DBCollection policyCol = db.getCollection("policy");		
		for(int i=0;i<1000;i++) {
			BasicDBObject polDoc = new BasicDBObject();
			polDoc.put("policyNo", i+1);
			polDoc.put("status", getStatus(i));
			polDoc.put("", v);
			policyCol.insert(polDoc);
		}
	}
	
	public static String getStatus(int i) {
		int mod = i%3;
		switch(mod) {
			case 0 :
				return "in force";
			case 1 :
				return "pending";
			case 2 : 
				return "suspended";
			default : 
				return "Active";
		}
	}
}
