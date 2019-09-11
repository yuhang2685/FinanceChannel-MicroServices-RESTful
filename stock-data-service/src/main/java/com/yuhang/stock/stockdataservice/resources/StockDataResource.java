package com.yuhang.stock.stockdataservice.resources;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuhang.stock.stockdataservice.models.AlphaVantageConnector;
import com.yuhang.stock.stockdataservice.models.Quote;
import com.yuhang.stock.stockdataservice.models.TimeSeries;
import com.yuhang.stock.stockdataservice.models.input.timeseries.Interval;
import com.yuhang.stock.stockdataservice.models.input.timeseries.OutputSize;
import com.yuhang.stock.stockdataservice.models.output.timeseries.IntraDay;
import com.yuhang.stock.stockdataservice.models.output.timeseries.data.StockData;


@RestController
@RequestMapping("/rest/datasource")
public class StockDataResource {

	/**
	 * 
	 * Functionality to supply the stock quote data
	 * 
	 * @param stock symbol
	 * @return {@link Quote} stock quote data
	 * 
	 */
	@RequestMapping("stock/{symbol}")
    //public Quote getQuote(@PathVariable("symbol") String stockSymbol) {
	public void getQuote(@PathVariable("symbol") String stockSymbol) {
		
		//return new Quote(stockSymbol, new BigDecimal(128.6600));
	    String apiKey = "SJZFZ36I8X46ASGJ";
	    int timeout = 3000;
	    AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
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
	    } //catch (AlphaVantageException e) {
	      catch (Exception e) {
	      System.out.println("something went wrong");
	    }
	}
}
