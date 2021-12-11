package com.RMIT.algorithm.utils;

import java.io.File;
import java.util.Scanner;

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
      // Init matrix 2D Array
      String[][] matrix = new String[StringUtils.parseInt(sizeStr[0])][StringUtils.parseInt(sizeStr[1])];
      int currentRow = 0;
      // Read the remaining lines for matrix data
      while (sc.hasNextLine()) {
        String data = sc.nextLine();
        String[] tokens = data.split(" ");
        // Fill the matrix
        for (int i = 0; i < tokens.length; i++) {
          matrix[currentRow][i] = tokens[i];
        }
        currentRow++;
      }
      // Close resource
      sc.close();
      return matrix;
    } catch (Exception e) {
      System.out.println("ERROR❗ Cannot read file due to unexpected error.");
      e.printStackTrace();
    }
    return null;
  }
}
