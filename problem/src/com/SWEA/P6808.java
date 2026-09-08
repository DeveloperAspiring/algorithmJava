import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P6808 {
    static int winCnt = 0;
    static int loseCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            int drow=0;
            int mask = (1<<19) -1;
            int enemyDrow = 0;
            for(int i = 0 ; i< 9; i++) {
                int a = Integer.parseInt(st.nextToken());
                drow |= (1 << a);
            }
            enemyDrow = (~drow) & mask;
            winCnt = 0;
            loseCnt = 0;
        }
    }
    static void perm(int cnt, int myFlag, int enemyFlag, int myScore, int enemyScore){
        if(myFlag == enemyFlag){
            if(myScore > enemyScore){
                ++winCnt;
            }
            else if(myScore < enemyScore ){
                ++loseCnt;
            }
        }

        for(int i = 1 ; i < 18; i++){
            if((enemyFlag & (1<<i)) != 1)continue;


        }
    }

//    static int getNextCnt(int cnt, int flag){
//
//    }
}
