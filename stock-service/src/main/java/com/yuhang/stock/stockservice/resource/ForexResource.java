package com.yuhang.stock.stockservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.yuhang.stock.stockservice.models.CurrencyExchangeData;
import com.yuhang.stock.stockservice.models.StockCurrentPrice;

@RestController
@RequestMapping("/rest/forex")
public class ForexResource {
		
		// Use the RestTemplate object in Bean
		@Autowired
		RestTemplate restTemplate;
				
		@GetMapping("/{toCurrency}")
		public CurrencyExchangeData getForex(@PathVariable("toCurrency") final String symbol){
			
			System.out.println("######################");
			
			CurrencyExchangeData qut = restTemplate.getForObject("http://alpha-vantage-API-client/forex/" + symbol, CurrencyExchangeData.class);
		    
		    return qut;  			
			
		}
	
}
