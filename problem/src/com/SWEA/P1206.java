package com.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P1206 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 1; i<= 10; i++){
            int N = Integer.parseInt(br.readLine());
            List<Integer> list = new ArrayList<>();
            String line = br.readLine();
            if (line != null) {
                // StringTokenizer로 공백 기준 토큰 분리
                StringTokenizer st = new StringTokenizer(line, " ");

                while (st.hasMoreTokens()) {
                    list.add(Integer.parseInt(st.nextToken()));
                }
                int total = 0;
                for(int index = 0; index< list.size(); index++ ){
                    int count = Integer.MAX_VALUE;
                    for(int sIndex = index-1; sIndex >= 0 && index-sIndex<=2;sIndex--){
                        count = Integer.min(count, list.get(index) - list.get(sIndex));
                    }

                    for(int sIndex = index+1; sIndex < list.size() && sIndex-index<=2;sIndex++){
                        count = Integer.min(count, list.get(index) - list.get(sIndex));
                    }
                    if(count > 0){
                        total += count;
                    }
                }
                System.out.println("#"+i+" "+total);

            }
        }

    }
}
