package com.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class P1219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int t = 1; t<= 10; t++){
            StringTokenizer st =  new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int ln = Integer.parseInt(st.nextToken());

            int[][] arr= new int[100][2];

            st =  new StringTokenizer(br.readLine(), " ");

            while(st.hasMoreTokens()){
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
               int index = arr[r][0] == 0 ? 0:  1;

                arr[r][index] = c;
            }


            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(arr[0]);
            boolean flag = false;
            while (!queue.isEmpty()){
                int[] cs =  queue.poll();
                for(int i = 0; i<2; i++){
                    if(cs[i] == 0)continue;
                    if(cs[i] == 99)flag = true;
                    queue.add(arr[cs[i]]);
                }
                if(flag)break;
            }

            if(flag){
                System.out.println(1);
            }else{
                System.out.println(0);
            }
        }
    }
}
