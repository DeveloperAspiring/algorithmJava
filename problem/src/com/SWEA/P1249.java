package com.SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;



class Node implements Comparable<Node>{
    int x,y,cost;
    public Node(int x, int y, int cost){
        this.x = x;
        this.y = y;
        this.cost = cost;
    }


    @Override
    public int compareTo(Node o) {
        return this.cost- o.cost;
    }
}

public class P1249 {

    private static final int[] dx = new int[]{-1, 1, 0, 0};
    private static final int[] dy = new int[]{0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 2. 한 줄을 통째로 읽어온 뒤 공백 단위로 분할 준비
        //StringTokenizer st = new StringTokenizer(br.readLine());

        // 3. 데이터 읽기 예시 (첫 줄에 N과 M이 주어질 때)
        int T = Integer.parseInt(br.readLine());


        // 4. 여러 줄의 데이터를 연속으로 읽을 때
        for (int i = 0; i < T; i++) {

            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];
            int[][] dist = new int[N][N];

            Arrays.stream(dist).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));
            for (int j = 0; j < N; j++) {
                String line = br.readLine();
                for(int r = 0; r< N;r++){
                    arr[j][r] = line.charAt(r) -'0';
                }
            }
            dist[0][0] = arr[0][0];
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(0, 0, arr[0][0]));

            while (!pq.isEmpty()){
                Node now = pq.poll();

                if (now.cost > dist[now.x][now.y])
                    continue;

                if (now.x == N - 1 && now.y == N - 1){
                    break;
                }

                for (int d = 0; d < 4; d++) {

                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                        continue;

                    int nextCost = now.cost + arr[nx][ny];

                    if (nextCost < dist[nx][ny]) {
                        dist[nx][ny] = nextCost;
                        pq.offer(new Node(nx, ny, nextCost));
                    }
                }
            }
            int testcase = i+1;
            System.out.println("#"+testcase+" "+ dist[N-1][N-1]);

        }
    }
}
