package org.yuhangz.resources;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuhangz.AlphaVantageConnector;
import org.yuhangz.TimeSeries;
import org.yuhangz.input.timeseries.Interval;
import org.yuhangz.input.timeseries.OutputSize;
import org.yuhangz.models.StockCurrentPrice;
import org.yuhangz.models.StockQuote;
import org.yuhangz.output.AlphaVantageException;

import org.yuhangz.output.timeseries.Daily;
import org.yuhangz.output.timeseries.IntraDay;
import org.yuhangz.output.timeseries.data.StockData;

@RestController
@RequestMapping("/stockQuotes")
public class GlobalStockResource {
	

	@RequestMapping("/{symbol}")
	public StockQuote getInfo(@PathVariable("symbol") String symbol) {
	    String apiKey = "SJZFZ36I8X46ASGJ";
	    int timeout = 3000;
	    AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
	    
	    System.out.println("STOCK:");
	    System.out.println("");
	    TimeSeries stockTimeSeries = new TimeSeries(apiConnector);
	    
	    try {
	      //IntraDay response = stockTimeSeries.intraDay("MSFT", Interval.ONE_MIN, OutputSize.COMPACT);
	      Daily response = stockTimeSeries.daily(symbol);
	      Map<String, String> metaData = response.getMetaData();
	      System.out.println("Information: " + metaData.get("1. Information"));
	      System.out.println("Stock: " + metaData.get("2. Symbol"));
	      
	      List<StockData> stockData = response.getStockData();
	      
	      //System.out.println(stockData.get(0).getDateTime());
	      //System.out.println(stockData.get(0).getClose());
	      //System.out.println(stockData.get(1).getClose());
	      
	      double close = stockData.get(0).getClose();
	      double prevClose = stockData.get(1).getClose();
	      double change = 0.0;
	      if (prevClose !=0)
	        change = (close > prevClose) ? (+ ((close - prevClose) / prevClose)) : (- ((prevClose - close) / prevClose));
	      change *= 100;	     
	      change = BigDecimal.valueOf(change).setScale(2, RoundingMode.HALF_UP).doubleValue();
	      
	      //return new StockCurrentPrice(symbol, stockData.get(0).getDateTime(), stockData.get(0).getClose(), stockData.get(1).getClose());
	      return new StockQuote(
	    		  				symbol,
	    		  				close,
	    		  				stockData.get(0).getOpen(),
	    		  				stockData.get(0).getHigh(),
	    		  				stockData.get(0).getLow(),
	    		  				stockData.get(0).getVolume(),
	    		  				change
	    		  				);
	      /*
	      stockData.forEach(stock -> {
	        System.out.println("date:   " + stock.getDateTime());
	        System.out.println("open:   " + stock.getOpen());
	        System.out.println("high:   " + stock.getHigh());
	        System.out.println("low:    " + stock.getLow());
	        System.out.println("close:  " + stock.getClose());
	        System.out.println("volume: " + stock.getVolume());
	      });
	      */
	    } catch (AlphaVantageException e) {
	      System.out.println("something went wrong");
	      return null;
	    }
	    
	}
}
