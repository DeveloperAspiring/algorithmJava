package com.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P4192BFS {

    private static final int[] dx = {-1,1,0,0};
    private static final int[] dy = {0,0,-1,1};
    private static int mi = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testCase =  1; testCase <= T ; testCase++){
            int N = Integer.parseInt(br.readLine());
            char[][] arrWay = new char[N][N];
            int[][] arrWayCheck = new int[N][N];
            for(int i = 0; i<N; i++){
                StringTokenizer s = new StringTokenizer(br.readLine(), " ");

                for (int j = 0; j<N; j++){
                    arrWay[i][j] = s.nextToken().charAt(0);
                    arrWayCheck[i][j] = Integer.MAX_VALUE;
                }
            }
            String line = br.readLine();
            // 2. 공백 단위로 문자열을 쪼갭니다.
            StringTokenizer st = new StringTokenizer(line," ");
            // 3. 쪼개진 문자열을 int로 변환하여 변수에 담습니다.
            int startRow = Integer.parseInt(st.nextToken());
            int startColum = Integer.parseInt(st.nextToken());
            line = br.readLine();
            // 2. 공백 단위로 문자열을 쪼갭니다.
            st = new StringTokenizer(line);
            // 3. 쪼개진 문자열을 int로 변환하여 변수에 담습니다.
            int endRow = Integer.parseInt(st.nextToken()); // 3
            int endColum = Integer.parseInt(st.nextToken()); // 5
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{startRow,startColum});
            arrWayCheck[startRow][startColum] = 0;
            while (!queue.isEmpty()){
                int[] xy = queue.poll();
                for(int i = 0; i < 4; i++){
                    int nx = xy[0] + dx[i];
                    int ny = xy[1] + dy[i];

                    if(nx < 0|| nx >= N||ny < 0|| ny >= N|| arrWay[nx][ny] == '1' || arrWayCheck[nx][ny] < arrWayCheck[xy[0]][xy[1]] +1)
                        continue;
                    arrWayCheck[nx][ny] = arrWayCheck[xy[0]][xy[1]] +1;
                    queue.add(new int[]{nx,ny});
                }
            }
            if(arrWayCheck[endRow][endColum] == Integer.MAX_VALUE){
                System.out.println("#" + testCase+" "+"-1");
            }else{
                System.out.println("#" + testCase+" "+arrWayCheck[endRow][endColum]);
            }


        }
    }
}
