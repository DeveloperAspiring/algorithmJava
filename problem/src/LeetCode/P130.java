package LeetCode;

import java.util.Arrays;
import java.util.Queue;

public class P130 {
    class Solution {
        private static final int [] dr = {-1,1,0,0};
        private static final int [] dc = {0,0,-1,1};
        public static void solve(char[][] board) {
            int size = board.length;
            for(int row = 0; row < size ; row++){
                for(int col = 0; col < size ; col++){
                    if(board[row][col] == 'X') continue;
                    dfs(board, row,col,size);
                }
            }

            System.out.print(Arrays.deepToString(board));
        }
        public static void dfs(char[][] board, int row, int col, int size){

            board[row][col] =  'C';
             boolean flag = false;
            for(int index = 0; index < 4; index++){
                if(row + dr[index] <0 || row + dr[index] >= size || col + dc[index] <0 || col + dc[index] >= size)continue;

                if(board[row + dr[index]][col + dc[index]] == 'O'){
                    dfs(board, row + dr[index], col + dc[index], size);
                    flag = true;
                }
                if(board[row + dr[index]][col + dc[index]] == 'C'){
                    flag = true;
                }
            }
            if(flag){
                board[row][col] =  'X';
            }else{
                board[row][col] =  'O';
            }
        }


    }
    public static void main(String[] args){
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
       // char[][] board = {{'X','X','X','X'},{'X','X','X','X'},{'X','X','X','X'},{'X','O','X','X'}};
        Solution.solve(board);
    }
}
