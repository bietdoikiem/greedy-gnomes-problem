package com.RMIT.algorithm;

import com.RMIT.algorithm.utils.MatrixUtils;

public class MemoizationSearch {

  /**
   * Class for the optimal solution of the problem
   */
  public static class OptimalSolution {
    public int gold;
    public int steps;
    public String[][] matrix;
    public String path;

    public OptimalSolution() {
    }

    /**
     * Construct an optimal solution data type, including optimal matrix, gold and
     * steps.
     * 
     * @param rows
     * @param cols
     */
    public OptimalSolution(int rows, int cols) {
      matrix = new String[rows][cols];
    }
  }

  /**
   * Solve the matrix using Memoization Search
   * 
   * @param matrix Matrix to be solved
   * @return Optimal solution
   */
  public static OptimalSolution solve(String[][] matrix) {
    return null;
  }

  public static void scout(int x, int y, String[][] matrix) {
    /**
     * - Success Flow: We need memoize calculation of every function call in stack
     * (ref: top-down Fibonacci)
     * - Steps:
     * 1. Store repeated steps (DOWN, RIGHT) to avoid recompute over and over
     * 2. PostOrder traversal for backtracking
     * 3. Each node will remember its best path, in which it collects the most golds
     * with the minimal steps required
     * 
     */
    // Check if current trail of path is NOT SAFE!
    if (MatrixUtils.isSafe(x, y, matrix)) {
      return;
    }

  }
}
