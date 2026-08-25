package com.SWEA.GlacialMotion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    private static BufferedReader br;
    private static UserSolution userSolution = new UserSolution();

    private final static int MAX_N = 100;
    private final static int MAX_M = 5000;

    static UserSolution.RESULT user_ans;
    static int[][] m_ice_block;
    static int[][] ice_group;

    private static boolean run() throws Exception {
        StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");

        boolean okay = false;
        int n = Integer.parseInt(stdin.nextToken());
        int m = Integer.parseInt(stdin.nextToken());
        int k = Integer.parseInt(stdin.nextToken());

        m_ice_block = new int[MAX_N][MAX_N];
        ice_group = new int[MAX_M][3];

        for(int y = 0;y < n;y++)  {
            stdin = new StringTokenizer(br.readLine(), " ");
            for(int x = 0;x < n;x++) {
                m_ice_block[y][x] = Integer.parseInt(stdin.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            stdin = new StringTokenizer(br.readLine(), " ");
            ice_group[i][0] = Integer.parseInt(stdin.nextToken());
            ice_group[i][1] = Integer.parseInt(stdin.nextToken());
            ice_group[i][2] = Integer.parseInt(stdin.nextToken());
        }
        userSolution.init(n,  m,  m_ice_block, ice_group);
        okay = true;
        for(int q=0;q<k;q++) {
            user_ans = userSolution.oneYearLater();

            stdin = new StringTokenizer(br.readLine(), " ");
            System.out.println("\u001B[33m");
            for(int y=0;y<n;y++)  {
                for(int x=0;x<n;x++) {
                    int correct_ans = Integer.parseInt(stdin.nextToken());
                    System.out.print(correct_ans+" ");
                    if(correct_ans != user_ans.heights[y][x])
                        okay = false;
                }
                System.out.println();
            }
            System.out.println("-------------------------------------"+"\u001B[0m");
        }

        return okay;
    }

    public static void main(String[] args) throws Exception {
        int T, MARK;

        System.setIn(new java.io.FileInputStream("res/sample_input (3).txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");
        T = Integer.parseInt(stinit.nextToken());
        MARK = Integer.parseInt(stinit.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            int score = run() ? MARK : 0;
            System.out.println("#" + tc + " " + score);
        }

        br.close();
    }
}