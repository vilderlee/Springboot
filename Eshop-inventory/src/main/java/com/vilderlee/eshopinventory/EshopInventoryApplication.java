package com.vilderlee.eshopinventory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.vilderlee.eshopinventory.dao")
@EnableTransactionManagement
public class EshopInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopInventoryApplication.class, args);
	}

}
