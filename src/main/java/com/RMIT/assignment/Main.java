package com.RMIT.assignment;

import java.io.FileNotFoundException;

import com.RMIT.assignment.components.Menu;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {

    // Example argument: src/main/resources/sample-12x23.txt
    System.out.println("===== Welcome to the Greedy Gnomes Problem program =====");
    Menu.start(args[0]);

  }
}
