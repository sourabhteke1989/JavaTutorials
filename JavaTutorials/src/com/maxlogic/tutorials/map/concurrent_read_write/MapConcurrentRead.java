package com.maxlogic.tutorials.map.concurrent_read_write;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapConcurrentRead implements Runnable, IMapConcurrentTest {
	
	Map<String,String> dataMap = null;
	
	public MapConcurrentRead(Map data) {
		dataMap = data;
	}
	
	@Override
	public void run() {
		Thread t = Thread.currentThread();
		for(int i=0;i<10000;i++) {
			//System.out.println("Thread : "+t.getName()+"::"+t.getId()+", Key:"+entry.getKey()+", value:"+entry.getValue());
			String value = dataMap.get("K"+i);
		}
	}
	
}
