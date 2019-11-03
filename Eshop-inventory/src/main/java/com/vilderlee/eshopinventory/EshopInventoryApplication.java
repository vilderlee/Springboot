package com.vilderlee.eshopinventory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.vilderlee.eshopinventory.mapper")
@ServletComponentScan("com.vilderlee.eshopinventory.servlet")
@EnableTransactionManagement
public class EshopInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopInventoryApplication.class, args);
	}

}
