package com.yuhang.stock.stockdataservice.resources;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuhang.stock.stockdataservice.models.Quote;


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
    public Quote getQuote(@PathVariable("symbol") String stockSymbol) {
		
		return new Quote(stockSymbol, new BigDecimal(128.6600));
	}
}
