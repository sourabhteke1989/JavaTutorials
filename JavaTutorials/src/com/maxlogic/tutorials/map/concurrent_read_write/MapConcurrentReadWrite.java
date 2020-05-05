package com.maxlogic.tutorials.map.concurrent_read_write;

import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

public class MapConcurrentReadWrite implements Runnable, IMapConcurrentTest {
	Map<String,String> dataMap = null;
	
	public MapConcurrentReadWrite(Map data) {
		dataMap = data;
	}
	
	@Override
	public void run() {
		Thread t = Thread.currentThread();
		for(int i=0;i<10000;i++) {
			String value = dataMap.get("K"+i);
			int no = i+10;
			if(no>=10000) no = no-10000;
			dataMap.put("K"+no, t.getName());
		}
		dataMap.put("K", t.getName());
	}
	
}
