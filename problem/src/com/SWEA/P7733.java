package com.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


//치즈 도둑
public class P7733 {

    public static class UnionFind {
        int[] parent;
        int[] rank;
        int N;

        public UnionFind(int N) {
            parent = new int[N * N];
            rank = new int[N * N];
            this.N = N;

            for(int i = 0 ; i < N*N; i++){
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);

            if (rank[rootx] > rank[rooty]) {
                parent[rooty] = rootx;
            } else if (rank[rootx] < rank[rooty]) {
                parent[rootx] = rooty;
            } else {
                parent[rooty] = rootx;
                rank[rootx]++;
            }
        }
    }

    final static int[][] rot = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int maxCount = 0;
            int N = Integer.parseInt(br.readLine());

            int[][] matrix = new int[N][N];

            for (int r = 0; r < N ; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int c = 0; c < N ; c++) {
                    int tok = Integer.parseInt(st.nextToken());

                    matrix[r][c] = tok;
                }
            }
           // boolean flag = false;
            for (int day = 1; day <= 100 ; day++) {

                for (int r = 0; r < N ; r++) {
                    for (int c = 0; c < N ; c++) {
                        if(matrix[r][c] == day) {
                            matrix[r][c] = -1;
                        }
                    }
                }


                //그룹을 만들기 유니온 파인드 활용
                UnionFind u = new UnionFind(N );
                boolean[][] checker = new boolean[N ][N ];
                for (int r = 0; r < N ; r++) {
                    for (int c = 0; c < N ; c++) {

                        int tok = matrix[r][c];

                        if (tok == -1) continue;

                        checker[r][c] = true;

                        Queue<int[]> qGroup = new ArrayDeque<>();
                        qGroup.add(new int[]{r, c});

                        while (!qGroup.isEmpty()) {

                            int[] loc = qGroup.poll();
                            for (int i = 0; i < 4; i++) {
                                int newR = loc[0] + rot[i][0];
                                int newC = loc[1] + rot[i][1];

                                if (newR < 0 || newC < 0 || newR >= N  || newC >= N  || matrix[newR][newC] == -1 || checker[newR][newC])
                                    continue;
                                u.union(r * (N ) + c, newR * (N ) + newC);
                                qGroup.add(new int[]{newR, newC});
                                checker[newR][newC] = true;

                            }
                        }
                    }
                }
                int cnt = 0;
                //그룹화된 유니온 파인드 인스턴스에서 그룹 찾기
                for (int r = 0; r < N ; r++) {
                    for (int c = 0; c < N ; c++) {
                        if(matrix[r][c] == -1) continue;
                        int i = r*(N) +c;
                        if(u.find(i) == i){
                            cnt++;
                        }
                    }
                }
                maxCount = Math.max(maxCount, cnt);
            }

            if(maxCount == 0){
                maxCount = 1;
            }
            System.out.println("#"+t+" "+maxCount);
        }

    }
}
