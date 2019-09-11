package org.yuhangz.input.technicalindicators;

import org.yuhangz.input.ApiParameter;

public class Acceleration implements ApiParameter {
  private final String acceleration;

  private Acceleration(String acceleration) {
    this.acceleration = acceleration;
  }

  public static Acceleration of(float acceleration) {
    assert acceleration > 0.0;
    return new Acceleration(String.format("%.2f", acceleration));
  }

  @Override
  public String getKey() {
    return "acceleration";
  }

  @Override
  public String getValue() {
    return acceleration;
  }
}

