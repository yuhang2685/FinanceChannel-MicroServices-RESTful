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

import com.yuhang.stock.stockservice.models.Quote;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {
	
	// Use the RestTemplate object in Bean
	@Autowired
	RestTemplate restTemplate;
	
	
	// Query db-service to obtain a list of Strings by getting a HTTP-GET response.	
	@GetMapping("/{username}")
	public List<Quote> getStock(@PathVariable("username") final String userName){

		// First we visit db-service by username to get a list of Strings.
		// Then we use each String to query YahooFinance to obtain a Stock object.
		
		// Can we use simpler ways? like a wrapper object in movie-rating project?
		ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://db-service/rest/db/" + userName, HttpMethod.GET, 
			null, new ParameterizedTypeReference<List<String>>() {});			
		// From quoteResponse we obtain a list of Strings
		List<String> symbols = quoteResponse.getBody();
		
		return symbols.stream()
	      		.map(symbol -> 
	    	  	{
	    	  		Quote qut = restTemplate.getForObject("http://stock-data-service/rest/datasource/stock/" + symbol, Quote.class);
	    	  		return new Quote(qut.getSymbol(), qut.getPrice());
	    	  	}
	          )
          .collect(Collectors.toList());	
		
	}
	
}
