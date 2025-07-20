package org.example.javaCoding;

public class NumberOfIslands {

    public static void main(String[] args) {

        // Number of Islands: Given a 1D grid of '1's (land) and '0's (water), count the number of islands.
        int[] grid = {0,1,1,1,0,1,0,1,0,0,1,1,0,1,0,1,0,1}; //7
        //Integer[] grid = new Integer[]{1, 2, 3};
        System.out.println("Number of Islands (With Int array as Input) = " + totalNumberOfIslands(grid));

        String input = "0110010110101";
        System.out.println("Number of islands (With String value as Input): " + totalNumberOfIslands(input));  // Output: 3

    }

    private static int totalNumberOfIslands(String input) {
        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '1' && (i == 0 || input.charAt(i - 1) == '0')) {
                count++;
            }
        }
        return count;
    }

    private static int totalNumberOfIslands(int[] grid) {

        int count = 0;
        boolean onIsland = false;
        for (int arr : grid) {
            if (arr == 1 && !onIsland) {
                onIsland = true; // new island starts
                count++;
            } else if (arr == 0){
                onIsland = false; // water resets the flag
            }
        }
        return count;
    }
}
