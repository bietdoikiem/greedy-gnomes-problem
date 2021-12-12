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
    public int golds;
    public int steps;
    public String[][] matrix;
    public String path;

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
  public static OptimalSolution solve(String[][] matrix, boolean hasVisual) {

    int[] matrixSize = MatrixUtils.getSize(matrix);
    OptimalSolution solution = new OptimalSolution(matrixSize[0], matrixSize[1]);
    String[][] currentMatrix = new String[matrixSize[0]][matrixSize[1]];
    StringBuilder pathBuilder = new StringBuilder();
    // Clone matrixes from the original one
    solution.matrix = MatrixUtils.clone(matrix);
    currentMatrix = MatrixUtils.clone(matrix);
    // Run the problem solver
    System.out.println("Solving the Greedy Gnomes Problem... ⏳");
    LoadingIndicator indicator = new LoadingIndicator(); // Init indicator
    if (hasVisual == true) {
      visualScout(0, 0, matrix, solution, 0, 0, null, pathBuilder, currentMatrix);
    } else {
      scout(0, 0, matrix, solution, 0, 0, null, pathBuilder);
    }

    indicator.stop();
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
      int currentSteps, Direction currentDirection, StringBuilder pathBuilder) {
    // Check if current trail of path is NOT SAFE!
    if (!isSafe(x, y, matrix)) {
      return;
    }
    // Increase total steps (exclude step for starting point (0, 0))
    if (!isStart(x, y))
      currentSteps += 1;
    // Keep track of path and its direction
    if (currentDirection != null) {
      pathBuilder.append(currentDirection.toString());
    }
    // Increase golds (if any) & Mark trail as visited
    if (StringUtils.isNumeric(matrix[x][y]))
      currentGold += StringUtils.parseInt(matrix[x][y]);
    /// Store the current path's state if it maximizes the gold collected
    if (currentGold > solution.golds) {
      solution.golds = currentGold;
      solution.steps = currentSteps;
      solution.path = pathBuilder.toString();
    }
    // Go DOWN
    scout(x + 1, y, matrix, solution, currentGold, currentSteps, Direction.DOWN, pathBuilder);
    // Go RIGHT
    scout(x, y + 1, matrix, solution, currentGold, currentSteps, Direction.RIGHT, pathBuilder);
    // Backtrack visited paths
    if (pathBuilder.length() > 0)
      pathBuilder.deleteCharAt(pathBuilder.length() - 1);
  }

  /**
   * Scout the matrix to find all possible paths for Mr. Gnome (visualizations of
   * path in matrix included!)
   * 
   * @param x      X Coordinate
   * @param y      Y Coordinate
   * @param matrix Matrix to be scouted
   */
  public static void visualScout(int x, int y, String[][] matrix, OptimalSolution solution, int currentGold,
      int currentSteps, Direction currentDirection, StringBuilder pathBuilder, String[][] currentMatrix) {
    // Check if current trail of path is NOT SAFE!
    if (!isSafe(x, y, matrix)) {
      return;
    }
    // Increase total steps (exclude step for starting point (0, 0))
    if (!isStart(x, y))
      currentSteps += 1;
    // Keep track of path and its direction
    if (currentDirection != null) {
      pathBuilder.append(currentDirection.toString());
    }
    // Increase golds (if any) & Mark trail as visited
    if (StringUtils.isNumeric(matrix[x][y])) {
      currentGold += StringUtils.parseInt(matrix[x][y]);
      currentMatrix[x][y] = (!isStart(x, y)) ? ColorUtils.colorize(MatrixSymbol.GOLD.toString(), Color.YELLOW)
          : ColorUtils.colorize(MatrixSymbol.GOLD.toString(), Color.YELLOW_BOLD); // Differentiate starting point
    } else {
      currentMatrix[x][y] = (!isStart(x, y)) ? ColorUtils.colorize(MatrixSymbol.VISITED.toString(), Color.YELLOW)
          : ColorUtils.colorize(MatrixSymbol.VISITED.toString(), Color.YELLOW_BOLD); // Differentiate starting point
      ;
    }
    /// Store the current path's state if it maximizes the gold collected
    if (currentGold > solution.golds) {
      solution.golds = currentGold;
      solution.steps = currentSteps;
      solution.path = pathBuilder.toString();
      solution.matrix = MatrixUtils.clone(currentMatrix);
    }
    // Go DOWN
    visualScout(x + 1, y, matrix, solution, currentGold, currentSteps, Direction.DOWN, pathBuilder, currentMatrix);
    // Go RIGHT
    visualScout(x, y + 1, matrix, solution, currentGold, currentSteps, Direction.RIGHT, pathBuilder, currentMatrix);
    // Backtrack visited paths
    if (pathBuilder.length() > 0)
      pathBuilder.deleteCharAt(pathBuilder.length() - 1);
    currentMatrix[x][y] = MatrixSymbol.UNVISITED.toString();
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
    return x >= 0 && y >= 0 && x < matrixSize[0] && y < matrixSize[1]
        && !matrix[x][y].equalsIgnoreCase(MatrixSymbol.ROCK.toString());
  }

  private static boolean isStart(int x, int y) {
    return x == 0 && y == 0;
  }

}
