package com.RMIT.algorithm;

public class ExhaustiveSearch {
  public static void main(String[] args) {
    // Define problem Matrix
    // x = 12, y = 23
    char[][] problemMatrix = {
        { '.', 'X', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '.',
            '.' },
        { '.', '.', '.', '.', '.', '.', '2', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.',
            '.' },
        { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '6', '.', '.', '.', '.',
            '.' },
        { '2', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '3', '.', '.', '.', '.', '1', '.', '.', '.', '.',
            '.' },
        { '.', 'X', '4', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', 'X', '.', '.', '.', '1', '.', '.', '.', '.',
            '.' },
        { '.', '.', '.', '.', '.', '.', '.', '2', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '2', '.', '.', '.', '.',
            '.', },
        { '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '2', 'X', '.', '.', '.',
            '.', },
        { '2', '.', '2', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '4', '.', '.', '1', '.', '.', '.', '.', '.', '.',
            '.', },
        { '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.',
            '.', },
        { '.', '.', '.', 'X', '.', '.', '3', '.', '.', '.', '.', '.', '.', '.', '.', '.', '2', '.', '.', '.', '.', '.',
            '.', },
        { '1', '.', '.', 'X', '.', '.', '.', '.', '.', '.', '2', '.', '.', '.', '.', '.', '.', '3', '.', '.', 'X', '.',
            '.', },
        { '2', 'X', 'X', 'X', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'X', '.', '.', '.', '.', '.', 'X',
            '.' }

    };
    displayMatrix(problemMatrix);
  }

  /**
   * Display matrix in console
   * 
   * @param matrix Array of characters to be displayed
   */
  public static void displayMatrix(char[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.print("\n");
    }
  }
}
