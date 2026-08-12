package Programmers;

import java.util.ArrayDeque;
import java.util.Queue;

public class Network {
    class Solution {
        public static int solution(int n, int[][] computers) {
            int answer = 0;
            boolean[] checker = new boolean[n];
            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                if (checker[i]) continue;
                checker[i] = true;
                q.add(i);
                while (!q.isEmpty()) {
                    int index = q.poll();
                    for (int i2 = 0; i2 < n; i2++) {
                        if (checker[i2] || computers[index][i2] == 0) continue;
                        checker[i2] = true;
                        q.add(i2);
                    }
                }
                answer++;
            }
            System.out.println(answer);
            return answer;
        }
    }

    public static void main(String[] args) {

        // Solution.solution(3,new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});


        Solution.solution(3, new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}});

    }
}
