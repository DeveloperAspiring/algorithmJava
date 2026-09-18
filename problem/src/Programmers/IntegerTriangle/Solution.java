package Programmers.IntegerTriangle;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int size = triangle.length;

        for(int i = size -1; i > 0; i--){

            for(int j = 0 ; j <= i-1; j++){
                int a= triangle[i][j];
                int b= triangle[i][j+1];
                int m = Integer.max(a,b);
                triangle[i-1][j] += m;
            }
        }
        answer = triangle[0][0];

        return answer;
    }
}
