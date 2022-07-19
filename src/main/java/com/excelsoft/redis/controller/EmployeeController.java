package com.excelsoft.redis.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.excelsoft.redis.exception.ResouceNotFoundException;
import com.excelsoft.redis.model.Employee;
import com.excelsoft.redis.repository.EmployeeRepository;

import org.redisson.Redisson;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

//	@Autowired
//	Config config;

	public Config configRedis() throws IOException {
		System.err.println("creating the bean");
//        return Config.fromYAML(new File("src/main/resources/redis.yml"));
		Config config = new Config();
		int size=0;
		if(size>1) {
			config.useClusterServers().setScanInterval(2000)
			.addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001")
			.addNodeAddress("redis://127.0.0.1:7002")
			.setIdleConnectionTimeout(10000).setConnectTimeout(10000)
			.setSubscriptionConnectionMinimumIdleSize(1)
			.setIdleConnectionTimeout(10000).setTimeout(3000)
			.setRetryAttempts(3).setSlaveConnectionPoolSize(0);

		}
		config.useSingleServer().setAddress("redis://127.0.0.1:6379");
		config.useSingleServer().setConnectTimeout(10000);
		config.useSingleServer().setSubscriptionConnectionMinimumIdleSize(1);
		config.useSingleServer().setIdleConnectionTimeout(10000);
		config.useSingleServer().setTimeout(3000);
		config.useSingleServer().setRetryAttempts(3);
		config.useSingleServer().setDatabase(0);

		return config;
		
	}

	public RedissonClient redissonClient() {
		try {
			return Redisson.create(configRedis());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	RedissonClient config = redissonClient();

	RMapCache<String, Object> map = config.getMapCache("any");

	@GetMapping("/saveingData")
	public String save22() {

		map.put("key", "value", 10000, TimeUnit.MILLISECONDS);
		return "value";
	}

	@GetMapping("/getData")
	public String name() {
		/*
		 * if(map.get("key")!=null) { map.put("key", "value",10000,
		 * TimeUnit.MILLISECONDS); }
		 */
		return (String) map.get("key");
	}

	/*
	 * @PostMapping("/employees") public Employee addEmployee(@RequestBody Employee
	 * employee) {
	 * 
	 * return employeeRepository.save(employee); }
	 * 
	 * @GetMapping("/") public ResponseEntity<List<Employee>> getAllEmployees() {
	 * return ResponseEntity.ok(employeeRepository.findAll()); }
	 * 
	 * @GetMapping("employees/{employeeId}")
	 * 
	 * @Cacheable(value = "employees", key = "#employeeId") public Employee
	 * findEmployeeById(@PathVariable(value = "employeeId") Integer employeeId) {
	 * System.out.println("Employee fetching from database:: " + employeeId); return
	 * employeeRepository.findById(employeeId) .orElseThrow(() -> new
	 * ResouceNotFoundException("Employee not found" + employeeId));
	 * 
	 * }
	 * 
	 * @PutMapping("employees/{employeeId}")
	 * 
	 * @CachePut(value = "employees", key = "#employeeId") public Employee
	 * updateEmployee(@PathVariable(value = "employeeId") Integer employeeId,
	 * 
	 * @RequestBody Employee employeeDetails) { Employee employee =
	 * employeeRepository.findById(employeeId) .orElseThrow(() -> new
	 * ResouceNotFoundException("Employee not found for this id :: " + employeeId));
	 * employee.setName(employeeDetails.getName()); final Employee updatedEmployee =
	 * employeeRepository.save(employee); return updatedEmployee;
	 * 
	 * }
	 * 
	 * @DeleteMapping("employees/{employeeId}")
	 * 
	 * @CacheEvict(value = "employees", key = "#employeeId") public void
	 * deleteEmployee(@PathVariable(value = "employeeId") Integer employeeId) {
	 * Employee employee = employeeRepository.findById(employeeId) .orElseThrow(()
	 * -> new ResouceNotFoundException("Employee not found" + employeeId));
	 * employeeRepository.delete(employee); }
	 */
}
