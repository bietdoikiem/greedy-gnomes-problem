package com.RMIT.assignment.components;

public class TimeIndicator {
  private long startTime;
  private long endTime;
  private float timeElapsed; // In milliseconds

  public TimeIndicator() {
    startTime = System.currentTimeMillis();
  }

  /**
   * Retrieves total time elapsed
   * 
   * @return Time Elapsed
   */
  public float getTimeElapsed() {
    return timeElapsed;
  }

  /**
   * Stops the indicator and informs time elapsed
   */
  public void stop() {
    endTime = System.currentTimeMillis();
    timeElapsed = (endTime - startTime) / 1000F;
    System.out.println(String.format("Total time elapsed ⌛: %fs", timeElapsed));
  }

}
