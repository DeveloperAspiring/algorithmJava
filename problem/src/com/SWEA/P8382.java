import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P8382 {
    static  int[][] dr = new int[][]{{1,0},{-1,0}};
    static int[][] dc = new int[][]{{0,1},{0,-1}};

    public static void main(String agrs[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase<= T; testCase++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int x1,x2,y1,y2;

            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            y2 = y2 - y1;
            y2= Math.abs(y2);
            y1 = 0;
            x2 = x2 - x1;
            x2= Math.abs(x2);
            x1 = 0;

            boolean[][][] checker = new boolean[x2+2][y2+2][2];


            Queue<int[]> queue = new ArrayDeque<>();

            queue.add(new int[]{x1,y1,3, 0});
            checker[x1][y1][0] = true;
            checker[x1][y1][1] = true;

            int answer = -1;
            while (!queue.isEmpty()){
                int[] ways = queue.poll();

                if(ways[0] == x2 && ways[1] == y2){
                    if(answer == -1){
                        answer = ways[3];
                    }else{
                        answer = Math.min(answer, ways[3]);
                    }
                }

                if(ways[2] != 2){
                    for(int i = 0 ; i<2; i++){
                        int nR = ways[0]+dr[i][0];
                        int nC = ways[1]+dr[i][1];

                        if(nR <0 || nC <0|| nR >= x2+2 || nC >= y2+2 || checker[nR][nC][0]) continue;
                        checker[nR][nC][0] = true;
                        queue.add(new int[]{nR,nC,2, ways[3] +1});
                    }
                }
                if(ways[2] != 1){
                    for(int i = 0 ; i<2; i++){
                        int nR = ways[0]+dc[i][0];
                        int nC = ways[1]+dc[i][1];

                        if(nR <0 || nC <0|| nR >= x2+2 || nC >= y2+2 || checker[nR][nC][1]) continue;
                        checker[nR][nC][1] = true;
                        queue.add(new int[]{nR,nC,1, ways[3] +1});
                    }
                }

            }

            System.out.println("#" +testCase+" "+answer);
        }


    }

//    public static void bfs(int sR,int sC,int eR,int eC, boolean way){
//
//    }
}
