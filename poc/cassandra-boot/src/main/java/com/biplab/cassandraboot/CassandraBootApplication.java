package com.biplab.cassandraboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@EnableCassandraRepositories(basePackages = "com.biplab.cassandraboot.repository")
@SpringBootApplication
public class CassandraBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CassandraBootApplication.class, args);
	}

}
