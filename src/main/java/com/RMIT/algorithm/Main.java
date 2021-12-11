package com.RMIT.algorithm;

import com.RMIT.algorithm.utils.MatrixUtils;

public class Main {
  public static void main(String[] args) {
    String[][] problemMatrix = MatrixUtils.parseFromFile(args[0]);
    ExhaustiveSearch.solve(problemMatrix);
  }
}
