package com.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1204 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t<= T; t++){
            int test_case = Integer.parseInt(br.readLine());
            String input = br.readLine();
            int [] arr = new int[101];
            StringTokenizer st = new StringTokenizer(input, " ");

            while (st.hasMoreTokens()) {
                int number = Integer.parseInt(st.nextToken());

                arr[number] += 1;
            }
            int count = 0;
            int num = 0;
            for(int i =0 ; i<101 ; i++){
                if(Integer.compare(count, arr[i]) == -1){
                    count = arr[i];
                    num = i;
                }else if(Integer.compare(count, arr[i]) == 0){
                    count = arr[i];
                    num = i;
                }
            }
            System.out.println("#"+ test_case+" "+num);
        }
    }
}
