package com.yuhang.stock.stockservice.models;

import java.time.LocalDateTime;

/**
 * CurrencyExchangeData class used to register the rate of a conversion between to currencies.
 */
public class CurrencyExchangeData {

  private  String fromCurrencyCode;
  private  String fromCurrencyName;
  private  String toCurrencyCode;
  private  String toCurrencyName;
  private  float exchangeRate;
  private  LocalDateTime time;
  private  String timezone;

  public CurrencyExchangeData(String fromCurrencyCode,
                              String fromCurrencyName,
                              String toCurrencyCode,
                              String toCurrencyName,
                              float exchangeRate,
                              LocalDateTime time,
                              String timezone) {
    this.fromCurrencyCode = fromCurrencyCode;
    this.fromCurrencyName = fromCurrencyName;
    this.toCurrencyCode = toCurrencyCode;
    this.toCurrencyName = toCurrencyName;
    this.exchangeRate = exchangeRate;
    this.time = time;
    this.timezone = timezone;
  }

  public CurrencyExchangeData(){}
  
  public String getFromCurrencyCode() {
    return fromCurrencyCode;
  }

  public String getFromCurrencyName() {
    return fromCurrencyName;
  }

  public String getToCurrencyCode() {
    return toCurrencyCode;
  }

  public String getToCurrencyName() {
    return toCurrencyName;
  }

  public float getExchangeRate() {
    return exchangeRate;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public String getTimezone() {
    return timezone;
  }
}
