package com.biplab.user.userclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class UserClientApplication {

	@Autowired
	private DiscoveryClient discoveryClient;

	public static void main(String[] args) {
		SpringApplication.run(UserClientApplication.class, args);

		System.out.println();
	}

	@PostConstruct
	public void boot() {
		List<ServiceInstance> list = discoveryClient.getInstances("user-service");
		if (list != null && list.size() > 0 ) {

		}

	}
}
