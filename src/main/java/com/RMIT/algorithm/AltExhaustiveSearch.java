package com.RMIT.algorithm;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class AltExhaustiveSearch {
  String[][] board;
  int rows;
  int cols;

  public String[][] readFile(String filename) throws FileNotFoundException {
    File inputFile = new File(filename);
    Scanner sc = new Scanner(inputFile);
    rows = sc.nextInt();
    cols = sc.nextInt();
    board = new String[rows][cols];
    int curRow = 0;
    int curCol = 0;
    while (sc.hasNextLine()) {
      if (curCol == cols) {
        curCol = 0;
        curRow++;
      }
      String data = sc.next();
      if (data.charAt(0) == '.') {
        board[curRow][curCol] = ".";
      } else if (data.charAt(0) == 'x') {
        board[curRow][curCol] = "x";
      } else {
        board[curRow][curCol] = String.valueOf(data);
      }
      curCol++;
    }
    sc.close();
    return board;
  }

  public void displayBoard(String[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }

  private boolean isPath(String[][] board, int m, int n) {
    boolean isValidPath = false;
    if (m >= 0 && n >= 0 && m < board.length && n < board[0].length) {
      if (board[m][n] != "x" && board[m][n] != "X") {
        isValidPath = true;
      }
    }
    return isValidPath;
  }

  private boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public void findPath(String[][] board, int i, int j, int curScore, int curStep, String curMove, StringBuffer curPath, BruteForceSolution result) {
    // return if it is an invalid path
    if (!isPath(board, i, j)) {
      return;
    }

    if (curMove != null){
      curPath.append(curMove);
    }
    // check whether it is the start position or not
    if (i != 0 || j != 0) {
      curStep += 1;
    }
    if (isNumeric(board[i][j])) {
      // mark that position as Gold
      result.mineArea[i][j] = "G";
      curScore += Integer.parseInt(board[i][j]);
    }
    // mark that position as checked
    else {
      result.mineArea[i][j] = "+";
    }
    // update score if it is less than current score
    if (result.totalScore < curScore) {
      result.totalScore = curScore;
      result.totalStep = curStep;
      result.finalPath = curPath.toString();
    }

    findPath(board, i + 1, j, curScore, curStep, "D", curPath, result);
    findPath(board, i, j + 1, curScore, curStep, "R", curPath, result);

    // if it is not the start position, then backtrack
    if (curPath.length() != 0) {
      curPath.deleteCharAt(curPath.length() - 1);
    }

    // mark the unchecked position as "."
    result.mineArea[i][j] = ".";

  }

  public void exhaustiveSearch(String[][] board) {
    BruteForceSolution res = new BruteForceSolution(board.length, board[0].length);
    StringBuffer curPath = new StringBuffer();
    // initialize the mineArea with all "."
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        res.mineArea[i][j] = ".";
      }
    }

    long startTime = System.currentTimeMillis();
    // call findPath function to find the shortest path
    findPath(board, 0, 0, 0, 0, null, curPath, res);
    long stopTime = System.currentTimeMillis();
    long elapsedTime = stopTime - startTime;
    System.out.println("Steps: " + res.totalStep);
    System.out.println("Golds: " + res.totalScore);
    System.out.println("Path: " + res.finalPath);
    System.out.println("Running in: " + elapsedTime + " ms");
  }

}

class BruteForceSolution {
  String[][] mineArea;
  int totalStep;
  int totalScore;
  String finalPath;

  public BruteForceSolution(int rows, int cols) {
    totalStep = 0;
    totalScore = 0;
    mineArea = new String[rows][cols];
  }
}