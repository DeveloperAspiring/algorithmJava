package com.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5188 {
    static int minxValue = Integer.MAX_VALUE;
    public static void colb(int[][] arr, boolean[] checker,int depth, int size, int  rSum, int cSum){
        if(depth == size){
            minxValue = Math.min(minxValue, rSum);
            minxValue = Math.min(minxValue, cSum);
            return;
        }
        for(int i = 0 ; i <size; i++){
            if(checker[i] || (minxValue <= rSum + arr[depth][i] && minxValue <= cSum + arr[i][depth]))continue;
            checker[i] = true;
            colb(arr, checker, depth +1, size,rSum + arr[depth][i],cSum + arr[i][depth] );
            checker[i] = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase<= T; testCase++){
            int N;
            minxValue = Integer.MAX_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            N = Integer.parseInt(st.nextToken());
            int[][] arr = new int[N][N];
            boolean[] checker = new boolean[N];
            for(int row = 0; row<N;row++){
                st = new StringTokenizer(br.readLine()," ");
                for(int col = 0; col<N;col++){
                    arr[row][col] = Integer.parseInt(st.nextToken());
                }
            }
            colb(arr, checker,0 ,N,0,0);
            System.out.println("#" + testCase+" "+(minxValue));
        }
    }
}
