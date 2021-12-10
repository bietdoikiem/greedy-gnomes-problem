package com.RMIT.algorithm;

import java.util.Arrays;

import com.RMIT.algorithm.utils.MatrixUtils;
import com.RMIT.algorithm.utils.StringUtils;

public class ExhaustiveSearch {
  public static void main(String[] args) throws InterruptedException {
    // Define problem Matrix
    // x = 12, y = 23
    String[][] problemMatrix = {
        { ".", "X", ".", ".", "X", ".", ".", ".", ".", ".", ".", ".", ".", ".", "X",
            ".", ".", ".", ".", ".", ".", ".",
            "." },
        { ".", ".", ".", ".", ".", ".", "2", ".", ".", ".", ".", ".", ".", ".", ".",
            ".", ".", ".", ".", ".", ".", ".",
            "." },
        { ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".",
            ".", ".", "6", ".", ".", ".", ".",
            "." },
        { "2", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "3", ".", ".",
            ".", ".", "1", ".", ".", ".", ".",
            "." },
        { ".", "X", "4", ".", ".", ".", ".", ".", ".", ".", ".", "X", ".", "X", ".",
            ".", ".", "1", ".", ".", ".", ".",
            "." },
        { ".", ".", ".", ".", ".", ".", ".", "2", ".", ".", ".", ".", ".", ".", ".",
            "X", ".", "2", ".", ".", ".", ".",
            ".", },
        { ".", ".", ".", "X", ".", ".", ".", ".", ".", ".", "X", ".", ".", ".", ".",
            ".", ".", "2", "X", ".", ".", ".",
            ".", },
        { "2", ".", "2", ".", ".", ".", ".", ".", ".", ".", ".", "X", "4", ".", ".",
            "1", ".", ".", ".", ".", ".", ".",
            ".", },
        { ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".",
            ".", ".", ".", ".", ".", ".", ".",
            ".", },
        { ".", ".", ".", "X", ".", ".", "3", ".", ".", ".", ".", ".", ".", ".", ".",
            ".", "2", ".", ".", ".", ".", ".",
            ".", },
        { "1", ".", ".", "X", ".", ".", ".", ".", ".", ".", "2", ".", ".", ".", ".",
            ".", ".", "3", ".", ".", "X", ".",
            ".", },
        { "2", "X", "X", "X", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".",
            "X", ".", ".", ".", ".", ".", "X",
            "." }
    };
    String[][] simpleMatrix = {
        { ".", "2", "" },
        { ".", "X", "3" },
        { "6", ".", "X" }
    };
    exhaustiveSearch(problemMatrix);

  }

  /* * Declaration of constants * */
  static final String ROCK_SYMBOL = "X";
  static final String TRAIL_GOLD_SYMBOL = "G";
  static final String TRAIL_VISITED_SYMBOL = "+";
  static final String TRAIL_UNVISITED_SYMBOL = ".";

  /**
   * Optimal Solution class for Greedy Gnome problem
   */
  static class OptimalSolution {
    public int golds;
    public int steps;
    public String[][] matrix;

    public OptimalSolution() {
    }

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
  public static void exhaustiveSearch(String[][] matrix) {

    int[] matrixSize = MatrixUtils.getSize(matrix);
    OptimalSolution solution = new OptimalSolution(matrixSize[0], matrixSize[1]);
    // Pre-fill solution array
    Arrays.stream(solution.matrix).forEach(a -> Arrays.fill(a, TRAIL_UNVISITED_SYMBOL));
    System.out.println("Solving the Greedy Gnomes Problem... ⏳");
    // Init indicator
    LoadingIndicator indicator = new LoadingIndicator();
    scout(0, 0, matrix, solution, 0, 0);
    System.out.println("Optimal Gold 🪙: " + solution.golds + " - Optimal Steps 👣: " + solution.steps);
    indicator.stop();
  }

  /**
   * Scout the matrix to find all possible paths for Mr. Gnome
   * 
   * @param x      X Coordinate
   * @param y      Y Coordinate
   * @param matrix Matrix to be scouted
   * @return True if scout-able || False if not
   */
  public static void scout(int x, int y, String[][] matrix, OptimalSolution solution, int currentGold,
      int currentSteps) {
    // Check if current trail of path is NOT SAFE! (Store & Reset)
    if (!isSafe(x, y, matrix)) {
      return;
    }
    // Increase total steps (exclude step for starting point (0, 0))
    if (!(x == 0 && y == 0))
      currentSteps += 1;
    // Increase golds (if any) & Mark trail as visited
    if (StringUtils.isNumeric(matrix[x][y])) {
      currentGold += StringUtils.parseInt(matrix[x][y]);
      solution.matrix[x][y] = TRAIL_GOLD_SYMBOL; // Mark 'G'
    } else {
      solution.matrix[x][y] = TRAIL_VISITED_SYMBOL; // Mark '+'
    }
    /// Store the current path's state if it maximizes the gold collected
    if (currentGold > solution.golds) {
      solution.golds = currentGold;
      solution.steps = currentSteps;
    }
    /* * BEGIN Debug * */
    // System.out.println(
    // "Current Gold 🪙: " + currentGold + " - Current Steps 👣: " + currentSteps);
    // MatrixUtils.display(solution.matrix);
    // System.out.println("----------------------------------------------");
    /* * END Debug * */
    // Go DOWN
    scout(x + 1, y, matrix, solution, currentGold, currentSteps);
    // Go RIGHT
    scout(x, y + 1, matrix, solution, currentGold, currentSteps);

    // Backtrack visited path
    solution.matrix[x][y] = TRAIL_UNVISITED_SYMBOL;
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
