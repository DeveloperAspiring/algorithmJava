package jun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1717 {

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

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        UnionFind unionFind = new UnionFind(N+1);


        for(int i = 0 ; i<M;i++){
            boolean flag = true;
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            if(A == 0){
                unionFind.union(B,C);
            }else{
                if(!unionFind.isSameP(B,C)){
                    flag = false;
                }
                System.out.println(flag? "yes": "no");
            }
        }

    }
}
