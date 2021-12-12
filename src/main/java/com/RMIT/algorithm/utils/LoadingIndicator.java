package com.RMIT.algorithm.utils;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingIndicator {
  private String animation = "⠋⠙⠹⠸⠼⠴⠦⠧⠇⠏";
  private int animationIndex = 0;
  private long startTime;
  private long endTime;
  private float timeElapsed; // In milliseconds
  private float timeElapsedUI = 0; // In Second
  private final Timer timer;

  public LoadingIndicator() {
    timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        animationIndex++;
        timeElapsedUI += 0.1;
        render();
      }
    }, 100, 100);
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
   * Renders the indicator
   */
  public void render() {
    char symbol = animation.charAt(animationIndex % animation.length());
    String text = String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s %.1fs \r", symbol, symbol, symbol, symbol, symbol, symbol,
        symbol,
        symbol, symbol, symbol, symbol, symbol, symbol, symbol, timeElapsedUI);
    System.out.print(text);
  }

  /**
   * Stops the indicator and informs time elapsed
   */
  public void stop() {
    endTime = System.currentTimeMillis();
    timeElapsed = (endTime - startTime) / 1000F;
    System.out.println(String.format("Total time elapsed ⌛: %fs", timeElapsed));
    timer.cancel();
  }

}
