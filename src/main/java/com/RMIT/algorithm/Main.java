package com.RMIT.algorithm;

import java.io.FileNotFoundException;

import com.RMIT.algorithm.utils.MatrixUtils;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    // src/main/resources/sample-12x23.txt
    /* * Minh/Exhaustive Search * */
    // Solution solution =
    // ExhaustiveSearch.solveCLI(MatrixUtils.parseFromFile(args[0]));
    /* * Minh/Memoization Search * */
    Solution solution = MemoizationSearch.solveCLI(MatrixUtils.parseFromFile(args[0]));
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
    System.out.println("Optimal Gold 🪙 : " + solution.getGold());
    System.out.println("Optimal Steps 👣 : " + solution.getSteps());
    System.out.println("Optimal Path 🧩 : " + solution.getPath());
    MatrixUtils.display(solution.getMatrix()); // (If has solution matrix)
  }

}
