package jun;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

public class P2247 {
    char[][] arr = {{'*', '*', '*'}, {'*', ' ', '*'}, {'*', '*', '*'}};
    HashMap<Character, int[]> map = new HashMap<>();
    static HashSet<Integer> set = new HashSet<>();
    public static void main(String[] args) throws IOException {

        Integer[] arr = set.toArray(new Integer[0]);
//        InputStream in = System.in;
//        int t = in.read();
        int t = 729;
        char[][] cArr = make(t);


        for (int i = 0; i < t; i++) {
            for (int j = 0; j < t; j++) {
                System.out.print(cArr[i][j]);
            }
            System.out.println();
        }


    }

    static char[][] make(int c) {
        if(c == 1){
            return new char[][]{{'*'}};
        }
        char[][] old = make(c/3);
        char[][] newArr = new char[c][c];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                //중심조건
                if( i < c/3 || i >= c*2/3 || j < c/3 || j >= c*2/3){
                    int r = i%(c/3);
                    int co = j%(c/3);
                    newArr[i][j] = old[r][co];
                }
            }
        }
        return newArr;
    }
}
