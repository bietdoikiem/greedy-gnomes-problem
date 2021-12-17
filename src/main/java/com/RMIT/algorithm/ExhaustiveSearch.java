package com.RMIT.algorithm;

import com.RMIT.algorithm.enums.Direction;
import com.RMIT.algorithm.enums.MatrixSymbol;
import com.RMIT.algorithm.utils.ColorUtils;
import com.RMIT.algorithm.utils.ColorUtils.Color;
import com.RMIT.algorithm.utils.LoadingIndicator;
import com.RMIT.algorithm.utils.MatrixUtils;
import com.RMIT.algorithm.utils.StringUtils;

/**
 * Search for every possible path of the matrix to find the one that earns the
 * maximum gold
 */
public class ExhaustiveSearch {

  /**
   * Exhaustively search for the optimal solution for our friend, Gnome! (Solve
   * the problem only => Slightly higher time efficiency)
   * 
   * @param matrix
   */
  public static Solution solve(String[][] matrix) {
    Solution solution = new Solution();
    StringBuilder pathBuilder = new StringBuilder();
    // Run the problem solver
    System.out.println("Solving the Greedy Gnomes Problem...⏳");
    scout(0, 0, matrix, solution, 0, null, pathBuilder);
    return solution;
  }

  /**
   * Scout the matrix to find all possible paths for Mr. Gnome (only path
   * included)
   * 
   * @param x                X coordinate
   * @param y                Y coordinate
   * @param matrix           Problem Matrix
   * @param solution         Optimal Solution
   * @param currentGold      Current Collected Gold
   * @param currentDirection Current Scouting Direction
   * @param pathBuilder      Current Path String (builder)
   */
  public static void scout(int x, int y, String[][] matrix, Solution solution, int currentGold,
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
    if (currentGold > solution.getGold()) {
      solution.setGold(currentGold);
      solution.setSteps(pathBuilder.length());
      solution.setPath(pathBuilder.toString());
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
  public static Solution solveCLI(String[][] matrix) {
    int[] matrixSize = MatrixUtils.getSize(matrix);
    Solution solution = new Solution(matrixSize[0], matrixSize[1]);
    StringBuilder pathBuilder = new StringBuilder();
    // Clone solution matrix from the original one
    solution.setMatrix(MatrixUtils.clone(matrix));
    // Run the problem solver
    System.out.println("Solving the Greedy Gnomes Problem...⏳");
    LoadingIndicator indicator = new LoadingIndicator(); // Init indicator
    scoutCLI(0, 0, matrix, solution, 0, null, pathBuilder, matrix);
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
  public static void scoutCLI(int x, int y, String[][] matrix, Solution solution, int currentGold,
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
    if (currentGold > solution.getGold()) {
      solution.setGold(currentGold);
      solution.setSteps(pathBuilder.length());
      solution.setPath(pathBuilder.toString());
      solution.setMatrix(MatrixUtils.clone(currentMatrix));
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
