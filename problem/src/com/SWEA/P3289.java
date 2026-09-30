package com.SWEA;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class P3289 {

    public static class UnionFind{
        int[] p;
        int[] r;

        public UnionFind(int size){
            p = new int[size+1];
            r = new int[size+1];

            for(int i = 1; i < size+1; i++){
                p[i] = i;
                r[i] = 1;
            }
        }

        public int find(int m){

            if(p[m] != m){
                p[m] = find(p[m]);
            }

            return p[m];
        }

        public void union(int a, int b){
            int aP = find(a);
            int bP = find(b);

            if(r[aP] > r[bP]){
                p[aP] = bP;
                r[bP] += r[aP];
            }else{
                p[bP] = aP;
                r[aP] += r[bP];
            }
        }
        public boolean isConnected(int a, int b){
           if(find(a) == find(b)){
               return true;
           }
           return false;
        }
    }

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase<=T; testCase++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            UnionFind unionFind = new UnionFind(n);
            String answer ="";
            for(int i = 0 ; i < m; i++){
                st = new StringTokenizer(br.readLine()," ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                if(a == 0){
                    unionFind.union(b,c);
                }else{
                    if(unionFind.isConnected(b,c)){
                        answer +="1";
                    }else{
                        answer +="0";
                    }
                }

            }

            System.out.println("#"+testCase+" "+answer);
        }
    }
}
