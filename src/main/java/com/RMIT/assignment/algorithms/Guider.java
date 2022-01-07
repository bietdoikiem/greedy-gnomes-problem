package com.RMIT.assignment.algorithms;

import java.util.ArrayList;

public class Guider {

  static class Node {
    int maxPoint;
    ArrayList<Character> bestStep;
    int X;
    int Y;
    Node Right;
    Node Down;
    int Value;
    boolean traveled;

    public Node(int X, int Y, int Value) {
      this.maxPoint = 0;
      this.bestStep = new ArrayList<Character>();
      this.X = X;
      this.Y = Y;
      this.Value = Value;
      this.traveled = false;
    }
  }

  private static Node[][] nodesList;

  public Guider(int[][] map) {
    int rows = map.length;
    int columns = map[0].length;
    nodesList = new Node[rows][columns];
    addNode(map);
  }

  public static void addNode(int[][] map) {
    // add all node
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        Node node = new Node(i, j, map[i][j]);
        nodesList[i][j] = node;
      }
    }

    // add link between node
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        Node node = nodesList[i][j];
        try {
          node.Down = nodesList[i + 1][j];
        } catch (Exception e) {
        }
        try {
          node.Right = nodesList[i][j + 1];
        } catch (Exception e) {
        }

      }
    }
  }

  // add travelled as an indicate to be used later in dynamic programming, add a
  // condition to the below function for allowed dynamic programming
  public static int travel(Node position, boolean useTraveled) {
    if (position == null) {
      return 0;
    }
    if (useTraveled && position.traveled) {
      return position.maxPoint;
    }
    if (position.Right == null || position.Down == null) {
      position.maxPoint = position.Value;
    }
    if (position.Value == -1) {
      return 0;
    }
    int RightPoint = travel(position.Right, useTraveled);
    int DownPoint = travel(position.Down, useTraveled);

    if (RightPoint > DownPoint) {
      if (RightPoint > 0) {
        position.bestStep.add("R".charAt(0));
        position.bestStep.addAll(position.Right.bestStep);
      }
      position.maxPoint = position.Value + RightPoint;
    } else {
      if (DownPoint > 0) {
        position.bestStep.add("D".charAt(0));
        position.bestStep.addAll(position.Down.bestStep);
      }
      position.maxPoint = position.Value + DownPoint;
    }

    if (useTraveled) {
      position.traveled = true;
    }

    return position.maxPoint;
  }

  public void buildGuide(boolean dynamic) {
    Node begin = nodesList[0][0];
    travel(begin, dynamic);
  }

  public void printResults() {
    Node begin = nodesList[0][0];
    System.out.print("The step is: ");
    for (int i = 0; i < begin.bestStep.toArray().length; i++) {
      System.out.print(begin.bestStep.get(i));
    }
    System.out.print("\n");
    System.out.println("Total steps: " + begin.bestStep.size());
    System.out.println("Result in the point of " + begin.maxPoint);
  }

}
