package org.yuhangz.input.timeseries;

import org.yuhangz.input.ApiParameter;

/**
 * Output size parameter for the time series api call.
 */
public enum OutputSize implements ApiParameter {
  COMPACT("compact"),
  FULL("full");

  private final String outputSize;

  OutputSize(String outputSize) {
    this.outputSize = outputSize;
  }

  @Override
  public String getKey() {
    return "outputsize";
  }

  @Override
  public String getValue() {
    return outputSize;
  }
}
