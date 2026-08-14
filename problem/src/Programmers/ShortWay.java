package Programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortWay {

    final static int[][] rot = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    class Solution {
        public static int solution(int[][] maps) {
            int answer = 1;

            int N = maps.length;
            int M = maps[0].length;

            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{0,0});
            maps[0][0] = 0;
            boolean isFind = false;
            int count = 1;
            while (!queue.isEmpty()){
                int[] arr = queue.poll();
                count--;
                if(arr[0] ==N-1 && arr[1] ==M-1){
                    isFind = true;
                    break;
                }
                for(int i = 0; i< 4 ; i++){
                    int nr = arr[0] + rot[i][0];
                    int nc = arr[1] + rot[i][1];
                    if(nr <0 || nr >= N ||nc <0 || nc >= M || maps[nr][nc] == 0)continue;
                    maps[nr][nc] = 0;
                    queue.add(new int[]{nr,nc});
                }
                if(count == 0){
                    answer++;
                    count = queue.size();
                }
            }
            if(!isFind){
                answer = -1;
            }
            return answer;
        }
    }
    private static void print(int[][] maps){
        for(int[]col : maps){
            for(int i : col){
                System.out.print(i+" ");
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }
    public static void main(String[] args){

        int [][] arr;

        //arr = new int[][]{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};


        arr = new int[][]{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}};

        
        Solution.solution(arr);
    }
}
