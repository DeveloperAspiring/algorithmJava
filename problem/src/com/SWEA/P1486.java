package com.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1486 {

    public static int Combi(int [] arr, int flag,int sum, int target){
        int n = Integer.MAX_VALUE;
        if(sum >= target)return sum;
        for(int i = flag+1; i<arr.length; i++){
           // System.out.println(sum +"+"+ arr[i]);
           n = Math.min(n, Combi(arr, i, sum + arr[i], target));
           if(n == target)return n;
        }
        return n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase<= T; testCase++){
            int N,B;
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            int [] arr = new int[N];
            st = new StringTokenizer(br.readLine()," ");

            for (int i = 0; i< N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int an = Integer.MAX_VALUE;
            for(int i = 0; i< N; i++){
                an = Math.min(an,Combi(arr, i, arr[i], B) );
            }

            System.out.println("#" + testCase+" "+(an- B));
        }
    }
}
