package com.yuhang.stock.stockdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class StockDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockDataServiceApplication.class, args);
	}

}
