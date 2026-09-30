package com.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P3307 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase<= T; testCase++){
            int N = Integer.parseInt(br.readLine());
            int [] lan = new int [N];
            int [] size = new int [N];
            StringTokenizer st= new StringTokenizer(br.readLine(), " ");
            for(int i = 0 ; i < N ; i++){
                int a = Integer.parseInt(st.nextToken());
                lan[i] = a;
                size[i] = 1;
            }
            int max = 0;
            for(int i = 0; i < N; i++){
                int fl = lan[i];
                int s = size[i];
                for(int j = i+1; j < N; j++){
                    if(fl < lan[j] && s+ 1 > size[j] ){
                        size[j] = s + 1;
                        max= Math.max(size[j], max);
                    }
                }
            }

            System.out.println("#"+testCase+" "+max);
        }
    }
}
