package com.RMIT.algorithm;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class DPTopDown {
  // Number of rows, columns and steps
  static int numberRows = 0, numberColumns = 0, steps = 0, currentFoundGold = 0, totalGold = 0;
  static ArrayList<Character> path = new ArrayList<Character>();

  // Variable temp_max_sum is used to store maximum sum till current position
  // static int tempMaxGold[][] = new int[27][27];

  // Variable visited is used to keep track of all the visited positions
  // static int visitedPos[][] = new int[27][27];

  // Variable path_arr is used to store the maximum path
  // static int pathMap[][] = new int[27][27];

  public static class VisitedSign {
    public int tempGold;
    public boolean isVisited;

    public VisitedSign(int tempGold, Boolean isVisited) {
      this.tempGold = tempGold;
      this.isVisited = isVisited;
    }

    @Override
    public String toString() {
      return String.format("%d%s", tempGold, isVisited);
    }
  }

  // For storing current sum
  // static int ;

  // For continuous update of maximum sum required
  // static int ;

  static int[][] readInputFile(String fileName) throws FileNotFoundException {
    Scanner myReader = new Scanner(new File(fileName));
    numberRows = Integer.parseInt(myReader.next());
    numberColumns = Integer.parseInt(myReader.nextLine().strip());
    int i = 0, j = 0;
    int[][] inputArray = new int[numberRows][numberColumns];
    while (myReader.hasNextLine()) {
      if (j == numberColumns) {
        j = 0;
        i++;
      }
      String data = myReader.next();
      if (data.charAt(0) == 'x')
        inputArray[i][j] = -1;
      else if (data.charAt(0) == '.')
        inputArray[i][j] = 0;
      else
        inputArray[i][j] = Integer.parseInt(data);

      j++;
    }
    myReader.close();
    return inputArray;
  }

  static int findPath(int row, int column, VisitedSign[][] sign) {
    // Base case
    if (sign[row + 1][column].tempGold == 0 && sign[row][column + 1].tempGold == 0)
      return 1;
    if (sign[row + 1][column].tempGold >= sign[row][column + 1].tempGold) {
      // pathMap[row + 1][column] = 1;
      path.add('D');
      steps++;
      findPath(row + 1, column, sign);
    } else {
      // pathMap[row][column + 1] = 1;
      path.add('R');
      steps++;
      findPath(row, column + 1, sign);
    }
    return steps;
  }

  // Function to calculate maximum sum of path
  static int findMaximumGold(int row, int column, int[][] inputMatrix, VisitedSign[][] sign) {
    // Checking boundary condition
    if (row == numberRows - 1 && column == numberColumns - 1 && inputMatrix[row][column] != -1)
      return inputMatrix[row][column];

    // Checking whether or not a(row, column) is visited
    if (sign[row][column] != null && inputMatrix[row][column] != -1)
      return sign[row][column].tempGold;

    // Marking a(row, column) is visited
    sign[row][column] = new VisitedSign(0, true);

    int total_sum = 0;

    // Checking whether the position hasn't visited the last row or the last column.
    // Making recursive call
    // for all the possible moves from the current cell and then adding the maximum
    // returned by
    // the calls and updating it.
    if (row < numberRows - 1 && column < numberColumns - 1 && inputMatrix[row][column] != -1) {
      int current_sum = Math.max(
          findMaximumGold(row, column + 1, inputMatrix, sign),
          findMaximumGold(row + 1, column, inputMatrix, sign));
      total_sum = inputMatrix[row][column] + current_sum;

    }

    // Checking whether position
    // has reached last row
    else if (row == numberRows - 1 & inputMatrix[row][column] != -1) {
      total_sum = inputMatrix[row][column] + findMaximumGold(row, column + 1, inputMatrix, sign);
    }

    // If the position is in the last column
    else if (inputMatrix[row][column] != -1) {
      total_sum = inputMatrix[row][column] + findMaximumGold(row + 1, column, inputMatrix, sign);
    }

    // Updating the maximum sum till the current position in the temp_max_sum
    sign[row][column].tempGold = total_sum;

    // Returning the updated maximum value
    return total_sum;
  }

  // Driver Code
  public static Solution solve(String inputFile) throws FileNotFoundException {

    // try {

    // long startTime = System.currentTimeMillis();
    // Calling the implemented function
    // int result = maximum_sum_path(0, 0, a);
    // System.out.println("Optimal Gold 🪙 : " + result);
    // System.out.println("Optimal Steps 👣 : " + find_path(0, 0));
    // System.out.println("Optimal Path 🧩 : " +
    // path.toString().replaceAll("\\[|\\]|,", "").replace("\s", ""));

    // print_array(path_arr);
    // } catch (FileNotFoundException e) {
    // e.printStackTrace();
    // }
    int[][] a = readInputFile(inputFile);
    // System.out.println("rows is " + numberRows + ", columns is " +
    // numberColumns);
    VisitedSign[][] mySign = new VisitedSign[numberRows][numberColumns];
    // for (int i = 0; i < mySign.length; i++) {
    // for (int j = 0; j < i; j++) {
    // System.out.println(mySign[i][j]);
    // }

    // }
    Solution solution = new Solution(findMaximumGold(0, 0, a, mySign),
        findPath(0, 0, mySign), path.toString().replaceAll("\\[|\\]|,", "").replace("\s", ""));

    // System.out.println("Optimal Gold 🪙 : " + solution.getGold());
    // System.out.println("Optimal Steps 👣 : " + solution.getSteps());
    // System.out
    // .println(String.format("DONE! Time elapsed: %fs", (System.currentTimeMillis()
    // - startTime) / 1000F));
    return solution;

  }
}
