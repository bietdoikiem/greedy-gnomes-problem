package com.RMIT.assignment.enums;

public enum MatrixSymbol {
  ROCK("X"),
  GOLD("G"),
  VISITED("+"),
  UNVISITED(".");

  private final String symbol;

  private MatrixSymbol(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return symbol;
  }
}
