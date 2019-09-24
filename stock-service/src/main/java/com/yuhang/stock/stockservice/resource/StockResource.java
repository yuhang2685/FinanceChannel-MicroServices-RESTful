package com.yuhang.stock.stockservice.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.yuhang.stock.stockservice.models.StockCurrentPrice;
import com.yuhang.stock.stockservice.models.StockQuote;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {
	
	// Note RestTemplate object is the Spring REST Client for communication with external RESTful APIs.
	// See examples in https://www.baeldung.com/rest-template
	@Autowired
	RestTemplate restTemplate;
	
	// It works from Alpha Vantage!
	// Query db-service to obtain a list of Strings by getting a HTTP-GET response.	
	//@GetMapping("/{username}")
	//public List<Quote> getStock(@PathVariable("username") final String userName){
	public List<StockCurrentPrice> getStock(@PathVariable("username") final String userName){
		
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
	    	  		// alpha-vantage-API-client
	    	  		//Quote qut = restTemplate.getForObject("http://stock-data-service/rest/datasource/stock/" + symbol, Quote.class);
	    	  		StockCurrentPrice qut = restTemplate.getForObject("http://alpha-vantage-API-client/stockQuotes/" + symbol, StockCurrentPrice.class);
	    	  		//return new StockCurrentPrice(qut.getSymbol(), qut.getPrice());
	    	  		return qut;
	    	  	}
	          )
          .collect(Collectors.toList());	
		
	}
	

	// Delete the specific symbol from the specific user's watch-list.
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/{username}/{symbol}")
	public void deleteSymbol (@PathVariable("username") String username, @PathVariable("symbol") String symbol) {
		String entityUrl = "http://db-service/rest/db/" + username + "/" + symbol;
		restTemplate.delete(entityUrl);
	}
	
	// The method below is prepared for Angular UI of port# 4200.
	// Enabling CORS by Controller method CORS configuration:
	// Include CORS access control headers in its response 
	// by adding a @CrossOrigin annotation to the handler method.
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{username}")
	public List<StockQuote> getStockQuotes(@PathVariable("username") final String userName){
		
		List<StockQuote> rst = new ArrayList<StockQuote>();
		StockQuote s1 = new StockQuote(1, "MSFT", 138.25, 141.01, 141.65, 138.25, 32979846, -1.16);
		StockQuote s2 = new StockQuote(2, "GOOG", 1229.93, 1233.12, 1243.27, 1223.08, 1573080, -0.7);
		StockQuote s3 = new StockQuote(3, "AAPL", 217.73, 221.38, 222.56, 217.48, 32105176, -1.46);
		StockQuote s4 = new StockQuote(4, "AMZN", 1794.16, 1821.71, 1830.63, 1781.00, 4324478, -1.5);
		rst.add(s1);
		rst.add(s2);
		rst.add(s3);
		rst.add(s4);
		return rst;
	}
	
}
