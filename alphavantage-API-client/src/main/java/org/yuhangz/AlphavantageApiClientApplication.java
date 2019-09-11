package org.yuhangz;

import java.util.List;
import java.util.Map;

import org.yuhangz.AlphaVantageConnector;
import org.yuhangz.TimeSeries;
import org.yuhangz.input.timeseries.Interval;
import org.yuhangz.input.timeseries.OutputSize;
import org.yuhangz.output.AlphaVantageException;
import org.yuhangz.output.digitalcurrencies.data.SimpelDigitalCurrencyData;
import org.yuhangz.output.exchange.CurrencyExchange;
import org.yuhangz.output.exchange.data.CurrencyExchangeData;
import org.yuhangz.output.timeseries.IntraDay;
import org.yuhangz.output.timeseries.data.StockData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AlphavantageApiClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlphavantageApiClientApplication.class, args);
		/*	
	    String apiKey = "SJZFZ36I8X46ASGJ";
	    int timeout = 3000;
	    AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
	    
	    System.out.println("STOCK:");
	    System.out.println("");
	    TimeSeries stockTimeSeries = new TimeSeries(apiConnector);
	    
	    try {
	      IntraDay response = stockTimeSeries.intraDay("MSFT", Interval.ONE_MIN, OutputSize.COMPACT);
	      Map<String, String> metaData = response.getMetaData();
	      System.out.println("Information: " + metaData.get("1. Information"));
	      System.out.println("Stock: " + metaData.get("2. Symbol"));
	      
	      List<StockData> stockData = response.getStockData();
	      stockData.forEach(stock -> {
	        System.out.println("date:   " + stock.getDateTime());
	        System.out.println("open:   " + stock.getOpen());
	        System.out.println("high:   " + stock.getHigh());
	        System.out.println("low:    " + stock.getLow());
	        System.out.println("close:  " + stock.getClose());
	        System.out.println("volume: " + stock.getVolume());
	      });
	    } catch (AlphaVantageException e) {
	      System.out.println("something went wrong");
	    }
	    */
	    

	  }	

}
