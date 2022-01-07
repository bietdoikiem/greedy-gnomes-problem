package com.RMIT.assignment.components;

import com.RMIT.assignment.enums.Direction;

/**
 * Class for storing data of a sign to indicate which direction can we maximize
 * the gold collected
 */
public class Sign {
  public int goldLeft;
  public Direction direction;

  public Sign(int goldLeft, Direction direction) {
    this.goldLeft = goldLeft;
    this.direction = direction;
  }

  @Override
  public String toString() {
    return String.format("%d%s", goldLeft, direction);
  }
}
