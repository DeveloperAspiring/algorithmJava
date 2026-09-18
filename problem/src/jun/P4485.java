package jun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P4485 {
    final static int [][] moving = new int[][]{{-1,0},{1,0}, {0,1},{0,-1}};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = 1;
        while (true){
            int N = Integer.parseInt(br.readLine());
            if(N == 0 )break;
            int[][][] matrix = new int[N][N][2];

            int sR= 0;
            int sC= 0;

            int eR= 0;
            int eC= 0;

            for(int r = 0 ; r < N; r++){
                StringTokenizer st = new StringTokenizer(br.readLine()," ");
                for(int c = 0 ; c < N; c++){
                    matrix[r][c][0] = Integer.parseInt(st.nextToken());
                    matrix[r][c][1] = Integer.MAX_VALUE;
                }
            }

            PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a,b) -> Integer.compare(a[2],b[2]));
            priorityQueue.add(new int[]{sR,sC,matrix[sR][sC][0]});
            matrix[sR][sC][1] = matrix[sR][sC][0];

            while (!priorityQueue.isEmpty()){
                int[] loc = priorityQueue.poll();
                if(loc[0] == N-1 && loc[1] == N-1)break;
                for(int i = 0 ; i < 4 ; i++){
                    int newR  = loc[0] + moving[i][0];
                    int newC = loc[1] + moving[i][1];

                    if(newR < 0 || newC < 0 || newR >= N || newC >= N || matrix[newR][newC][1] <= matrix[newR][newC][0] + loc[2] )continue;
                    matrix[newR][newC][1] = matrix[newR][newC][0] + loc[2];
                    priorityQueue.add(new int[]{newR, newC, matrix[newR][newC][1]});

                }
            }

            System.out.println("Problem "+ t++ +": "+matrix[N-1][N-1][1]);
        }

    }
}
