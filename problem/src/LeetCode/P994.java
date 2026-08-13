package LeetCode;

import java.util.*;

public class P994 {
    final static int[][] rot = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    static class Orange{
        int row,col;
        public Orange(int row, int col){
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Orange){
               if(row == ((Orange) obj).row && col == ((Orange) obj).col){
                   return true;
               }
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row+","+col);
        }
    }
    static class Solution {
        public int orangesRotting(int[][] grid) {
            Orange cOrange = new Orange(-1, -1);
            int m = grid.length;
            int n = grid[0].length;
            Set<Orange> o1 = new HashSet<>();
            List<Orange> o2 = new ArrayList<>();

            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    if (grid[row][col] == 2) {
                        o2.add(new Orange(row,col));
                        continue;
                    }
                    if (grid[row][col] == 1) {
                        o1.add(new Orange(row,col));
                    }
                }
            }
            int count = 0;
            int size = -1;
            int index = 0;
            while (!o1.isEmpty()) {
                int o2Size = o2.size();
                for (; index < o2Size; index++) {
                    Orange or = o2.get(index);
                    int row = or.row;
                    int col = or.col;
                    for (int i = 0; i < 4; i++) {
                        cOrange.row = row+ rot[i][0];
                        cOrange.col = col+ rot[i][1];
                        if(!o1.contains(cOrange))continue;
                        o2.add(new Orange(cOrange.row,cOrange.col));
                        o1.remove(cOrange);
                    }
                }


                //if (o1.isEmpty()) break;
                if(size == o1.size()){
                    count= -1;
                    break;
                }

                count++;
                size= o1.size();
            }

            System.out.println(count);
            return count;
        }
    }

    public static void main(String[] args) {

        // int[][] grid = {{1}};
       //int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        //int[][] grid = {{2,1,1},{0,1,1},{1,0,1}};
        int[][] grid = {{2,1,0},{0,0,0},{0,1,1}};

       Solution s = new Solution();
       s.orangesRotting(grid);
    }
}
