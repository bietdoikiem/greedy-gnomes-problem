package com.RMIT.algorithm;

import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class DPTopDown {
        // Number of rows, columns and steps
        static int n_rows=0, n_columns=0, step=0;
        static ArrayList<Character> path = new ArrayList<Character>();

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

        static int[][] read_sample_file() throws FileNotFoundException{
            
                File myObj = new File("/Users/thien/Desktop/cosc2658-group-asm/src/main/java/resources/sample1.txt");
                Scanner myReader = new Scanner(myObj);
                n_rows = Integer.parseInt(myReader.next());
                n_columns = Integer.parseInt(myReader.nextLine().strip());
                int i=0,j=0;
                int[][] input_array = new int[n_rows][n_columns];
                while (myReader.hasNextLine()) {
                        if (j == n_columns){j =0; i++;}
                        String data = myReader.next();
                    if (data.charAt(0) == 'x') input_array[i][j] = -1;
                    else if (data.charAt(0) == '.') input_array[i][j] = 0;
                    else input_array[i][j] = Integer.parseInt(data);
                    
                    j++;
                }
                myReader.close();
              return input_array;
        }

        static void print_array(int[][] arr){
            for (int i = 0; i < n_rows; i++) {
                for (int j = 0; j < n_columns; j++) {
                    if (arr[i][j] == 0) {
                        System.out.print("XX ");
                    } else if (arr[i][j] < 10)
                        System.out.print(arr[i][j] + "  ");
                    else
                        System.out.print(arr[i][j] + " ");
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
        static int maximum_sum_path(int row, int column,int[][] input_matrix)
        {
            // Checking boundary condition
            if (row == n_rows - 1 && column == n_columns - 1 && input_matrix[row][column] != -1)
                return input_matrix[row][column];

            // Checking whether or not a(row, column) is visited
            if (visited[row][column] != 0 && input_matrix[row][column] != -1)
                return temp_max_sum[row][column];

            // Marking a(row, column) is visited
            visited[row][column] = 1;

            int total_sum = 0;

            // Checking whether the position hasn't visited the last row or the last column. Making recursive call
            // for all the possible moves from the current cell and then adding the maximum returned by
            // the calls and updating it.
            if (row < n_rows - 1 && column < n_columns - 1 && input_matrix[row][column] != -1)
            {
                int current_sum = Math.max(
                        maximum_sum_path(row, column + 1,input_matrix),
                        maximum_sum_path(row + 1, column,input_matrix));
                total_sum = input_matrix[row][column] + current_sum;
                
            }

            // Checking whether position
            // has reached last row
            else if (row == n_rows - 1 & input_matrix[row][column] != -1){
                    total_sum = input_matrix[row][column] + maximum_sum_path(row, column + 1,input_matrix); 
            }
                

                // If the position is in the last column
            else if (input_matrix[row][column] != -1 ){
                total_sum = input_matrix[row][column] + maximum_sum_path(row+1, column,input_matrix);
            }
             
            // Updating the maximum sum till the current position in the temp_max_sum
            temp_max_sum[row][column] = total_sum;

            // Returning the updated maximum value
            return total_sum;
        }

        // Driver Code
        public static void main(String[] args)
        {
            try {
                int[][] a = read_sample_file();
                //             Calling the implemented function
                System.out.println( "Gold: "+maximum_sum_path(0, 0,a));
                System.out.println("Steps: "+find_path(0,0));
                System.out.println("Path: "+ path);
                // print_array(path_arr);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
           
        }
    }

