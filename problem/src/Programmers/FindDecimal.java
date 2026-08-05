package Programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class FindDecimal {
    static boolean[] visited;
    static HashSet<Integer> h = new HashSet<>(20000);
    static int R;
    static long count = 0;
    private static void perm(int depth,char[] p, String nums){
        int a = Integer.parseInt(String.valueOf(nums));
        //System.out.println(a);
        if(a >1 && !h.contains(a)){
            h.add(a);
            count++;
            boolean a1 = true;
            for(int i =2; i<=Math.sqrt(a); i++){
                if(a%i == 0){
                    a1 = false;
                    count--;
                    break;
                }
            }
            if(a1){
                System.out.println(a);
            }
        }
        if(R == depth){
            return;
        }
        for(int i = 0; i<R; i++){
            if(visited[i]) continue;
            visited[i] = true;
            perm(depth+1, p,nums+p[i]);
            visited[i] = false;
            //nums[depth] = 0;
        }
    }



    public static int solution(String numbers) {
        count = 0;
        char[] chars = numbers.toCharArray();
        int size = chars.length;
        visited = new boolean[size];
        R =size;
        perm(0, chars,"0");
        int answer = 0;
        System.out.println(count);
        return answer;
    }

    public static void main(String[] args){
        solution("9999999");
    }

}
