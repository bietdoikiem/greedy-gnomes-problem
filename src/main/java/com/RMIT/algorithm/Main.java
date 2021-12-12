package com.RMIT.algorithm;

import java.io.FileNotFoundException;

import com.RMIT.algorithm.utils.MatrixUtils;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    /* * Minh/Exhaustive Search * */
    ExhaustiveSearch.OptimalSolution solution = ExhaustiveSearch.solveCLI(MatrixUtils.parseFromFile(args[0]));
    System.out.println("Optimal Gold 🪙 : " + solution.gold);
    System.out.println("Optimal Steps 👣 : " + solution.steps);
    System.out.println("Optimal Path 🧩 : " + solution.path);
    MatrixUtils.display(solution.matrix);
    /* * Dat/Dynamic Guider * */
    // int[][] matrix = DPTopDown
    // .read_file(args[0]);
    // Guider solutionFinder = new Guider(matrix);
    // solutionFinder.buildGuide(true);
    // solutionFinder.printResults();
    /* * Mi/Exhaustive Search * */
    // AltExhaustiveSearch es = new AltExhaustiveSearch();
    // String inputFile = new String(args[0]);
    // String[][] board = es.readFile(inputFile);
    // es.displayBoard(board);
    // es.exhaustiveSearch(board);
  }
}
