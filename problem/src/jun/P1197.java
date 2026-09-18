package jun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1197 {

    static class UnionFind{
        int[] p;
        int[] s;

        public UnionFind(int N){
             p = new int[N];
             s = new int[N];

             for(int i = 0 ; i < N; i++){
                 p[i] = i;
                 s[i] = 1;
             }
        }

        public int find(int x){
            if(p[x] != x){
                p[x] = find(p[x]);
            }

            return p[x];
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY)return;
            // 사이즈가 크면 작은 쪽으로 붙이기
            if(s[rootX] < s[rootY]){
                p[rootY] = rootX;
                s[rootX]+= s[rootY];
            } else if (s[rootX] > s[rootY]) {
                p[rootX] = rootY;
                s[rootY]+= s[rootX];
            }else{
                p[rootY] = rootX;
                s[rootX]+= s[rootY];
            }
        }

        public boolean isSameP(int x, int y){
            if(find(x) == find(y)){
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> Integer.compare(a[2],b[2]));
        for(int i = 0; i< E; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            q.add(new int[]{A,B,C});
        }
        UnionFind unionFind = new UnionFind(V+1);
        int c = 0;
        int vl = 0 ;
        while (!q.isEmpty()){
            int[] lo = q.poll();
            if(unionFind.isSameP(lo[0],lo[1]))continue;
            c+= lo[2];
            vl++;
            unionFind.union(lo[0],lo[1]);
            if(vl == (V-1))break;

        }

        System.out.println(c);
    }
}
