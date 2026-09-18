package Programmers.Hopscotch;


import java.util.ArrayList;
import java.util.TreeSet;

class Solution {

    int solution(int[][] land) {
        int answer = 0;
        int rowSize = land.length;

        int bforeC = -1;
        for(int i = 0; i< rowSize; i++){
            int max = 0;


            boolean[] flag = new boolean[4];
            int bforeC2 = -1;

            for(int c = 0 ; c < 4; c++){
                if(bforeC != -1 && bforeC ==c)continue;
                flag[c] = true;

                int m = subset(land,i+1,1,flag, land[i][c]);
                if(max  < m){
                    bforeC2 = c;
                    max = m;
                }

                flag[c] = false;
            }
            bforeC = bforeC2;
            answer+= land[i][bforeC];

        }



        return answer;
    }

    public int subset(int[][] land ,int startrow,int cnt,boolean [] visited,int tot){
        if(cnt == 3 || land.length <= startrow){
            return tot;
        }
        int m = 0;
        for(int i= 0; i<4; i++){
            if(visited[i])continue;
            //visited[i]=true;
            boolean[] a2 = new boolean[4];
            a2[i] = true;
            int a = subset(land,startrow+1,cnt+1, a2,tot+land[startrow][i] );
           // visited[i]=false;
            //int b =   subset(land,startrow,cnt, visited,tot);
            m = Math.max(a,m);
        }
        return m;
    }

//    public static void main(String[] args){
//        Solution s = new Solution();
//        s.solution(new int[][]{{1,2,3,5},{5,6,7,8},{4,3,2,1}});
//    }
}
