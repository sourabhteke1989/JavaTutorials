package com.maxlogic.tutorials.map.concurrent_read_write;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MainClass {
	public static void main(String[] args) {
		MainClass classTest = new MainClass();
		System.out.println("Concurrent Read only data");
		for(int i=0;i<10;i++) {
			classTest.testReadOnly("HashMap", new HashMap<>());
			classTest.testReadOnly("HashTable", new Hashtable<>());
			classTest.testReadOnly("ConcurrentHashMap", new ConcurrentHashMap<>());
			System.out.println("");
		}
		
		System.out.println("Concurrent Read/Write data");
		for(int i=0;i<10;i++) {
			classTest.testReadWrite("HashMap", new HashMap<>());
			classTest.testReadWrite("HashTable", new Hashtable<>());
			classTest.testReadWrite("ConcurrentHashMap", new ConcurrentHashMap<>());
			System.out.println("");
		}
		
		System.out.println("Concurrent Read/Write using iterator data");
		for(int i=0;i<10;i++) {
			//Not allowed for hash map gives "java.util.ConcurrentModificationException"
			//classTest.testReadWriteIterator("HashMap", new HashMap<>());
			
			//Not allowed for HashTable gives "java.util.ConcurrentModificationException"
			//classTest.testReadWriteIterator("HashTable", new Hashtable<>());
			
			classTest.testReadWriteIterator("ConcurrentHashMap", new ConcurrentHashMap<>());
			System.out.println("");
		}
		
	}
	
	private void testReadOnly(String mapType, Map<String,String> dataMap) {
		dataMap = insertRecords(dataMap);
		IMapConcurrentTest classToTest = new MapConcurrentRead(dataMap);
		try {
			initializeStartAndRecordTime(mapType, classToTest);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void testReadWriteIterator(String mapType, Map<String,String> dataMap) {
		dataMap = insertRecords(dataMap);
		IMapConcurrentTest classToTest = new MapConcurrentReadWriteIterator(dataMap);
		try {
			initializeStartAndRecordTime(mapType, classToTest);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void testReadWrite(String mapType, Map<String,String> dataMap) {
		dataMap = insertRecords(dataMap);
		IMapConcurrentTest classToTest = new MapConcurrentReadWrite(dataMap);
		try {
			initializeStartAndRecordTime(mapType, classToTest);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String,String> insertRecords(Map<String,String> inputMap){
		for(int i=0;i<10000;i++) {
			inputMap.put("K"+i, "V"+i);
		}
		return inputMap;
	}
	
	public void initializeStartAndRecordTime(String testDetail, IMapConcurrentTest classToRun) throws InterruptedException {
		
		long startTime = System.currentTimeMillis();
		Thread t1 = new Thread((Runnable)classToRun, testDetail+"T1");
		Thread t2 = new Thread((Runnable)classToRun, testDetail+"T2");
		Thread t3 = new Thread((Runnable)classToRun, testDetail+"T3");
		Thread t4 = new Thread((Runnable)classToRun, testDetail+"T4");
		Thread t5 = new Thread((Runnable)classToRun, testDetail+"T5");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		long endTime = System.currentTimeMillis();
		System.out.println("Test :\""+testDetail+"\", Completed execution in : "+(endTime-startTime)+" ms");
	
	}
}
