package jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P3109 {
    static int[][] up = new int[][]{{-1, 1},{0,1},{1,1} };
    static int[][] down = new int[][]{{1,1},{0,1},{-1, 1}};
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        char[][] matrix = new char[row][col];

        for(int r = 0; r<row; r++){
            char[] cols = br.readLine().toCharArray();
            for(int c = 0; c<col; c++){
                matrix[r][c] = cols[c];
            }
        }
        int cout = 0;
        for(int r = 0; r<row; r++){
            dfs(r,0,row, col, matrix, r <=row/2 ? up: down);
        }
        System.out.println(count);
    }

    public static boolean dfs(int row, int col,int endRow, int endCol, char[][] matrix, int [][] move){
        if( col == endCol-1){
            count++;
            return true;
        }

        for(int i = 0 ; i < 3; i++){
            int nr = row + move[i][0];
            int nc = col + move[i][1];
            if(nr <0 || nc < 0|| nr >= endRow || nc>= endCol || matrix[nr][nc] =='x')continue;
            matrix[nr][nc] ='x';
             boolean c =  dfs(nr,nc,endRow,endCol,matrix,move);
             if(c){
                 return true;
             }else{
                 matrix[nr][nc] ='.';
             }
        }

        return false;
    }
}
