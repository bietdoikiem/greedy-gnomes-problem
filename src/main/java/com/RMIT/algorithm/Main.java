package com.RMIT.algorithm;

import java.io.FileNotFoundException;

import com.RMIT.algorithm.utils.MatrixUtils;
import com.RMIT.algorithm.utils.MenuUtils;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {

    //6 10
    System.out.println("6x10");
    int[][] Matrix = MatrixUtils.intMatrixFromFile("src/main/resources/sample-6x10.txt");
    Guider solver = new Guider(Matrix);
    solver.buildGuide(true);
    solver.printResults();

    //13x19
    System.out.println("13x19");
    int[][] Matrix1 = MatrixUtils.intMatrixFromFile("src/main/resources/sample-13x19.txt");
    Guider solver1 = new Guider(Matrix1);
    solver1.buildGuide(true);
    solver1.printResults();

    // 15x18
    System.out.println("15x18");
    int[][] Matrix2 = MatrixUtils.intMatrixFromFile("src/main/resources/sample-15x18.txt");
    Guider solver2 = new Guider(Matrix2);
    solver2.buildGuide(true);
    solver2.printResults();

    // 20x20
    System.out.println("20x20");
    int[][] Matrix3 = MatrixUtils.intMatrixFromFile("src/main/resources/sample-20x20.txt");
    Guider solver3 = new Guider(Matrix3);
    solver3.buildGuide(true);
    solver3.printResults();

    // 22x24
    System.out.println("22x24");
    int[][] Matrix4 = MatrixUtils.intMatrixFromFile("src/main/resources/sample-22x24.txt");
    Guider solver4 = new Guider(Matrix4);
    solver4.buildGuide(true);
    solver4.printResults();

    // 23x19
    System.out.println("23x19");
    int[][] Matrix5 = MatrixUtils.intMatrixFromFile("src/main/resources/sample-23x19.txt");
    Guider solver5 = new Guider(Matrix5);
    solver5.buildGuide(true);
    solver5.printResults();

    // 24x27
    System.out.println("24x27");
    int[][] Matrix6 = MatrixUtils.intMatrixFromFile("src/main/resources/sample-24x27.txt");
    Guider solver6 = new Guider(Matrix6);
    solver6.buildGuide(true);
    solver6.printResults();

    // 27x27
    System.out.println("27x27");
    int[][] Matrix7 = MatrixUtils.intMatrixFromFile("src/main/resources/sample-27x27.txt");
    Guider solver7 = new Guider(Matrix7);
    solver7.buildGuide(true);
    solver7.printResults();



  }
}
