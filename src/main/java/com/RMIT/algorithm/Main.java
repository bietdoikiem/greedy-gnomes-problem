package com.RMIT.algorithm;

import com.RMIT.algorithm.utils.MatrixUtils;

public class Main {
  public static void main(String[] args) {
    ExhaustiveSearch.OptimalSolution solution = ExhaustiveSearch.solveCLI(MatrixUtils.parseFromFile(args[0]));
    System.out.println("Optimal Gold 🪙 : " + solution.golds);
    System.out.println("Optimal Steps 👣 : " + solution.steps);
    System.out.println("Optimal Path 🧩 : " + solution.path);
    MatrixUtils.display(solution.matrix);
  }
}
