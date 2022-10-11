package SudokuSolver;

public class SudokuSolver {

    private static final int GRID_SIZE = 9;
    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 0, 8, 0 },
                {6, 8, 0, 4, 7, 0, 0, 2, 0 },
                {0, 1, 9, 5, 0, 8, 6, 4, 7 },
                {0, 6, 0, 9, 0, 0, 0, 0, 4 },
                {3, 4, 2, 6, 8, 0, 0, 0, 0 },
                {1, 9, 0, 0, 5, 0, 8, 3, 0 },
                {0, 0, 0, 7, 2, 0, 4, 0, 3 },
                {0, 0, 6, 0, 0, 5, 0, 1, 0 },
                {0, 0, 3, 8, 9, 1, 5, 0, 0 }
        };
        //printBoard(board);            //if you want to see b4 solving
        if(solver(board)){
            System.out.println("nigga solved");
            System.out.println();
        }
        else{
            System.out.println("nigga could not solve(");
        }
        printBoard(board);
    }

    private static boolean isInRow(int[][] board, int num, int row){
        for(int i = 0; i < GRID_SIZE; i++){
            if(board[row][i] == num){
                return true;
            }
        }
        return false;
    }

    private static boolean isInColumn(int[][] board, int num, int column){
        for(int i = 0; i < GRID_SIZE; i++){
            if(board[i][column] == num){
                return true;
            }
        }
        return false;
    }
    private static boolean isInBox(int[][] board, int num, int row, int column){
        int bRow = row - row % 3;           //to find local box coordinates
        int bColumn = column - column % 3;  // 3 * 3 box

        for(int i = bRow; i < bRow + 3; i++){       //iterates through 3 rows
            for(int j = bColumn; j < bColumn + 3; j++){ //iterates through 3 columns
                if(board[i][j] == num){
                    return true;
                }
            }
        }
        return false;
    }
    //general check if a num can be placed
   // private static boolean isValid(int[][] board, int num, int row, int column){
   //     if(isInRow(board, num, row) && isInColumn(board, num, column) && isInBox(board, num, row, column)){
    //        return false;
     //   }else {
    //        return true;
    //    }
   // }
    private static boolean isValid(int[][] board, int num, int row, int column){
        return !isInRow(board, num, row) &&
                !isInColumn(board, num, column) &&
                !isInBox(board, num, row, column);
    }

    //checking which num can be placed at the spot
    private static boolean solver(int[][] board){
        for (int row = 0; row < GRID_SIZE; row++){
            for (int column = 0; column < GRID_SIZE; column++){
                if(board[row][column] == 0){
                    for(int guess = 1; guess <= GRID_SIZE; guess++){
                        if(isValid(board, guess, row, column)){
                            board[row][column] = guess;
                            //recursion baby
                            if(solver(board)){
                                return true;
                                //backtracking baby
                            }else{
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }

        }
        return true;
    }

    private static void printBoard(int[][] board) {
        //System.out.println("\n");
        for(int row = 0; row < GRID_SIZE; row++){
            if(row % 3 == 0 && row != 0){
                System.out.println("-----------");
            }
            for(int column = 0; column < GRID_SIZE; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print(" | ");
                }
                    System.out.print(board[row][column]);
                }
            System.out.println();
            }
        }
    }
