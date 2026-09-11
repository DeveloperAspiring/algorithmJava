import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

//빠삥 지뢰 찾기
//지뢰가 없는 곳(8방향으로) 탐색 발견시 bfs
public class P1868 {

    static int [][] mm = new int[][] {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};
    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());


        for(int t = 1; t<= T;t++) {
            int N = Integer.parseInt(br.readLine());

            int [][] mat = new int[N][N];

            int answer = 0;
            for(int r = 0 ; r< N;r++) {
                char[] chs = br.readLine().toCharArray();
                for(int c = 0 ; c< N;c++) {
                    char cr = chs[c];
                    if(cr == '.') {
                        answer++;
                        mat[r][c] = 1;
                    }else {

                    }
                }
            }
            for(int r = 0 ; r< N;r++) {
                for(int c = 0 ; c< N;c++) {
                    if( mat[r][c] == 0 || mat[r][c] == 2)continue;
                    boolean isClean = true;

                    for(int i = 0 ;i<8; i++) {
                        int newR = r + mm[i][0];
                        int newC = c + mm[i][1];

                        if(newR <0 || newC <0 || newR>=N || newC>= N )continue;


                        if(mat[newR][newC] == 0) {
                            isClean = false;
                            break;
                        }
                    }
                    // 8방향이 클린하면 bfs 탐색
                    if(isClean) {
                        Queue<int[]> q = new ArrayDeque<>();

                        q.add(new int[] {r,c});

                        mat[r][c] = 2;
                        while(!q.isEmpty()) {
                            int[] way = q.poll();

                            boolean isClean2 = true;
                            for(int i = 0 ;i<8; i++) {
                                int newR = way[0] + mm[i][0];
                                int newC = way[1] + mm[i][1];

                                if(newR <0 || newC <0 || newR>=N || newC>= N)continue;
                                if(mat[newR][newC] == 0) {
                                    isClean2 = false;
                                    break;
                                }

                            }

                            if(isClean2) {
                                for(int i = 0 ;i<8; i++) {
                                    int newR = way[0] + mm[i][0];
                                    int newC = way[1] + mm[i][1];

                                    if(newR <0 || newC <0 || newR>=N || newC>= N || mat[newR][newC] == 2|| mat[newR][newC] == 0)continue;
                                    answer--;
                                    mat[newR][newC] = 2;
                                    q.add(new int[] {newR,newC});
                                }
                            }
                        }
                    }
                }
            }

            System.out.println("#"+t+" "+answer);

        }
    }

}

