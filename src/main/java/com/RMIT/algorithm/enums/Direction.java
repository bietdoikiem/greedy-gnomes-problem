package com.RMIT.algorithm.enums;

public enum Direction {
  DOWN("D"),
  RIGHT("R");

  private final String direction;

  private Direction(String direction) {
    this.direction = direction;
  }

  @Override
  public String toString() {
    return direction;
  }
}
