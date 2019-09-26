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
	
	// Dependency Injection. Constructor needed for handling NullPointException?
	public DbServiceResource(QuotesRepository quotesRepository) {
		this.quotesRepository = quotesRepository;
	}
	

	/**
	 * API for getting all symbols of a specific user.
	 * @param username
	 * @return List<String> All symbols of a specific user.
	 * 
	 */
	@GetMapping("/{username}")	
	public List<String> getUserAllSymbols(@PathVariable("username") final String username){		
		return getUserSymbols(username);
	}
	

	/**
	 * API for adding multiple symbols for a specific user.
	 * Note since it is an API for POST, it is better to accept an entity wrapping all necessary data, like "Quotes".
	 * @param quotes
	 * @returnList<String> All symbols of a specific user.
	 *  
	 */
    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes) {
        quotes.getQuotes()
                .stream()
                .map(quote -> new Quote(quotes.getUserName(), quote))
                .forEach(quote -> quotesRepository.save(quote));
        return getUserSymbols(quotes.getUserName());
    }


   /**
     * API for deleting all symbols of a specific user.
     * @param username
     * @return List<String> All symbols of a specific user (expected to be empty).
     * 
     */
    @PostMapping("/delete/{username}")
    public List<String> deleteAllSymbolsOfUser(@PathVariable("username") final String username) {
        List<Quote> quotes = quotesRepository.findByUserName(username);        
        quotes.stream().forEach(quote -> quotesRepository.delete(quote));               
        return getUserSymbols(username);
    }

    
    /**
     * Function to obtain all symbols of a specific user.
     * @param username
     * @return List<String> All symbols of a specific user.
     * 
     */
    private List<String> getUserSymbols(@PathVariable("username") String username) {
        return quotesRepository.findByUserName(username)
        		.stream()
				//Alternatively use .map(Quote::getQuote)
				.map(quote -> {
					return quote.getQuote();					
				})
				.collect(Collectors.toList());
    }

    
    /**
     * API for deleting a specific symbol of a specific user.
     * @param username
     * @param symbol
     * @return List<Quote> All data records for the specific user and symbol (expected to be empty).
     * 
     */
    @DeleteMapping("/{username}/{symbol}")
    public List<Quote> deleteUserSymbol(@PathVariable("username") String username, @PathVariable("symbol") String symbol) {
        List<Quote> quotes = getQuotesUserAndSymbol(username, symbol);        
        quotes.stream().forEach(quote -> quotesRepository.delete(quote));                
        return getQuotesUserAndSymbol(username, symbol);
    }	
    

    /**
     * Function to obtain data records for the specific user and symbol.
     * @param username
     * @param symbol
     * @return List<Quote> All data records for the specific user and symbol.
     * 
     */
    public List<Quote> getQuotesUserAndSymbol (String username, String symbol) {
		return quotesRepository.findByUserNameAndSymbol(username, symbol);
	}
	
}
