package jun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


//17472 다리만들기 2
// mst, union find
public class P17472 {

    private static final int[][] drc = new int[][] {{-1,0},{0,1},{1,0},{0,-1}};

    static int R;
    static int C ;
    public static class UnionFind{
        int [] parents;
        int [] rank;

        UnionFind(int size){
            parents = new int[size];
            rank = new int[size];

            for(int i = 0 ; i < size; i++) {
                parents[i] = i;
                rank[i] = 1;
            }
        }
        public int find(int a) {
            if(a != parents[a]) {
                parents[a] = find(parents[a]);
            }

            return parents[a];
        }

        public void union(int a, int b) {
            int rA = find(a);
            int rB = find(b);

            if(rA == rB)return;

            if(rank[rA] < rank[rB]) {
                parents[rA] = rB;
                rank[rB] += rank[rA];
            }else {
                parents[rB] = rA;
                rank[rA] += rank[rB];
            }
        }
    }

    public static void main(String[] args)throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int matrix [][] = new int[R][C];
        boolean visited [][] = new boolean[R][C];
        UnionFind unionFind = new UnionFind(R*C);
        for(int r = 0 ; r< R ;r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int c = 0 ; c< C ;c++) {
                int el = Integer.parseInt(st.nextToken());
                matrix[r][c] = el;
            }
        }

        for(int r = 0 ; r< R ;r++) {
            for(int c = 0 ; c< C ;c++) {
                if(visited[r][c] || matrix[r][c] == 0)continue;
                Queue<int[]> q = new ArrayDeque<>();
                q.add(new int[] {r,c});
                visited[r][c] = true;

                while(!q.isEmpty()) {
                    int[] lo = q.poll();

                    for(int i = 0 ; i < 4; i++) {
                        int newR = lo[0] + drc[i][0];
                        int newC = lo[1] + drc[i][1];

                        if(newR < 0 || newR >= R || newC<0 || newC >= C || visited[newR][newC] || matrix[newR][newC] == 0)continue;
                        visited[newR][newC] = true;
                        q.add(new int[] {newR,newC});
                        unionFind.union(lo[0] * C + lo[1], newR * C + newC);

                    }
                }

            }
        }
        int V = 0 ;
        for(int r = 0 ; r< R ;r++) {
            for(int c = 0 ; c< C ;c++) {
                if(r*C + c == unionFind.find(r*C + c) && matrix[r][c] == 1)V++;

            }
        }
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a,b) -> Integer.compare(a[2], b[2]));
        for(int r = 0 ; r< R ;r++) {
            for(int c = 0 ; c< C ;c++) {
                if(matrix[r][c] == 0) continue;
                for(int i = 0 ; i < 4; i++) {
                    makeBridge(r,c,drc[i], matrix, priorityQueue);
                }

            }
        }
        int answer = 0 ;
        int count = 0 ;
        while(!priorityQueue.isEmpty()) {
            int[] info = priorityQueue.poll();
            if(unionFind.find(info[0]) == unionFind.find(info[1]))continue;
            unionFind.union(info[0], info[1]);
            answer += info[2];
            if(V -1 == ++count)break;

        }
        if(V -1 == count) {
            System.out.println(answer != 0? answer : -1);
        }else {
            System.out.println(-1);
        }
    }

    public static void makeBridge(int r, int c, int[] way, int[][] matrix, PriorityQueue<int[]> priorityQueue) {
        for(int i = 1 ; i <= 100; i++ ) {
            int newR = r + way[0]*i;
            int newC = c + way[1]*i;

            if(newR < 0 || newR >= R || newC<0 || newC >= C )break;
            if(matrix[newR][newC] == 1) {
                if(i >= 3) {
                    priorityQueue.add(new int[] {r*C + c, newR*C+newC, i-1});
                    break;
                }else {
                    break;
                }
            }

        }
    }

}
