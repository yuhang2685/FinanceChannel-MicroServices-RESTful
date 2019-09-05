package com.yuhang.stock.dbservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuhang.stock.dbservice.repository.QuotesRepository;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

	private QuotesRepository quotesRepository;
	
	// Constructor needed for handling NullPointException?
	// Dependency Injection?
	public DbServiceResource(QuotesRepository quotesRepository) {
		this.quotesRepository = quotesRepository;
	}
	
	@GetMapping("/{username}")
	// Input user name to fetch a list of quotes.
	public List<String> getQuotes(@PathVariable("username") final String username){
		
		return quotesRepository.findByUserName(username)
				.stream()
				//.map(Quote::getQuote)
				.map(quote -> {
					return quote.getQuote();					
				})
				.collect(Collectors.toList());
	}
	

}
