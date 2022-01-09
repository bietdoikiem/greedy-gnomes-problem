package com.RMIT.assignment;

import static org.junit.Assert.assertEquals;

import com.RMIT.assignment.algorithms.ExhaustiveSearch;
import com.RMIT.assignment.components.Solution;
import com.RMIT.assignment.utils.MatrixUtils;

import org.junit.Test;

public class ExhaustiveSearchTest {
  Solution solution;

  @Test
  public void solve_ShouldReturn6GoldAnd2Steps_WhenGiven3x3Matrix() {
    System.out.println("Test case: 3x3 Matrix");
    solution = ExhaustiveSearch
        .solve(MatrixUtils.parseIntFromFile("src/test/resources/sample-3x3.txt"));
    assertEquals(6, solution.getGold());
    assertEquals(2, solution.getSteps());
    System.out.println("Test DONE!");
  }

  @Test
  public void solve_ShouldReturn27GoldAnd19Steps_WhenGiven9x16Matrix() {
    System.out.println("Test case: 9x16 Matrix");
    solution = ExhaustiveSearch
        .solve(MatrixUtils.parseIntFromFile("src/test/resources/sample-9x16.txt"));
    assertEquals(27, solution.getGold());
    assertEquals(19, solution.getSteps());
    System.out.println("Test DONE!");
  }

  @Test
  public void solve_ShouldReturn18GoldAnd27Steps_WhenGiven12x23Matrix() {
    System.out.println("Test case: 12x23 Matrix");
    solution = ExhaustiveSearch
        .solve(MatrixUtils.parseIntFromFile("src/test/resources/sample-12x23.txt"));
    assertEquals(18, solution.getGold());
    assertEquals(27, solution.getSteps());
    System.out.println("Test DONE!");
  }
}
