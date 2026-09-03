package jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class P15686 {
    public static int mi(int a, int b) {
        if (a > b) {
            return a - b;
        } else {
            return b - a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<int[]> h = new ArrayList<>();
        ArrayList<int[]> ch = new ArrayList<>();
        ArrayList<int[]> chToh = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < N; c++) {
                String s = st.nextToken();
                if (Objects.equals(s, "0")) continue;
                if (Objects.equals(s, "1")) {
                    h.add(new int[]{r, c});
                }
                if (Objects.equals(s, "2")) {
                    ch.add(new int[]{r, c});
                }
            }
        }
        for (int i = 0; i < ch.size(); i++) {
            chToh.add(new int[h.size()]);
            for (int j = 0; j < h.size(); j++) {
                int[] chWay = ch.get(i);
                int[] hWay = h.get(j);

                chToh.get(i)[j] = mi(chWay[0], hWay[0])+mi(chWay[1], hWay[1]);
            }
        }
        int answer = dfs( new ArrayList<Integer>(), 0, M, chToh, h.size());
        System.out.println(answer);

    }

    public static int dfs(ArrayList<Integer> selected, int start, int selectedSize, ArrayList<int[]> chToh, int hSize){
        if(selected.size() == selectedSize){
            int [] arr = new int[hSize];
            for(int i = 0; i <selectedSize; i++){
                int index = selected.get(i);
                int[] hCh = chToh.get(index);
                for(int j = 0 ;j<hSize;j++){
                    if(arr[j] == 0){
                        arr[j] = hCh[j];
                        continue;
                    }
                    arr[j] = Math.min(arr[j], hCh[j]);

                }
            }
            int total = 0;
            for(int j = 0 ;j<hSize;j++){
                total+= arr[j];
            }
            return total;
        }
        int answer = Integer.MAX_VALUE;
        for(int i = start; i <chToh.size(); i++){
            selected.add(i);

            answer = Math.min( dfs(selected, i+1, selectedSize, chToh, hSize), answer);
            selected.remove(selected.size()-1);
        }

        return answer;
    }
}
