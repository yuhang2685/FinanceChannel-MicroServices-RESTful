package org.yuhangz.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuhangz.AlphaVantageConnector;
import org.yuhangz.ForeignExchange;
import org.yuhangz.output.AlphaVantageException;
import org.yuhangz.output.exchange.CurrencyExchange;
import org.yuhangz.output.exchange.data.CurrencyExchangeData;



@RestController
@RequestMapping("/forex")
public class ForeignExchangeRescource {

	@RequestMapping("/{symbol}")
	public CurrencyExchangeData getMovieInfo(@PathVariable("symbol") String symbol) {
		String apiKey = "SJZFZ36I8X46ASGJ";
	    int timeout = 3000;
	    AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
	    ForeignExchange foreignExchange = new ForeignExchange(apiConnector);

	    try {
	      CurrencyExchange currencyExchange = foreignExchange.currencyExchangeRate("USD", symbol);
	      CurrencyExchangeData currencyExchangeData = currencyExchange.getData();

	      System.out.println("from currency code: " + currencyExchangeData.getFromCurrencyCode());
	      System.out.println("from currency name: " + currencyExchangeData.getFromCurrencyName());
	      System.out.println("to currency code:   " + currencyExchangeData.getToCurrencyCode());
	      System.out.println("to currency name:   " + currencyExchangeData.getToCurrencyName());
	      System.out.println("exchange rate:      " + currencyExchangeData.getExchangeRate());
	      System.out.println("last refresh:       " + currencyExchangeData.getTime());
	      
	      return currencyExchangeData;
	      
	    } catch (AlphaVantageException e) {
	      System.out.println("something went wrong");
	      return null;
	    }
	}
}
