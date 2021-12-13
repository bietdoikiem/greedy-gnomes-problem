package com.RMIT.algorithm;

import static org.junit.Assert.assertEquals;

import com.RMIT.algorithm.utils.MatrixUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExhaustiveSearchTest {
  long startTime;

  @Before
  public void start() {
    startTime = System.currentTimeMillis();
  }

  @After
  public void end() {
    System.out
        .println(String.format("TEST DONE! Time elapsed: %fs", (System.currentTimeMillis() - startTime) / 1000F));
  }

  @Test
  public void solve_ShouldReturn6GoldAnd2Steps_WhenGiven3x3Matrix() {
    ExhaustiveSearch.OptimalSolution solution = ExhaustiveSearch
        .solve(MatrixUtils.parseFromFile("src/test/resources/sample-3x3.txt"));
    assertEquals(6, solution.gold);
    assertEquals(2, solution.steps);
  }

  @Test
  public void solve_ShouldReturn18GoldAnd27Steps_WhenGiven12x23Matrix() {
    ExhaustiveSearch.OptimalSolution solution = ExhaustiveSearch
        .solve(MatrixUtils.parseFromFile("src/test/resources/sample-12x23.txt"));
    assertEquals(18, solution.gold);
    assertEquals(27, solution.steps);
  }
}
