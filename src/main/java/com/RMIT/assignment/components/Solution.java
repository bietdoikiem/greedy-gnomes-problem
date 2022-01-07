package com.RMIT.assignment.components;

/**
 * Class for the optimal solution of the problem
 */
public class Solution {
  private int gold;
  private int steps;
  private String path;
  private int[][] matrix; // Solution matrix (for visualization)

  public Solution() {
  }

  /**
   * Initialize solution with fundamental params (maximum gold, steps, path)
   * 
   * @param gold
   * @param steps
   * @param path
   */
  public Solution(int gold, int steps, String path) {
    this.gold = gold;
    this.steps = steps;
    this.path = path;
  }

  /**
   * Initialize solution with matrix for later visualization purpose
   * 
   * @param rows
   * @param cols
   */
  public Solution(int rows, int cols) {
    matrix = new int[rows][cols];
  }

  /**
   * Get maximum gold mined
   * 
   * @return gold
   */
  public int getGold() {
    return this.gold;
  }

  /**
   * Set maximum gold mined
   * 
   * @param gold
   */
  public void setGold(int gold) {
    this.gold = gold;
  }

  /**
   * Get total number of steps to mine maximum gold
   * 
   * @return steps
   */
  public int getSteps() {
    return this.steps;
  }

  /**
   * Set total number of steps
   * 
   * @param steps
   */
  public void setSteps(int steps) {
    this.steps = steps;
  }

  /**
   * Get path to mine maximum gold
   * 
   * @return path
   */
  public String getPath() {
    return this.path;
  }

  /**
   * Set path to mine maximum gold
   * 
   * @param path
   */
  public void setPath(String path) {
    this.path = path;
  }

  /**
   * Get solution matrix for visualization purpose
   * 
   * @return
   */
  public int[][] getMatrix() {
    return this.matrix;
  }

  /**
   * Set solution matrix for visualization purpose
   * 
   * @return
   */
  public void setMatrix(int[][] matrix) {
    this.matrix = matrix;
  }
}