package com.RMIT.algorithm;

import java.io.FileNotFoundException;

import com.RMIT.algorithm.utils.MenuUtils;

public class Main {
  static Solution mySolution;

  public static void main(String[] args) throws FileNotFoundException {

    // src/main/resources/sample-12x23.txt
    //

    mySolution = DPTopDown.solve("src/main/resources/sample-12x23.txt");
    System.out.println("Optimal Gold 🪙 : " + mySolution.getGold());
    System.out.println("Optimal Steps 👣 : " + mySolution.getSteps());
  }
}
