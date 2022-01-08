package com.RMIT.assignment.algorithms;

import com.RMIT.assignment.components.Sign;
import com.RMIT.assignment.components.Solution;
import com.RMIT.assignment.enums.Direction;
import com.RMIT.assignment.utils.MatrixUtils;

/**
 * Search the optimal path to mine maximum amount of gold with Dynamic
 * Programing integration
 */
public class MemoizationSearch {

  /**
   * Solve the matrix using Memoization Search
   * 
   * @param matrix Matrix to be solved
   * @return Optimal solution
   */
  public static Solution solve(int[][] matrix) {
    // Retrieve matrix's no. of rows & columns
    int[] matrixSize = MatrixUtils.getSize(matrix);
    // Init matrix of direction signs
    Sign[][] signs = new Sign[matrixSize[0]][matrixSize[1]];
    // Init path builder
    StringBuilder path = new StringBuilder();
    // Run the problem solver
    System.out.println("Solving...⏳");
    int mostGold = scout(0, 0, null, matrix, signs);
    // Construct optimal path from matrix of signs
    buildPath(0, 0, signs, path);
    // Construct solution
    Solution solution = new Solution(mostGold, path.length(), path.toString());
    return solution;
  }

  /**
   * Scout for the optimal paths in each turn of the matrix (Sounds cool!!!)
   * 
   * @param x         X Coordinate
   * @param y         Y Coordinate
   * @param direction Direction
   * @param matrix    Matrix of the problem
   * @param signs     Signs to show where the most amount of gold can be collected
   * @return Maximum golds to be collected
   */
  public static int scout(int x, int y, Direction direction,
      int[][] matrix, Sign[][] signs) {
    // Check if current trail of path is NOT SAFE to scout!
    if (!MatrixUtils.isSafe(x, y, matrix)) {
      return 0;
    }

    // Check if best path has been explored and sign was created beforehand
    if (signs[x][y] != null) {
      return signs[x][y].goldLeft;
    }
    // Check which cache position (DOWN or RIGHT) earns higher gold
    int downGold = scout(x + 1, y, Direction.DOWN, matrix, signs);
    int rightGold = scout(x, y + 1, Direction.RIGHT, matrix, signs);

    // Check & Acquire GOLD (if any)
    int currentGold = matrix[x][y];
    // Initialize value of total gold
    int totalGold = currentGold;

    // Compare and retrieve the maximum gold that can be collected between two
    // directions
    if (downGold > rightGold) {
      // Calculate total gold earn in direction with higher value (DOWN)
      totalGold += downGold;
      // Store calculated gold
      signs[x][y] = new Sign(totalGold, Direction.DOWN);
      return totalGold;
    } else if (rightGold > downGold) {
      // Calculate total gold earn in direction with higher value (RIGHT)

      totalGold += rightGold;
      // Store calculated gold
      signs[x][y] = new Sign(totalGold, Direction.RIGHT);
      return totalGold;
    }
    // If EQUAL, calculate total gold with DOWN or RIGHT (optional) direction
    // Here we choose DOWN as an option
    totalGold += downGold;
    // Skip initializing sign of the direction with zero gold
    if (totalGold == 0) {
      return 0;
    }
    // Skip initializing sign if there are no other directions to mine any more gold
    if (downGold == 0 && rightGold == 0) {
      signs[x][y] = new Sign(totalGold, null);
      return totalGold;
    }
    // Initialize sign with chosen DOWN option
    signs[x][y] = new Sign(totalGold, Direction.DOWN);
    return totalGold;
  }

  /**
   * Build the optimal path regarding the matrix of signs
   * 
   * @param x     X coordinate
   * @param y     Y coordinate
   * @param signs Matrix of signs
   * @param path  Optimal path to earn the most amount of gold
   */
  public static void buildPath(int x, int y, Sign[][] signs, StringBuilder path) {
    // Check if it is dead-end
    if (signs[x][y] == null) {
      return;
    }
    // Check if there are no directions left to go (last gold has been mined)
    if (signs[x][y].direction == null) {
      return;
    }
    // Gradually build the optimal path according to the direction of the sign
    path.append(signs[x][y].direction.toString());
    // Traverse to appropriate direction that the signs indicate
    if (signs[x][y].direction == Direction.DOWN) {
      buildPath(x + 1, y, signs, path);
    } else if (signs[x][y].direction == Direction.RIGHT) {
      buildPath(x, y + 1, signs, path);
    }
  }

}
