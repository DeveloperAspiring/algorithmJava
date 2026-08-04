package com.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1954 {

    private static final int[] dx = {1,0,-1,0};
    private static final int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t<= T; t++){
            int N = Integer.parseInt(br.readLine());

            int[][] arr= new int[N][N];
            int way = 0;
            int row = 0, col = 0;
            for(int i = 1; i <= N*N ; i ++){
                arr[row][col] = i;

                if(row + dy[way] <0||row + dy[way] >= N || col + dx[way] <0||col + dx[way] >= N || arr[row + dy[way]][col + dx[way]] != 0){
                    way++;
                    way = way%4;
                }
                row = row + dy[way];
                col = col + dx[way];
            }
            System.out.println("#"+t);
            for(int i = 0; i < N ; i ++){
                for(int j = 0; j < N ; j ++){
                    System.out.print(arr[i][j]+" ");
                }
                System.out.println();
            }
        }
    }
}
