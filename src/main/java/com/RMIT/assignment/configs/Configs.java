package com.RMIT.assignment.configs;

public class Configs {
  private static final int MATRIX_MAX_ROWS = 27;
  private static final int MATRIX_MAX_COLUMNS = 27;

  /**
   * Get maximum number of rows allowed in the matrix
   * 
   * @return
   */
  public static int getMaxRows() {
    return MATRIX_MAX_ROWS;
  }

  /**
   * Get maximum number of columns allowed in the matrix
   * 
   * @return
   */
  public static int getMaxCols() {
    return MATRIX_MAX_COLUMNS;
  }

  private Configs() {
  }
}
