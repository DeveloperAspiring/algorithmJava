package com.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

public class P1238 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int testCase = 1; testCase <= 10; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int size, start;
            size = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            HashMap<Integer, ArrayList<Integer>> arr = new HashMap<>();
            boolean[] checker = new boolean[101];
            st = new StringTokenizer(br.readLine(), " ");
            while (st.hasMoreElements()) {
                int row = Integer.parseInt(st.nextToken());
                if (!arr.containsKey(row)) {
                    arr.put(row, new ArrayList<>());
                }
                arr.get(row).add(Integer.parseInt(st.nextToken()));

            }
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(start);
            checker[start] = true;
            int count = 1;

            int max = 0;
            while (!queue.isEmpty()) {
                count--;
                int num = queue.poll();

                max = Math.max(max, num);
                if (arr.containsKey(num)) {
                    int arrSize = arr.get(num).size();
                    for (int i = 0; i < arrSize; i++) {
                        int node = arr.get(num).get(i);
                        if (checker[node]) continue;
                        checker[node] = true;
                        queue.add(node);

                    }
                }
                if (count == 0) {
                   // System.out.println("--------------------------");
                    count = queue.size();
                    if (count > 0) {
                        max = 0;
                    }
                }
            }

            System.out.println("#"+testCase+" "+max);
          //  System.out.println("end");
        }
    }
}
