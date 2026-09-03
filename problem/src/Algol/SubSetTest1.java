package Algol;

import java.util.Arrays;

public class SubSetTest1 {

    static int[] p= {1,2,3,4,5};
    static int N;
    static boolean[] visited;
    static int count;

    public static void main(String[] args) {

        N=p.length;
        visited=new boolean[N];
        count=0;
        subset(0,0, 1);
        System.out.println(count);

    }

    static void subset(int cnt, int tot, int mul) {
        if(cnt==N) {
            for (int i = 0; i < N; i++) {
                if(visited[i]) {
                    System.out.print(p[i]+" ");
                }
            }
            System.out.println();
            System.out.println(tot+"  "+mul);
            System.out.println("---------------------------");
            count++;
            return ;
        }
        visited[cnt]=true;
        subset(cnt+1, tot+p[cnt], mul*p[cnt]);
        visited[cnt]=false;
        subset(cnt+1, tot, mul);
    }

}
