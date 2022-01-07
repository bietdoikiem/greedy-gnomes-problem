package com.RMIT.algorithm;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import com.RMIT.algorithm.utils.MatrixUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MemoizationSearchTest {
  long startTime;
  Solution solution;
  

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
    System.out.println("Test case: 3x3 Matrix");
    solution = MemoizationSearch
        .solve(MatrixUtils.parseFromFile("src/test/resources/sample-3x3.txt"));
    assertEquals(6, solution.getGold());
    assertEquals(2, solution.getSteps());
  }

  @Test
  public void solve_ShouldReturn27GoldAnd19Steps_WhenGiven9x16Matrix() {
    System.out.println("Test case: 9x16 Matrix");
    solution = MemoizationSearch
        .solve(MatrixUtils.parseFromFile("src/test/resources/sample-9x16.txt"));
    assertEquals(27, solution.getGold());
    assertEquals(19, solution.getSteps());
  }

  @Test
  public void solve_ShouldReturn18GoldAnd27Steps_WhenGiven12x23Matrix() {
    System.out.println("Test case: 12x23 Matrix");
    solution = MemoizationSearch
        .solve(MatrixUtils.parseFromFile("src/test/resources/sample-12x23.txt"));
    assertEquals(18, solution.getGold());
    assertEquals(27, solution.getSteps());
  }


  
}
