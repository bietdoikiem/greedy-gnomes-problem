package com.RMIT.assignment.algorithms;

import com.RMIT.assignment.components.Solution;
import com.RMIT.assignment.components.TimeIndicator;
import com.RMIT.assignment.enums.Direction;
import com.RMIT.assignment.utils.MatrixUtils;

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
  public static Solution solve(int[][] matrix) {
    TimeIndicator indicator = new TimeIndicator(); // Init indicator
    Solution solution = new Solution();
    StringBuilder pathBuilder = new StringBuilder();
    // Run the problem solver
    System.out.println("Solving...⏳");
    scout(0, 0, matrix, solution, 0, null, pathBuilder);
    indicator.stop(); // Stop indicator upon finishing
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
  public static void scout(int x, int y, int[][] matrix, Solution solution, int currentGold,
      Direction currentDirection, StringBuilder pathBuilder) {
    // Check if current trail of path is NOT SAFE!
    if (!MatrixUtils.isSafe(x, y, matrix)) {
      return;
    }
    // Keep track of path and its direction
    if (currentDirection != null) {
      pathBuilder.append(currentDirection.toString());
    }
    // Increase golds (if any)
    if (matrix[x][y] > 0) {
      currentGold += matrix[x][y];
    }
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

}
