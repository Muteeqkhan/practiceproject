/*
 * package com.excelsoft.redis;
 * 
 * import java.io.IOException;
 * 
 * import org.redisson.Redisson; import org.redisson.api.RList; import
 * org.redisson.api.RedissonClient;
 * 
 * public class Demo {
 * 
 * public static void main(String[] args) throws IOException {
 * 
 * RedissonClient redisson = Redisson.create();
 * 
 * // implements java.util.List RList<String> list = redisson.getList("myList");
 * list.add("1"); list.add("2"); list.add("3");
 * 
 * boolean contains = list.contains("1");
 * 
 * System.out.println("List size: " + list.size());
 * System.out.println("Is list contains value '1': " + contains);
 * 
 * for (String element : list) { System.err.println("List element: " + element);
 * }
 * 
 * redisson.shutdown(); }
 * 
 * }
 */