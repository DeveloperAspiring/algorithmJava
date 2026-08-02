package com.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1215 {
    public static void main(String str[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int testCase = 1; testCase <= 10; testCase++) {
            int N = Integer.parseInt(br.readLine());
            char[][] chars = new char[8][8];
            for (int col = 0; col < 8; col++) {
                String line = br.readLine();
                // 2. 공백 단위로 문자열을 쪼갭니다.
                char[] c = line.toCharArray();
                for (int row = 0; row < 8; row++) {
                    chars[col][row] = c[row];
                }
            }
            int sum = 0;
            for (int col = 0; col < 8 ; col++) {
                for (int row = 0; row < 8; row++) {
                    boolean flagX = true;
                    boolean flagY = true;
                    for (int i = 0; i < N - 1 - i; i++) {
                        if (row + N - 1 - i >= 8 || chars[col][row + i]!= chars[col][row + N - 1 - i]) flagX = false;
                        if (col + N - 1 - i >= 8 ||chars[col + i][row] != chars[col + N - 1 - i][row]) {

                            flagY = false;
                        }
                    }
                    if (flagX) sum++;
                    if (flagY) sum++;
                }

            }
            System.out.println("#"+testCase+" "+sum);
        }

    }
}

