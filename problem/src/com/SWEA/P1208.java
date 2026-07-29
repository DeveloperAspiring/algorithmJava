package com.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class P1208 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int testCase = 1; testCase <= 10; testCase++) {
            int N = Integer.parseInt(br.readLine());
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line, " ");
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();

            for (int i = 0; i < 100; i++) {
                int a = Integer.parseInt(st.nextToken());
                treeMap.put(a, treeMap.getOrDefault(a, 0) + 1);
            }

            int minKey = treeMap.firstKey(); // 출력: 2 (가장 앞의 Key)
            int maxKey = treeMap.lastKey();
            for (int i = 0; i < N; i++) {
                if(minKey == maxKey){
                    break;
                }
                treeMap.put(minKey, treeMap.get(minKey) -1);
                treeMap.put(minKey+1, treeMap.getOrDefault(minKey+1, 0) + 1);

                treeMap.put(maxKey, treeMap.get(maxKey) -1);
                treeMap.put(maxKey-1, treeMap.getOrDefault(maxKey-1, 0) + 1);

                if(treeMap.get(minKey) == 0) {
                    treeMap.remove(minKey);
                    minKey = treeMap.firstKey();
                }
                if(treeMap.get(maxKey) == 0) {
                    treeMap.remove(maxKey);
                    maxKey = treeMap.lastKey();
                }
            }
            int t = maxKey - minKey;
            System.out.println("#"+testCase+" "+ t);
        }

    }
}

