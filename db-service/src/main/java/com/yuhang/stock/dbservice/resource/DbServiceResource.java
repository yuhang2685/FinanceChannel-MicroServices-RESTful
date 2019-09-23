package com.yuhang.stock.dbservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuhang.stock.dbservice.model.Quote;
import com.yuhang.stock.dbservice.model.Quotes;
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
	
	// Input user name to return a list of Strings.
	@GetMapping("/{username}")	
	public List<String> getQuotes(@PathVariable("username") final String username){
		
		return getQuotesByUserName(username);
	}
	
	// Input is a Quotes object which has a username and a list of Strings.
	// For each String, it constructs a Quote object using username and the String
	// and then save each above constructed Quote object in DB.
	// Then it returns the list of Strings for the specified user.
    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes) {

        quotes.getQuotes()
                .stream()
                .map(quote -> new Quote(quotes.getUserName(), quote))
                .forEach(quote -> quotesRepository.save(quote));
        return getQuotesByUserName(quotes.getUserName());
    }


    @PostMapping("/delete/{username}")
    public List<String> delete(@PathVariable("username") final String username) {

        List<Quote> quotes = quotesRepository.findByUserName(username);
        
        quotes.stream().forEach(quote -> quotesRepository.delete(quote));
                
        return getQuotesByUserName(username);
    }	
	

    // From db, JPA gets the list of Quote objects where each one has username matched with the given username.
    // It returns a list of Strings constructed by putting together the Strings in Quote objects.
    private List<String> getQuotesByUserName(@PathVariable("username") String username) {
        return quotesRepository.findByUserName(username)
        		.stream()
				//.map(Quote::getQuote)
				.map(quote -> {
					return quote.getQuote();					
				})
				.collect(Collectors.toList());
    }
	
	
	
	
	
	
	
	
	

}