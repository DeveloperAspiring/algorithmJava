import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1767 {
    static int[][] move = new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
    static int sumCore = 0;
    static int laneE = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){
            int N = Integer.parseInt(br.readLine());

            int [][] matrix = new int[N][N];


            ArrayList<int[]> arr = new ArrayList<>();

            for(int row = 0 ; row <N; row++){
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for(int col = 0 ; col <N; col++){
                    int a = Integer.parseInt(st.nextToken());
                    matrix[row][col] = a;
                    if(row <= 0 || col <= 0 || row >= N-1 || col >= N-1 || a == 0)continue;

                    arr.add(new int[]{row,col});
                }
            }
            sumCore = 0;
            laneE = Integer.MAX_VALUE;
            dfs(matrix,arr,0,arr.size(),0,0,0);

            System.out.println("#"+t+" "+laneE);

        }
    }

    public static void dfs(int [][] matrix,ArrayList<int[]> arr ,int cnt, int arrSize, int checker, int laneCount, int coreC){

        if(cnt == arrSize){
           if(coreC > sumCore){
               sumCore = coreC;
               laneE  = laneCount;

           }
           if(coreC == sumCore && laneCount < laneE){
               laneE = laneCount;
           }
            return;
        }

        if(coreC + arrSize - cnt < sumCore ){
            return;
        }
        for(int i = checker; i<arrSize;i++ ){
            //if((checker & (1 << i)) == (1 << i))continue;

            int[] loc = arr.get(i);

            for(int j = 0 ; j< 4; j++){
                int[] m = move[j];
                int c = moving(m,new int[]{loc[0], loc[1]}, matrix);
                c= c ==0 ? 0: c-1;
               // dfs(matrix,arr,cnt+1, arrSize,checker | (1 << i),laneCount+c, c == 0 ? coreC : coreC +1);
                dfs(matrix,arr,cnt+1, arrSize,i+1,laneCount+c, c == 0 ? coreC : coreC +1);

                for(int w = 1 ; w <= c;w++){
                    int row =loc[0];
                    int col =loc[1];
                    int newR = row + w*m[0];
                    int newC = col + w*m[1];


                    matrix[newR][newC] =0;

                }


            }
        }
    }

    public static int moving(int[] m, int[] loc, int [][] matrix){
        int row =loc[0];
        int col =loc[1];
        int newR = row + m[0];
        int newC = col + m[1];
        if(newR <0 || newC <0 || newR>= matrix.length || newC >= matrix.length )return 1;
        if(matrix[newR][newC] == 1)return 0;

        matrix[newR][newC] = 1;
        int flag = moving(m, new int[]{newR, newC}, matrix);
        if(flag == 0){
            matrix[newR][newC] = 0;
            return 0;
        }else{
            return flag+1;
        }
    }
}
