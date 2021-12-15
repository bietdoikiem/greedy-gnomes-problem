package com.RMIT.algorithm.utils;

import java.io.File;
import java.util.Scanner;

import com.RMIT.algorithm.configs.Configs;
import com.RMIT.algorithm.enums.MatrixSymbol;

public class MatrixUtils {

  /**
   * Returns matrix size in array: 1st element is no. of rows & 2nd element is no.
   * columns
   * 
   * @param matrix Matrix of characters
   * @return Array of matrix size with 2 elements: rows, columns
   */
  public static int[] getSize(String[][] matrix) {
    int[] size = new int[2];
    size[0] = matrix.length;
    size[1] = matrix[0].length;
    return size;
  }

  /**
   * Displays the matrix in console
   * 
   * @param matrix 2D Array of characters to be displayed
   */
  public static void display(String[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.print("\n");
    }
  }

  /**
   * Clones the provided matrix
   * 
   * @param src Source matrix
   * @return A new clone of the provided matrix
   */
  public static String[][] clone(String[][] src) {
    int length = src.length;
    String[][] target = new String[length][src[0].length];
    for (int i = 0; i < length; i++) {
      System.arraycopy(src[i], 0, target[i], 0, src[i].length);
    }
    return target;
  }

  /**
   * Fills the matrix with content whose data type is String
   * 
   * @param matrix  Matrix to be filled
   * @param content Content to fill in
   */
  public static void fill(String[][] matrix, String content) {
    if (matrix == null) {
      throw new Error("MATRIX ERROR! Matrix is null");
    }
    int[] matrixSize = MatrixUtils.getSize(matrix);
    for (int i = 0; i < matrixSize[0]; i++) {
      for (int j = 0; j < matrixSize[1]; j++) {
        matrix[i][j] = content;
      }
    }
  }

  /**
   * Parses matrix (2D array) from file's path
   * 
   * @param path File's path
   * @return String Matrix
   */
  public static String[][] parseFromFile(String path) {
    try {
      File file = new File(path);
      Scanner sc = new Scanner(file);
      // Read rows & columns in the 1st line of file
      String sizeData = sc.nextLine();
      String[] sizeStr = sizeData.split(" ");
      // Validate acceptable rows & columns
      int rows = StringUtils.parseInt(sizeStr[0]);
      int cols = StringUtils.parseInt(sizeStr[1]);
      if (rows > Configs.getMaxRows() || cols > Configs.getMaxCols()) {
        sc.close();
        throw new Error("MATRIX ERROR! Given matrix has exceeded the maximum allowance size (27, 27)");
      }
      // Init matrix 2D Array
      String[][] matrix = new String[rows][cols];
      int currentRow = 0;
      // Read the remaining lines for matrix data
      while (sc.hasNextLine()) {
        String data = sc.nextLine();
        String[] tokens = data.split(" ");
        // Fill the matrix
        for (int i = 0; i < tokens.length; i++) {
          if (!isValidMatrixSymbol(tokens[i])) {
            sc.close();
            throw new Error("MATRIX ERROR! Matrix contains illegal symbol " + "\"" + tokens[i] + "\"");
          }
          matrix[currentRow][i] = tokens[i];
        }
        currentRow++;
      }
      sc.close();
      return matrix;
    } catch (Exception e) {
      System.out.println(String.format("MATRIX ERROR! Cannot read file %s", e.getMessage()));
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Check if the matrix contain valid symbols
   * 
   * @param symbol
   * @return True if valid || False if not
   */
  public static boolean isValidMatrixSymbol(String symbol) {
    return symbol.equalsIgnoreCase(MatrixSymbol.GOLD.toString())
        || symbol.equalsIgnoreCase(MatrixSymbol.ROCK.toString())
        || symbol.equalsIgnoreCase(MatrixSymbol.UNVISITED.toString())
        || symbol.equalsIgnoreCase(MatrixSymbol.VISITED.toString())
        || StringUtils.isNumeric(symbol);
  }

  /**
   * Check if the current coordination of the matrix is safe to scout
   * 
   * @param x      X Coordinate
   * @param y      Y Coordinate
   * @param matrix Matrix to be checked
   * @return True if safe || False if not
   */
  public static boolean isSafe(int x, int y, String[][] matrix) {
    int[] matrixSize = MatrixUtils.getSize(matrix);
    return x >= 0 && y >= 0 && x < matrixSize[0] && y < matrixSize[1]
        && !matrix[x][y].equalsIgnoreCase(MatrixSymbol.ROCK.toString());
  }

  /**
   * Check if it's the starting point of the Matrix (0, 0)
   * 
   * @param x X Coordinate
   * @param y Y Coordinate
   * @return True if at the start || False if not
   */
  public static boolean isStart(int x, int y) {
    return x == 0 && y == 0;
  }
}
