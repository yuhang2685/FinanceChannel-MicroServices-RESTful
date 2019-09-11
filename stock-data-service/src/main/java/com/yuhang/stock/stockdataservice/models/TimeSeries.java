package com.yuhang.stock.stockdataservice.models;

import com.yuhang.stock.stockdataservice.models.input.Function;
import com.yuhang.stock.stockdataservice.models.input.Symbol;
import com.yuhang.stock.stockdataservice.models.input.timeseries.Interval;
import com.yuhang.stock.stockdataservice.models.input.timeseries.OutputSize;
import com.yuhang.stock.stockdataservice.models.output.timeseries.IntraDay;

/**
 * The Stock Time Series Data provides realtime and historical equity data in 4 different temporal resolutions:
 * (1) intraday, (2) daily, (3) weekly, and (4) monthly.
 */
public class TimeSeries {
  private final ApiConnector apiConnector;

  /**
   * Constructs a Time Series Data api endpoint with the help of an {@link ApiConnector} and a {@link JsonParser}
   *
   * @param apiConnector the connection to the api
   */
  public TimeSeries(ApiConnector apiConnector) {
    this.apiConnector = apiConnector;
  }

  
  
  /**
   * This API returns intraday time series (timestamp, open, high, low, close, volume) of the equity specified, updated realtime.
   *
   * @param symbol the stock symbol to lookup
   * @param interval the interval between two consecutive data points in the time series {@link Interval}
   * @param outputSize the specification of the amount of returned data points {@link OutputSize}
   * @return {@link IntraDay} time series data
   */
  public IntraDay intraDay(String symbol, Interval interval, OutputSize outputSize)  {
    String json = apiConnector.getRequest(new Symbol(symbol), Function.TIME_SERIES_INTRADAY, interval, outputSize);
    return IntraDay.from(interval, json);
  }


}
