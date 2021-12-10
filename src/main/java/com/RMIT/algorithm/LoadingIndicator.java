package com.RMIT.algorithm;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingIndicator {
  private String animation = "⠋⠙⠹⠸⠼⠴⠦⠧⠇⠏";
  private int animationIndex = 0;
  private final Timer timer;

  public LoadingIndicator() {
    timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        animationIndex++;
        render();
      }
    }, 100, 100);
  }

  public void render() {
    char symbol = animation.charAt(animationIndex % animation.length());
    String text = String.format("%s%s%s%s%s%s%s%s%s%s\r", symbol, symbol, symbol, symbol, symbol, symbol,
        symbol,
        symbol, symbol, symbol);
    System.out.print(text);
  }

  public void stop() {
    timer.cancel();
  }

}
