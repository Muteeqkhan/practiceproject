package com.excelsoft.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class RedisCaheApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisCaheApplication.class, args);
	}
	
	

	/*
	 * @Bean(name = "config",destroyMethod="shutdown") public RedissonClient
	 * config() throws IOException { Config config=Config.fromYAML(new
	 * File("src/main/resources/redis.yml")); return Redisson.create(config); }
	 *
	 */

	
	
}
