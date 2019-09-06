package com.yuhang.stock.stockservice.resource;

import java.io.IOException;
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

import yahoofinance.Stock;
import yahoofinance.YahooFinance;


@RestController
@RequestMapping("/rest/stock")
public class StockResource {
	
	// Use the RestTemplate object in Bean
	@Autowired
	RestTemplate restTemplate;
	
	
	// Query db-service to obtain a list of Strings by getting a HTTP-GET response.	
	@GetMapping("/{username}")
	public List<Stock> getStock(@PathVariable("username") final String userName){

		// First we visit db-service by username to get a list of Strings.
		// Then we use each String to query YahooFinance to obtain a Stock object.
		
		// Can we use simpler ways? like a wrapper object in movie-rating project?
		ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://localhost:8080/rest/db/" + userName, HttpMethod.GET, 
			null, new ParameterizedTypeReference<List<String>>() {});			
		// From quoteResponse we obtain a list of Strings
		List<String> quotes = quoteResponse.getBody();
		// Can also use (this::getStockPrice) 		
		return quotes.stream().map(quote -> getStockPrice(quote))
				.collect(Collectors.toList());
	}

	// This method uses YahooFinance to get Stock object by a String.
	private Stock getStockPrice(String quote) {
		// TODO Auto-generated method stub
		try {
			return YahooFinance.get(quote);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Stock(quote);
		}
	}

}
