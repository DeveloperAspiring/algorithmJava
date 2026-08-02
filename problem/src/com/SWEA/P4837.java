package com.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class P4837 {
    private static int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            Stack<int[]> stack = new Stack<>();
            int[] f = new int[3];
            f[0] = 1;
            f[1] = 1;
            f[2] = 1;
            stack.add(f);
            int sum = 0;
            while (!stack.isEmpty()) {
                int[] current = stack.pop();
                int currentSum = current[0];
                int currentCount = current[1];
                int nextIndex = current[2];


                if (currentCount == N && currentSum == K){
                    sum++;
                    continue;
                }
                if (nextIndex >= 12 || currentSum > K) {
                    continue;
                }

                stack.add(new int[]{currentSum, currentCount, nextIndex + 1});
                stack.add(new int[]{currentSum + arr[nextIndex], currentCount + 1, nextIndex + 1});
            }


            System.out.println("#" + testCase + " " + sum);



        }
    }
}
