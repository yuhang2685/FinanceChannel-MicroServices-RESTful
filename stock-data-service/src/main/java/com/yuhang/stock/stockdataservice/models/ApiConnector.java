package com.yuhang.stock.stockdataservice.models;

import com.yuhang.stock.stockdataservice.models.input.ApiParameter;

/**
 * An ApiConnector represents a connection to an api endpoint.
 */
@FunctionalInterface
public interface ApiConnector {

  /**
   * Sends request to the api.
   *
   * @param apiParameters the api parameters (required/optional) to the api call.
   * @return the json response for the given call.
   */
  String getRequest(ApiParameter... apiParameters);
}