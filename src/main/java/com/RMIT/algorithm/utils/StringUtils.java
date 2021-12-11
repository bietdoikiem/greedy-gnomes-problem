package com.RMIT.algorithm.utils;

import java.util.Arrays;

public class StringUtils {

  /**
   * Gets Number from string
   * 
   * @param str String of integer
   * @return Integer number || Error if failed!
   */
  public static int parseInt(String str) {
    if (str == null) {
      throw new Error("ERROR❗ String is null");
    }
    int number = Integer.parseInt(str);
    return number;
  }

  /**
   * Checks if the current String represents a number
   * 
   * @param character Character to be checked
   * @return True if valid || False if not
   */
  public static boolean isNumeric(String str) {
    if (str == null) {
      return false;
    }
    try {
      Integer.parseInt(str);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  public static String repeat(char c, int count) {
    char[] arr = new char[count];
    Arrays.fill(arr, c);
    return new String(arr);
  }

}
