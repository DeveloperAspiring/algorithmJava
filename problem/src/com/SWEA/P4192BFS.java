package com.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P4192BFS {
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


        }
    }
}
