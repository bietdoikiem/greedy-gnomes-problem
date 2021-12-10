package com.RMIT.algorithm.utils;

public class MatrixUtils {
  /**
   * Return matrix size in array: 1st element is no. of rows & 2nd element is no.
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
   * Display the matrix in console
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
}
