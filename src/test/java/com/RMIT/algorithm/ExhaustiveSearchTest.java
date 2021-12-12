package com.RMIT.algorithm;

import com.RMIT.algorithm.utils.MatrixUtils;

import org.junit.Test;

public class ExhaustiveSearchTest {
  @Test
  public void solve_ShouldReturn18GoldAnd27Steps_WhenGiven12x23Matrix() {
    ExhaustiveSearch.OptimalSolution solution = ExhaustiveSearch
        .solve(MatrixUtils.parseFromFile("src/test/resources/sample-12x23.txt"), false);
  }
}
