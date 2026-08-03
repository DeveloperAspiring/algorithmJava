package Programmers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class HIndex {

    static class Solution {
        public int solution(int[] citations) {
            int answer = 0;
            int count = 0;
            Arrays.sort(citations);
            int size = (int) Arrays.stream(citations).count();
            for(int i = size -1; i >=0 ; i --){
                count++;
                if(count <= citations[i]){
                    answer = Math.max(answer, count);
                }
            }

            return answer;
        }
    }

    public static void main(String[] args) throws IOException {
        int[] hIndex = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        Solution s = new Solution();
        s.solution(hIndex);
    }
}
