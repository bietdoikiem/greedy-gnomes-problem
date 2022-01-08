package com.RMIT.assignment.components;

import java.util.Scanner;

import com.RMIT.assignment.algorithms.ExhaustiveSearch;
import com.RMIT.assignment.algorithms.MemoizationSearch;
import com.RMIT.assignment.utils.MatrixUtils;

public class Menu {
  public static void start(String filename) {
    System.out.println("Please specify an algorithm to solve the problem (1 or 2) or quit the program (0): ");
    System.out.println("1. Exhaustive Search");
    System.out.println("2. Memoization Search (Dynamic Programming)");
    System.out.println("0. Quit");
    Scanner sc = new Scanner(System.in);
    System.out.print("Your option: ");
    String option = sc.nextLine();
    TimeIndicator indicator; // init indicator
    Solution solution; // Init solution
    switch (option) {
      case "1":
        indicator = new TimeIndicator(); // Init indicator
        solution = ExhaustiveSearch.solve(MatrixUtils.parseIntFromFile(filename));
        indicator.stop();
        displaySolution(solution);
        break;
      case "2":
        indicator = new TimeIndicator(); // Init indicator
        solution = MemoizationSearch.solve(MatrixUtils.parseIntFromFile(filename));
        indicator.stop();
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

  /**
   * Display the solution object in the console
   * 
   * @param solution Solution of solved matrix
   */
  private static void displaySolution(Solution solution) {
    System.out.println("Optimal Gold 🪙 : " + solution.getGold());
    System.out.println("Optimal Steps 👣 : " + solution.getSteps());
    System.out.println("Optimal Path 🧩 : " + solution.getPath());
  }
}
