package com.vilderlee.eshopinventory;

import com.vilderlee.eshopinventory.redis.RedisDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EshopInventoryApplicationTests {

	@Autowired
	private RedisDAO redisDAO;

	@Test
	public void contextLoads() {
		redisDAO.set("user.name","vilderlee");
		System.out.println(redisDAO.get("user.name"));
	}


	@Test
	public void del(){
		redisDAO.delete("key为product:inventory:1");
		System.out.println(redisDAO.get("key为product:inventory:1"));
	}
}
