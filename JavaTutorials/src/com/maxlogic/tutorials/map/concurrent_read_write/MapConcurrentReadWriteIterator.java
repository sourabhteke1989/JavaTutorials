package com.maxlogic.tutorials.map.concurrent_read_write;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapConcurrentReadWriteIterator implements Runnable, IMapConcurrentTest {
	Map<String,String> dataMap = null;
	
	public MapConcurrentReadWriteIterator(Map data) {
		dataMap = data;
	}
	
	@Override
	public void run() {
		Thread t = Thread.currentThread();
		int i=0;
		for(String key : dataMap.keySet()) {
			String value = dataMap.get(key);
			int no = i+10;
			if(no>=10000) no = no-10000;
			//dataMap.put("K"+no, t.getName());
			i++;
		}
		dataMap.put("K", t.getName());
	}
	
}