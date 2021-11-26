package com.RMIT.algorithm;

import java.util.ArrayList;

public class DPTopDown {
        // Number of rows, columns and steps
        static int n_rows=12, n_columns=23, step=0;
        static ArrayList<Character> path = new ArrayList<Character>();

        static int a[][] = {{0, 'X', 0, 0, 'X', 0, 0, 0, 0, 0, 0, 0, 0, 0, 'X', 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 'X', 4, 0, 0, 0, 0, 0, 0, 0, 0, 'X', 0, 'X', 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 'X', 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 0, 'X', 0, 0, 0, 0, 0, 0, 'X', 0, 0, 0, 0, 0, 0, 2, 'X', 0, 0, 0, 0},
                {2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 'X', 4, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 'X', 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 'X', 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 3, 0, 0, 'X', 0, 0},
                {2, 'X', 'X', 'X', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'X', 0, 0, 0, 0, 0, 'X', 0}};

        // Variable temp_max_sum is used to store maximum sum till current position
        static int temp_max_sum[][] = new int[27][27];

        // Variable visited is used to keep track of all the visited positions
        static int visited[][] = new int[27][27];

        // Variable path_arr is used to store the maximum path
        static int path_arr[][] = new int[27][27];

        // For storing current sum
        static int current_sum = 0;

        // For continuous update of maximum sum required
        static int total_sum = 0;

        static void print_array(int[][] arr){
            for (int[] ints : arr) {
                for (int anInt : ints) {
                    if (anInt == 0) {
                        System.out.print("XX ");
                    } else if (anInt < 10)
                        System.out.print(anInt + "  ");
                    else
                        System.out.print(anInt + " ");
                }
                System.out.println();
            }
        }
        static int find_path(int row, int column){
            // Base case
            if (temp_max_sum[row+1][column] == 0 && temp_max_sum[row][column+1] == 0)
                    return 1;
            if (temp_max_sum[row+1][column] >= temp_max_sum[row][column+1]){
                path_arr[row+1][column] = 1;
                path.add('D');
                step++;
                find_path(row+1,column);
            }
            else {
                path_arr[row][column+1] = 1;
                path.add('R');
                step++;
                find_path(row,column+1);
            }
            return step;
        }
        // Function to calculate maximum sum of path
        static int maximum_sum_path(int row, int column)
        {
            // Checking boundary condition
            if (row == n_rows - 1 && column == n_columns - 1 && a[row][column] != 'X')
                return a[row][column];

            // Checking whether or not a(row, column) is visited
            if (visited[row][column] != 0 && a[row][column] != 'X')
                return temp_max_sum[row][column];

            // Marking a(row, column) is visited
            visited[row][column] = 1;

            int total_sum = 0;

            // Checking whether the position hasn't visited the last row or the last column. Making recursive call
            // for all the possible moves from the current cell and then adding the maximum returned by
            // the calls and updating it.
            if (row < n_rows - 1 && column < n_columns - 1 && a[row][column] != 'X')
            {
                int current_sum = Math.max(
                        maximum_sum_path(row, column + 1),
                        maximum_sum_path(row + 1, column));
                total_sum = a[row][column] + current_sum;
            }

            // Checking whether position
            // has reached last row
            else if (row == n_rows - 1 & a[row][column] != 'X')
                total_sum = a[row][column] +
                        maximum_sum_path(row, column + 1);

                // If the position is in the last column
            else
                if (a[row][column] != 'X')
                total_sum = a[row][column] +
                        maximum_sum_path(row + 1, column);

            // Updating the maximum sum till the current position in the temp_max_sum
            temp_max_sum[row][column] = total_sum;

            // Returning the updated maximum value
            return total_sum;
        }

        // Driver Code
        public static void main(String[] args)
        {
//             Calling the implemented function
            System.out.println( "Gold: "+maximum_sum_path(0, 0));
            System.out.println("Steps: "+find_path(0,0));
            System.out.println("Path: "+ path);
//            print_array(temp_max_sum);
//            System.out.println("--------------");
//            print_array(path_arr);
        }
    }

