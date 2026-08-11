package LeetCode;

import java.util.Arrays;
import java.util.Queue;

public class P130 {
    class Solution {
        private static final int[] dr = {-1, 1, 0, 0};
        private static final int[] dc = {0, 0, -1, 1};

        public static void solve(char[][] board) {
            int rsize = board.length;
            int csize = board[0].length;
            for (int row = 0; row < rsize; row++) {
                for (int col = 0; col < csize; col++) {
                    if (board[row][col] == 'X') continue;
                    for (int index = 0; index < 4; index++) {
                        if (row + dr[index] < 0 || row + dr[index] >= rsize || col + dc[index] < 0 || col + dc[index] >= csize) {
                            dfs(board,row,col,rsize,csize);
                            break;
                        }
                    }
                }
            }

            for (int row = 0; row < rsize; row++) {
                for (int col = 0; col < csize; col++) {
                    if(board[row][col] == 'O')board[row][col] ='X';
                    if(board[row][col] == 'C')board[row][col] ='O';
                }
            }

            System.out.print(Arrays.deepToString(board));
        }

        public static void dfs(char[][] board, int row, int col, int rsize, int csize) {

            board[row][col] = 'C';
            for (int index = 0; index < 4; index++) {
                if (row + dr[index] < 0 || row + dr[index] >= rsize || col + dc[index] < 0 || col + dc[index] >= csize)continue;

                if (board[row + dr[index]][col + dc[index]] == 'O') {
                    dfs(board, row + dr[index], col + dc[index], rsize, csize);

                }
            }
        }


    }

    public static void main(String[] args) {
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        char[][] board1 = {
                {'X', 'X', 'X', 'X'},
                {'X', 'X', 'O', 'X'},  // 이중 for문이 여기를 먼저 지나침
                {'X', 'X', 'O', 'X'},
                {'X', 'X', 'O', 'X'}   // 테두리
        };
        char[][] board2 = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'X'}
        };

        char[][] board3 = {
                {'O', 'O', 'O'},
                {'O', 'X', 'O'},
                {'O', 'O', 'O'}
        };

        char[][] board4 = {
                {'O', 'O', 'O', 'X', 'X'},
                {'X', 'X', 'O', 'X', 'X'},
                {'X', 'X', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'X', 'X'}
        };
        // char[][] board = {{'X','X','X','X'},{'X','X','X','X'},{'X','X','X','X'},{'X','O','X','X'}};
        Solution.solve(board4);
    }
}
