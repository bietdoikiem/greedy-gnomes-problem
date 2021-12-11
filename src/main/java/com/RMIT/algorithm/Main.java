package com.RMIT.algorithm;

import com.RMIT.algorithm.utils.MatrixUtils;

public class Main {
  public static void main(String[] args) {
    ExhaustiveSearch.solve(MatrixUtils.parseFromFile(args[0]));
  }
}
