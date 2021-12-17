package com.RMIT.algorithm.utils;

import java.util.Scanner;

import com.RMIT.algorithm.ExhaustiveSearch;
import com.RMIT.algorithm.MemoizationSearch;
import com.RMIT.algorithm.Solution;

public class MenuUtils {
  public static void start(String filename) {
    System.out.println("Please specify an algorithm to solve the problem (1 or 2) or quit the program (0): ");
    System.out.println("1. Exhaustive Search");
    System.out.println("2. Dynamic Programming (Memoization) Search");
    System.out.println("0. Quit");
    Scanner sc = new Scanner(System.in);
    System.out.print("Your option: ");
    String option = sc.nextLine();
    Solution solution; // Init solution
    switch (option) {
      case "1":
        solution = ExhaustiveSearch.solveCLI(MatrixUtils.parseFromFile(filename));
        displaySolution(solution);
        break;
      case "2":
        solution = MemoizationSearch.solveCLI(MatrixUtils.parseFromFile(filename));
        displaySolution(solution);
        break;
      case "0":
        System.out.println("Exiting program...");
        break;
      default:
        System.out.println("ERROR! Invalid option. Please specify between 1, 2 or 0");
        start(filename);
    }
    sc.close();
  }

  private static void displaySolution(Solution solution) {
    System.out.println("Optimal Gold 🪙 : " + solution.getGold());
    System.out.println("Optimal Steps 👣 : " + solution.getSteps());
    System.out.println("Optimal Path 🧩 : " + solution.getPath());
    MatrixUtils.display(solution.getMatrix());
  }
}
