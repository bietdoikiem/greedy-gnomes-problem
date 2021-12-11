package com.RMIT.algorithm;

import java.util.Arrays;

import com.RMIT.algorithm.utils.MatrixUtils;
import com.RMIT.algorithm.utils.StringUtils;

public class ExhaustiveSearch {

  /* * Declaration of constants * */
  public static final String ROCK_SYMBOL = "X";
  public static final String TRAIL_GOLD_SYMBOL = "G";
  public static final String TRAIL_VISITED_SYMBOL = "+";
  public static final String TRAIL_UNVISITED_SYMBOL = ".";

  /**
   * Optimal Solution class for Greedy Gnome problem
   */
  static class OptimalSolution {
    public int golds;
    public int steps;
    public String[][] matrix;

    /**
     * Construct an optimal solution data type, including optimal matrix, gold and
     * steps.
     * 
     * @param rows
     * @param cols
     */
    public OptimalSolution(int rows, int cols) {
      matrix = new String[rows][cols];
      golds = 0;
      steps = 0;
    }
  }

  /**
   * Exhaustively search for the optimal solution for our friend, Gnome!
   * 
   * @param matrix
   */
  public static void solve(String[][] matrix) {

    int[] matrixSize = MatrixUtils.getSize(matrix);
    OptimalSolution solution = new OptimalSolution(matrixSize[0], matrixSize[1]);
    String[][] currentMatrix = new String[matrixSize[0]][matrixSize[1]];
    // Pre-fill matrix with dots
    Arrays.stream(solution.matrix).forEach(a -> Arrays.fill(a, TRAIL_UNVISITED_SYMBOL));
    Arrays.stream(currentMatrix).forEach(a -> Arrays.fill(a, TRAIL_UNVISITED_SYMBOL));
    // Solving the problem
    System.out.println("Solving the Greedy Gnomes Problem... ⏳");
    LoadingIndicator indicator = new LoadingIndicator(); // Init indicator
    scout(0, 0, matrix, solution, 0, 0, currentMatrix);
    System.out.println("Optimal Gold 🪙: " + solution.golds);
    System.out.println("Optimal Steps 👣: " + solution.steps);
    System.out.println("Optimal Path 🧩:");
    MatrixUtils.display(solution.matrix);
    indicator.stop();
  }

  /**
   * Scout the matrix to find all possible paths for Mr. Gnome
   * 
   * @param x      X Coordinate
   * @param y      Y Coordinate
   * @param matrix Matrix to be scouted
   */
  public static void scout(int x, int y, String[][] matrix, OptimalSolution solution, int currentGold,
      int currentSteps, String[][] currentMatrix) {
    // Check if current trail of path is NOT SAFE!
    if (!isSafe(x, y, matrix)) {
      return;
    }
    // Increase total steps (exclude step for starting point (0, 0))
    if (!(x == 0 && y == 0))
      currentSteps += 1;
    // Increase golds (if any) & Mark trail as visited
    if (StringUtils.isNumeric(matrix[x][y])) {
      currentGold += StringUtils.parseInt(matrix[x][y]);
      currentMatrix[x][y] = TRAIL_GOLD_SYMBOL;
    } else {
      currentMatrix[x][y] = TRAIL_VISITED_SYMBOL;
    }
    /// Store the current path's state if it maximizes the gold collected
    if (currentGold > solution.golds) {
      solution.golds = currentGold;
      solution.steps = currentSteps;
      solution.matrix = MatrixUtils.clone(currentMatrix);
    }
    // Go DOWN
    scout(x + 1, y, matrix, solution, currentGold, currentSteps, currentMatrix);
    // Go RIGHT
    scout(x, y + 1, matrix, solution, currentGold, currentSteps, currentMatrix);
    // Backtrack visited path
    currentMatrix[x][y] = TRAIL_UNVISITED_SYMBOL;
  }

  /**
   * Check if the current coordination of the matrix is safe to scout
   * 
   * @param x      X Coordinate
   * @param y      Y Coordinate
   * @param matrix Matrix to be checked
   * @return True if safe || False if not
   */
  private static boolean isSafe(int x, int y, String[][] matrix) {
    int[] matrixSize = MatrixUtils.getSize(matrix);
    return x >= 0 && y >= 0 && x < matrixSize[0] && y < matrixSize[1] && !matrix[x][y].equalsIgnoreCase(ROCK_SYMBOL);
  }

}
