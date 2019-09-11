package org.yuhangz;

import org.yuhangz.input.Function;
import org.yuhangz.input.exchange.FromCurrency;
import org.yuhangz.input.exchange.ToCurrency;
import org.yuhangz.input.symbol.FromSymbol;
import org.yuhangz.input.symbol.ToSymbol;
import org.yuhangz.input.timeseries.OutputSize;
import org.yuhangz.output.AlphaVantageException;
import org.yuhangz.output.exchange.CurrencyExchange;
import org.yuhangz.output.exchange.Daily;

/**
 * Foreign Exchange Rate
 */
public class ForeignExchange {

  private final ApiConnector apiConnector;

  /**
   * Constructs a ForeignExchange Data api endpoint with the help of an {@link ApiConnector}.
   *
   * @param apiConnector the connection to the api
   */
  public ForeignExchange(ApiConnector apiConnector) {
    this.apiConnector = apiConnector;
  }

  /**
   * Functionality to show the ratio exchange between two currencies.
   *
   * @return {@link CurrencyExchange} data
   */
  public CurrencyExchange currencyExchangeRate(String fromCCY, String toCCY) throws AlphaVantageException {
    // YH: Obtain String "json" which is the unprocessed response from Alpha Vantage API.
	// YH: Method "apiConnector.getRequest" is a general method using variant arguments as request parameters 
	//     to obtain the response as String from Alpha Vantage API
	String json = apiConnector.getRequest(Function.CURRENCY_EXCHANGE_RATE, new FromCurrency(fromCCY), new ToCurrency(toCCY));
    // YH: Process the response and construct a CurrencyExchange object
	return CurrencyExchange.from(json);
  }

  /**
   * This API returns daily time series (date, daily open, daily high, daily low, daily close, daily volume) of the equity specified.
   *
   * @param fromSymbol the forex symbol convert from
   * @param toSymbol the forex symbol convert to
   * @param outputSize the specification of the amount of returned data points {@link OutputSize}
   * @return {@link Daily} daily forex data
   */
  public Daily daily(String fromSymbol, String toSymbol, OutputSize outputSize) throws AlphaVantageException {
    String json = apiConnector.getRequest(Function.FX_DAILY, new FromSymbol(fromSymbol), new ToSymbol(toSymbol), outputSize);
    return Daily.from(json);
  }
}
