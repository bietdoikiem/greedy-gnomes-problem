package com.RMIT.algorithm;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingIndicator {
  private String animation = "⠋⠙⠹⠸⠼⠴⠦⠧⠇⠏";
  private int animationIndex = 0;
  private float timeElapsed = 0; // In Second
  private final Timer timer;

  public LoadingIndicator() {
    timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        animationIndex++;
        timeElapsed += 0.1;
        render();
      }
    }, 100, 100);
  }

  /**
   * Renders the indicator
   */
  public void render() {
    char symbol = animation.charAt(animationIndex % animation.length());
    String text = String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s %.1fs \r", symbol, symbol, symbol, symbol, symbol, symbol,
        symbol,
        symbol, symbol, symbol, symbol, symbol, symbol, symbol, timeElapsed);
    System.out.print(text);
  }

  /**
   * Stops the indicator and informs time elapsed
   */
  public void stop() {
    System.out.println(String.format("Total time elapsed ⌛: %.1fs", timeElapsed));
    timer.cancel();
  }

}
