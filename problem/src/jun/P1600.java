package jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.concurrent.CountDownLatch;

public class P1600 {

    static int[][] move = new int[][] {{-1,0},{1,0},{0,1},{0,-1}};
    static int[][] moveH = new int[][] {{-2,1},{-2,-1},{2,1},{2,-1},{1,2},{-1,2},{-1,-2},{1,-2}};

    static class Node implements Comparable<Node>{

        int [] loc;
        int count;
        int hCount = 0;
        Node(int r,int c, int count, int hCount){
            loc = new int[] {r,c};
            this.count = count;
            this.hCount = hCount;
        }

        @Override
        public int compareTo(Node o) {
            // TODO Auto-generated method stub
            return Integer.compare(count, o.count);
        }

    }

    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());


        int startR = 0,startC = 0;
        int endR = row-1,endC = col-1;

        int[][][] matrixC = new int[row][col][2];
        for(int r = 0; r<row; r++) {
            st = new StringTokenizer(br.readLine()," ");
            for(int c = 0; c<col; c++) {
                int a =Integer.parseInt(st.nextToken());
                if(a == 1) {
                    matrixC[r][c][0] = 0;
                    matrixC[r][c][1] = 0;
                }else {
                    matrixC[r][c][0] = Integer.MAX_VALUE;
                    matrixC[r][c][1] = Integer.MAX_VALUE;
                }


            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, 0, 0));
        matrixC[0][0][0] = 0;
        matrixC[0][0][1] = 0;

        int answer = -1;
        while (!pq.isEmpty()) {
            Node n = pq.poll();
            print(matrixC, row,col,0);
            print(matrixC, row,col,1);
            int[] loc = n.loc;
            if(loc[0] == endR && loc[1] == endC) {
                answer = n.count;
                break;
            }

            for(int i =0 ; i < 4; i++) {
                int newR = loc[0] + move[i][0];
                int newC = loc[1] + move[i][1];

                if(newR <0 || newC < 0|| newR >= row || newC >= col || n.count +1> matrixC[newR][newC][0])continue;
                matrixC[newR][newC][0] = n.count +1;
                pq.add(new Node(newR,newC, n.count +1, n.hCount));
            }

            if(n.hCount >= K)continue;
            for(int i =0 ; i < 8; i++) {
                int newR = loc[0] + moveH[i][0];
                int newC = loc[1] + moveH[i][1];

                if(newR <0 || newC < 0|| newR >= row || newC >= col || n.count +1> matrixC[newR][newC][1])continue;
                matrixC[newR][newC][1] = n.count +1;
                pq.add(new Node(newR,newC, n.count +1,n.hCount+1));
            }
        }

        System.out.println(answer);

    }

    public static void print(int[][][] m ,int row,int col,int a) {
        System.out.println(a == 1 ? "말" :"원");
        for(int r = 0; r<row; r++) {
            for(int c = 0; c<col; c++) {
                System.out.print(m[r][c][a]+" ");


            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

}

