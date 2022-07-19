
package com.excelsoft.redis;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class MapExample {

	public static void main(String[] args) throws IOException { 
		// connects to127.0.0.1:6379 by default // 
		Config config = Config.fromYAML(new File("src/main/resources/redis.yml")); 
		RedissonClient client = Redisson.create(config);
  
  // implements java.util.concurrent.ConcurrentMap
		RMapCache<String, Object> map = client.getMapCache("any");
  
  map.put("key", "value",50000,TimeUnit.MILLISECONDS);
  
  System.err.println(map.get("key"));
  
//  boolean contains = map.containsKey("a"); System.err.println("Map size: " +
//  map.size()); System.err.println("Is map contains key 'a': " + contains);
//  
//  Integer value = map.get("c"); 
//  System.err.println("Value mapped by key 'c': "
//  + value);
//  
//  boolean added = map.putIfAbsent("d", 4)==null;
//  System.err.println("Is value mapped by key 'c' added: " + added);
//  
//  
//  Set<String> keySet = map.keySet(); for(String kString:keySet) {
//  System.err.println(map.get(kString)); }
//  
//  
//  RMapCache<String, Object> map2 = client.getMapCache("any");
//  System.err.println(map2.get("key")); client.shutdown();
  
  client.shutdown();
  }
}
