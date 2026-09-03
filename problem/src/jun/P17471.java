package jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17471 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt( br.readLine());
        int[] city = new int[N];
        int [][] cityNode = new int[N][];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0 ; i<N; i++){
            int a = Integer.parseInt( st.nextToken());

            city[i] = a;

        }
        for(int i = 0 ; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt( st.nextToken());
            int []roads = cityNode[i];
            for(int j = 0; j<a;j++){
                a = Integer.parseInt( st.nextToken());
                roads[j] = a;
            }
        }

        for(int i = 0 ; i<N; i++ ){

        }

    }

    public void dfs(int node,int count, int size, int sum, boolean check[], int [] city, int [][] cityNode){
        if(count == size-1){
            return;
        }
        check[node] = true;
        int[] nodes = cityNode[node];
        for(int i = 0; i < nodes.length;i++ ){
            dfs(node+1 , count+1, size, sum + );
        }
    }

}
