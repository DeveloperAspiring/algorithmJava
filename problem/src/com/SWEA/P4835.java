import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P4835 {

    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            int [] arr = new int[N];

            st = new StringTokenizer(br.readLine()," ");


            for(int i = 0 ; i< N; i++) {
                int a = Integer.parseInt(st.nextToken());
                arr[i] = a;
            }

            int sum = 0;
            int b = Integer.MAX_VALUE, t = 0;

            for(int i = 0 ; i < R; i++) {
                sum+= arr[i];
            }
            b = Math.min(sum, b);
            t = Math.max(sum, t);
            for(int i = R ; i < N; i++) {
                sum += arr[i] - arr[i - R];

                b = Math.min(sum, b);
                t = Math.max(sum, t);
            }

            System.out.println("#"+testCase+" "+(t-b));
        }
    }

}
