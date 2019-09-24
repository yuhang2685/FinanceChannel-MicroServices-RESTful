package com.yuhang.stock.dbservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
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
	// Dependency Injection.
	public DbServiceResource(QuotesRepository quotesRepository) {
		this.quotesRepository = quotesRepository;
	}
	
	// This service returns a list of stock symbols by user name.
	@GetMapping("/{username}")	
	public List<String> getQuotes(@PathVariable("username") final String username){
		
		return getQuotesByUserName(username);
	}
	
	
	// Input is a Quotes object which is comprised of a username and a list of symbols.
	// For each symbol, it constructs a Quote object using username and the symbol,
	// and then save each above constructed Quote object in DB.
	// Then it returns the list of symbols for the specified user.
    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes) {

        quotes.getQuotes()
                .stream()
                .map(quote -> new Quote(quotes.getUserName(), quote))
                .forEach(quote -> quotesRepository.save(quote));
        return getQuotesByUserName(quotes.getUserName());
    }


    // First, JPA gets from DB the list of Quote objects where each one has its field username matched with the given username.
    // Then, JPA deletes every Quote object in the list from DB.
    // Finally, it should return the list of (expected to be empty) Quote objects having username matched with the given username.
    @PostMapping("/delete/{username}")
    public List<String> delete(@PathVariable("username") final String username) {

        List<Quote> quotes = quotesRepository.findByUserName(username);        
        quotes.stream().forEach(quote -> quotesRepository.delete(quote));               
        return getQuotesByUserName(username);
    }		

    // First, JPA gets from DB the list of Quote objects where each one has its field username matched with the given username.
    // Then, it returns a list of symbols by putting together the symbol field of each Quote object.
    private List<String> getQuotesByUserName(@PathVariable("username") String username) {
        return quotesRepository.findByUserName(username)
        		.stream()
				//.map(Quote::getQuote)
				.map(quote -> {
					return quote.getQuote();					
				})
				.collect(Collectors.toList());
    }

    
    // First, JPA gets from DB the list of Quote objects where each one has the specified username and symbol.
    // Then, JPA deletes every Quote object in the list from DB.
    // Finally, it returns the list of (expected to be empty) Quote objects having the specific username and symbol.
    @DeleteMapping("/{username}/{symbol}")
    public List<Quote> deleteSymbol(@PathVariable("username") String username, @PathVariable("symbol") String symbol) {

        List<Quote> quotes = getQuoteByUserNameAndSymbol(username, symbol);        
        quotes.stream().forEach(quote -> quotesRepository.delete(quote));                
        return getQuoteByUserNameAndSymbol(username, symbol);
    }	
    
    // Obtain the list of records where each has the specific username and symbol.
    //@GetMapping("/{username}/{symbol}")	
    public List<Quote> getQuoteByUserNameAndSymbol (String username, String symbol) {
		return quotesRepository.findByUserNameAndSymbol(username, symbol);
	}
	
}
