package com.RMIT.algorithm;

import com.RMIT.algorithm.enums.Direction;
import com.RMIT.algorithm.enums.MatrixSymbol;
import com.RMIT.algorithm.utils.ColorUtils;
import com.RMIT.algorithm.utils.ColorUtils.Color;
import com.RMIT.algorithm.utils.LoadingIndicator;
import com.RMIT.algorithm.utils.MatrixUtils;
import com.RMIT.algorithm.utils.StringUtils;

public class ExhaustiveSearch {

  /* * Declaration of enumerates * */

  /**
   * Optimal Solution class for Greedy Gnome problem
   */
  static class OptimalSolution {
    public int gold;
    public int steps;
    public String[][] matrix;
    public String path;

    /**
     * Raw implementation of solution (no solution matrix visual)
     */
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
   * Exhaustively search for the optimal solution for our friend, Gnome! (Solve
   * the problem only => Slightly higher time efficiency)
   * 
   * @param matrix
   */
  public static OptimalSolution solve(String[][] matrix) {
    OptimalSolution solution = new OptimalSolution();
    StringBuilder pathBuilder = new StringBuilder();
    // Run the problem solver
    System.out.println("Solving the Greedy Gnome Problem...⏳");
    scout(0, 0, matrix, solution, 0, null, pathBuilder);
    return solution;
  }

  /**
   * Scout the matrix to find all possible paths for Mr. Gnome (only path
   * included)
   * 
   * @param x      X Coordinate
   * @param y      Y Coordinate
   * @param matrix Matrix to be scouted
   */
  public static void scout(int x, int y, String[][] matrix, OptimalSolution solution, int currentGold,
      Direction currentDirection, StringBuilder pathBuilder) {
    // Check if current trail of path is NOT SAFE!
    if (!MatrixUtils.isSafe(x, y, matrix)) {
      return;
    }
    // // Increase total steps (exclude step for starting point (0, 0))
    // if (!MatrixUtils.isStart(x, y))
    // currentSteps += 1;
    // Keep track of path and its direction
    if (currentDirection != null) {
      pathBuilder.append(currentDirection.toString());
    }
    // Increase golds (if any) & Mark trail as visited
    if (StringUtils.isNumeric(matrix[x][y]))
      currentGold += StringUtils.parseInt(matrix[x][y]);
    /// Store the current path's state if it maximizes the gold collected
    if (currentGold > solution.gold) {
      solution.gold = currentGold;
      solution.steps = pathBuilder.length();
      solution.path = pathBuilder.toString();
    }
    // Go DOWN
    scout(x + 1, y, matrix, solution, currentGold, Direction.DOWN, pathBuilder);
    // Go RIGHT
    scout(x, y + 1, matrix, solution, currentGold, Direction.RIGHT, pathBuilder);
    // Backtrack visited paths
    if (pathBuilder.length() > 0)
      pathBuilder.deleteCharAt(pathBuilder.length() - 1);
  }

  /**
   * (CLI version) Exhaustively search for the optimal solution for our friend,
   * Gnome! (With
   * visualizations for CLI such as loading bar, matrix path, etc. => Slightly
   * slower time
   * efficiency)
   * 
   * @param matrix
   */
  public static OptimalSolution solveCLI(String[][] matrix) {
    int[] matrixSize = MatrixUtils.getSize(matrix);
    OptimalSolution solution = new OptimalSolution(matrixSize[0], matrixSize[1]);
    String[][] currentMatrix = new String[matrixSize[0]][matrixSize[1]];
    StringBuilder pathBuilder = new StringBuilder();
    // Clone matrixes from the original one
    solution.matrix = MatrixUtils.clone(matrix);
    currentMatrix = MatrixUtils.clone(matrix);
    // Run the problem solver
    System.out.println("Solving the Greedy Gnome Problem...⏳");
    LoadingIndicator indicator = new LoadingIndicator(); // Init indicator
    scoutCLI(0, 0, matrix, solution, 0, null, pathBuilder, currentMatrix);
    indicator.stop();
    return solution;
  }

  /**
   * (CLI version) Scout the matrix to find all possible paths for Mr. Gnome
   * (visualizations of
   * path in matrix included!)
   * 
   * @param x      X Coordinate
   * @param y      Y Coordinate
   * @param matrix Matrix to be scouted
   */
  public static void scoutCLI(int x, int y, String[][] matrix, OptimalSolution solution, int currentGold,
      Direction currentDirection, StringBuilder pathBuilder, String[][] currentMatrix) {
    // Check if current trail of path is NOT SAFE!
    if (!MatrixUtils.isSafe(x, y, matrix)) {
      return;
    }
    // Increase total steps (exclude step for starting point (0, 0))
    // if (!MatrixUtils.isStart(x, y))
    // currentSteps += 1;
    // Keep track of path and its direction
    if (currentDirection != null) {
      pathBuilder.append(currentDirection.toString());
    }
    // Increase golds (if any) & Mark trail as visited
    if (StringUtils.isNumeric(matrix[x][y])) {
      currentGold += StringUtils.parseInt(matrix[x][y]);
      currentMatrix[x][y] = (!MatrixUtils.isStart(x, y))
          ? ColorUtils.colorize(MatrixSymbol.GOLD.toString(), Color.YELLOW)
          : ColorUtils.colorize(MatrixSymbol.GOLD.toString(), Color.YELLOW_BOLD); // Differentiate starting point
    } else {
      currentMatrix[x][y] = (!MatrixUtils.isStart(x, y))
          ? ColorUtils.colorize(MatrixSymbol.VISITED.toString(), Color.YELLOW)
          : ColorUtils.colorize(MatrixSymbol.VISITED.toString(), Color.YELLOW_BOLD); // Differentiate starting point
      ;
    }
    /// Store the current path's state if it maximizes the gold collected
    if (currentGold > solution.gold) {
      solution.gold = currentGold;
      solution.steps = pathBuilder.length();
      solution.path = pathBuilder.toString();
      solution.matrix = MatrixUtils.clone(currentMatrix);
    }
    // Go DOWN
    scoutCLI(x + 1, y, matrix, solution, currentGold, Direction.DOWN, pathBuilder, currentMatrix);
    // Go RIGHT
    scoutCLI(x, y + 1, matrix, solution, currentGold, Direction.RIGHT, pathBuilder, currentMatrix);
    // Backtrack visited paths
    if (pathBuilder.length() > 0)
      pathBuilder.deleteCharAt(pathBuilder.length() - 1);
    currentMatrix[x][y] = MatrixSymbol.UNVISITED.toString();
  }

}
