package org.yuhangz.resources;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuhangz.AlphaVantageConnector;
import org.yuhangz.DigitalCurrencies;
import org.yuhangz.input.digitalcurrencies.Market;
import org.yuhangz.output.AlphaVantageException;
import org.yuhangz.output.digitalcurrencies.IntraDay;
import org.yuhangz.output.digitalcurrencies.data.SimpelDigitalCurrencyData;

@RestController
@RequestMapping("/cryptoCurrency")
public class CryptoCurrenciesResource {
	

	@RequestMapping("/{symbol}")
	public void getMovieInfo(@PathVariable("symbol") String symbol) {
		
	    String apiKey = "SJZFZ36I8X46ASGJ";
	    int timeout = 3000;
	    AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
	    DigitalCurrencies digitalCurrencies = new DigitalCurrencies(apiConnector);

	    try {
	      IntraDay response = digitalCurrencies.intraDay("BTC", Market.USD);
	      Map<String, String> metaData = response.getMetaData();
	      System.out.println("Information: " + metaData.get("1. Information"));
	      System.out.println("Digital Currency Code: " + metaData.get("2. Digital Currency Code"));

	      List<SimpelDigitalCurrencyData> digitalData = response.getDigitalData();
	      digitalData.forEach(data -> {
	        System.out.println("date:       " + data.getDateTime());
	        System.out.println("price A:    " + data.getPriceA());
	        System.out.println("price B:    " + data.getPriceB());
	        System.out.println("volume:     " + data.getVolume());
	        System.out.println("market cap: " + data.getMarketCap());
	      });
	    } catch (AlphaVantageException e) {
	      System.out.println("something went wrong");
	    }
	  }
	
}
