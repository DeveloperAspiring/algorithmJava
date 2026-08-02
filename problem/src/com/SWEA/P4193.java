//package com.SWEA;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//public class P4193 {
//    private static final int[] dx = {-1,1,0,0};
//    private static final int[] dy = {0,0,-1,1};
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int T = Integer.parseInt(br.readLine());
//        StringTokenizer st;
//        for(int testCase = 1 ; testCase <= T; testCase++){
//            int N = Integer.parseInt(br.readLine());
//            int[][] arr = new int[N][N];
//            int[][] arrWay = new int[N][N];
//            for(int index = 0 ; index <N; index++){
//                st = new StringTokenizer(br.readLine()," ");
//                for(int j = 0;j <N; j++){
//                    arr[index][j] = Integer.parseInt(st.nextToken());
//                    arrWay[index][j] = Integer.MAX_VALUE;
//                }
//            }
//            st = new StringTokenizer(br.readLine()," ");
//            int sr = Integer.parseInt(st.nextToken());
//            int sc = Integer.parseInt(st.nextToken());
//            st = new StringTokenizer(br.readLine()," ");
//            int er = Integer.parseInt(st.nextToken());
//            int ec = Integer.parseInt(st.nextToken());
//
//            Queue<int[]> queue = new LinkedList<>();
//            queue.add(new int[]{sr,sc});
//            arrWay[sr][sc] = 0;
//            while (!queue.isEmpty()){
//                int[] xy = queue.poll();
//                for(int i = 0; i < 4; i++){
//                    int nr = xy[0];
//                    int nc = xy[1];
//
//                    if(nr <0 || nr >= N|| nc <0 || nc >=N || arr[nr][nc] == 1 || arrWay[nr][nc] ){
//
//                    }
//                }
//
//            }
//        }
//    }
//}
