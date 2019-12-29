package com.majesco.poc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Config.class)
public class RedisClusterApplicationTests {

	@Autowired
	RedisTemplate<String, String> redisTemplate;

	@Test
	public void testReplication() throws InterruptedException {

		for (int i = 0; i < 10; i++) {
			String key = "XXX:" + i;
			System.out.println("Inserting Key : " + key);
			redisTemplate.opsForValue().set(key, key);
			Thread.sleep(2000);
		}

		for (int i = 0; i < 10; i++) {
			String key = "XXX:" + i;
			System.out.println(redisTemplate.opsForValue().get(key));
		}
	}

}
