package jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P5427 {

    static class  Node implements Comparable<Node> {

        int [] mat;
        int count;
        Node(int row, int col, int count){
            mat = new int[]{row,col};
            this.count = count;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(count, o.count);
        }
    }
    static  int [][] moving = new int[][]{{-1,0},{1,0}, {0,1},{0,-1}};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 0 ;t<T; t++) {


            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            int[][] matrix = new int[row][col];
            int startRow = 0;
            int startCol = 0;

            Queue<int[]> queueWater = new ArrayDeque<>();
            PriorityQueue<Node> queueMove = new PriorityQueue<>();
            for (int r = 0; r < row; r++) {
                char[] chars = br.readLine().toCharArray();
                for (int c = 0; c < col; c++) {
                    char ch = chars[c];
                    if (ch == '.') {
                        matrix[r][c] = Integer.MAX_VALUE;
                        continue;
                    }
                    if (ch == '#') {
                        matrix[r][c] = -2;
                        continue;
                    }
                    if (ch == '*') {
                        matrix[r][c] = -1;
                        queueWater.add(new int[]{r, c});
                        continue;
                    }

                    if (ch == '@') {
                        startRow = r;
                        startCol = c;
                        continue;
                    }
                }
            }
            queueMove.add(new Node(startRow, startCol, 0));
            int answer = -1;
            int moveSize = 0;
            boolean isEscape = false;
            while (!queueMove.isEmpty()) {
                if (moveSize == 0) {
                    moveSize = queueMove.size();
                    int wSize = queueWater.size();
                    for (int i = 0; i < wSize; i++) {
                        int[] waterLocate = queueWater.poll();
                        for (int j = 0; j < 4; j++) {
                            int newR = waterLocate[0] + moving[j][0];
                            int newC = waterLocate[1] + moving[j][1];

                            if (newR < 0 || newC < 0 || newR >= row || newC >= col || matrix[newR][newC] == -2 || matrix[newR][newC] == -1 )
                                continue;

                            queueWater.add(new int[]{newR, newC});
                            matrix[newR][newC] = -1;
                        }
                    }
                }

                Node node = queueMove.poll();
                moveSize--;
                int[] move = node.mat;

                for (int j = 0; j < 4; j++) {
                    int newR = move[0] + moving[j][0];
                    int newC = move[1] + moving[j][1];
                    if (newR < 0 || newC < 0 || newR >= row || newC >= col )
                    {
                        answer = node.count+1;
                        isEscape = true;
                        break;
                    }
                    if(node.count >= matrix[newR][newC])continue;

                    queueMove.add(new Node(newR, newC, node.count + 1));
                    matrix[newR][newC] = node.count + 1;
                }
                if(isEscape){
                    break;
                }
            }
            if (answer == -1) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(answer);
            }
        }
    }
}
