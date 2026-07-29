package com.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P4192DFS {

    private static final int[] dx = {-1,1,0,0};
    private static final int[] dy = {0,0,-1,1};
    private static int mi = Integer.MAX_VALUE;

    private static void print(boolean[][] check, int N, int sum){
        if (sum >= mi) {
            return;
        }
        for(int i= 0; i< N;i++){
            for(int j = 0; j<N;j++){
                System.out.print(check[i][j] ? 1 +" ": 0+" ");
            }
            System.out.println();
        }
        System.out.println("----------------------------"+ sum);
    }
    private static void dfs(int N,int stRow, int stCol, int endRow,int endCol,boolean[][] check, char[][] arr, int sum){
        //print(check, N, sum);
        if(stRow == endRow && stCol == endCol){
            mi = Math.min(sum, mi);
            return;
        }
        for(int d = 0; d < 4; d++){
            int nx = stRow + dx[d];
            int ny = stCol + dy[d];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N || arr[nx][ny] == '1' || check[nx][ny])
                continue;
            check[nx][ny] = true;
            dfs(N,nx,ny,endRow,endCol,check,arr,sum +1);
            check[nx][ny] = false;
            //print(check, N, sum);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testCase =  1; testCase <= T ; testCase++){
            int N = Integer.parseInt(br.readLine());
            char[][] arrWay = new char[N][N];
            boolean[][] arrWayCheck = new boolean[N][N];
            for(int i = 0; i<N; i++){
                StringTokenizer s = new StringTokenizer(br.readLine(), " ");

                for (int j = 0; j<N; j++){
                    arrWay[i][j] = s.nextToken().charAt(0);
                }
            }
            String line = br.readLine();

            // 2. 공백 단위로 문자열을 쪼갭니다.
            StringTokenizer st = new StringTokenizer(line," ");

            // 3. 쪼개진 문자열을 int로 변환하여 변수에 담습니다.
            int startRow = Integer.parseInt(st.nextToken());
            int startColum = Integer.parseInt(st.nextToken());
            arrWayCheck[startRow][startColum] = true;
            line = br.readLine();

            // 2. 공백 단위로 문자열을 쪼갭니다.
            st = new StringTokenizer(line);

            // 3. 쪼개진 문자열을 int로 변환하여 변수에 담습니다.
            int endRow = Integer.parseInt(st.nextToken()); // 3
            int endColum = Integer.parseInt(st.nextToken()); // 5
            int sum = 0;
            mi = Integer.MAX_VALUE;
            dfs(N,startRow,startColum,endRow,endColum,arrWayCheck,arrWay,sum);
            if(mi == Integer.MAX_VALUE){
                System.out.println("#" + testCase+" "+-1);
            }else{
                System.out.println("#" + testCase+" "+mi);
            }

        }
    }
}
