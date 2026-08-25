package com.SWEA.GlacialMotion;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;

class UserSolution {

    int[][] dxy = {{-1,0},{1,0},{0,-1},{0,1}};
    int[][] moveArr = {{0,-1},{1,0},{0,1},{-1,0}};
    private final static int MAX_N = 100;
    class RESULT {
        int[][] heights;
        RESULT() {
            heights = new int[MAX_N][MAX_N];
        }
    }
    int N;
    int M;
    int mIceBlock[][];
    int mIceGroup[][];
    int mIceVolume[][];
//    N : 전체 바다의 가로, 세로 길이 (5 ≤ N ≤ 100)
//    M : 빙하의 개수 (1 ≤ M ≤ 5,000)
//    mIceBlock[][] : 각 좌표에 존재하는 얼음덩어리 “높이”
//    mIceGroup[][] : 각 빙하를 구성하고 있는 얼음덩어리 1 개 에 대한 X, Y 좌표 및 빙하의 이동 방향
    //그 빙하가 움직이는 방향 ( 0 : ↑, 1 : →, 2 : ↓, 3 : ←) 을 의미한다.
    void init(int N, int M, int mIceBlock[][], int mIceGroup[][]) {
        this.N = N;
        this.M = M;
        this.mIceBlock = mIceBlock;
        this.mIceGroup = mIceGroup;
        this.mIceVolume = new int[M][1];
    }
// 움직이고 병합하고
    RESULT oneYearLater() {
        RESULT res = new RESULT();
        //print();
        int newmIceBlock[][][] = new int[N][N][2];
        //봄륨과 필드크기를 가지고 있음
        int[][] volumeAndField= new int[M][2];
        for(int i=0; i< M; i++){


             volumeAndField[i] = dfsMove(mIceGroup[i][0],mIceGroup[i][1],mIceGroup[i][2], new int[]{0, 0}, newmIceBlock,i );

            int x = mIceGroup[i][0];
            int y = mIceGroup[i][1];

            int newX = (x+moveArr[mIceGroup[i][2]][0])%N;
            int newY = (y+moveArr[mIceGroup[i][2]][1])%N;
            //음수 정상화
            newX = newX <0 ? N+newX : newX;
            newY = newY <0 ? N+newY : newY;
            mIceGroup[i][0] = newX;
            mIceGroup[i][1] = newY;
            //mIceVolume[i][0] = volume;
        }
        ArrayList<int[]> arrayList  = new ArrayList<>();
        int size = 0;
        for(int row=0; row< N; row++){
            for(int col=0; col< N; col++) {
                Queue<int[]> q = new ArrayDeque<>();
                if (newmIceBlock[row][col][0] == 0) continue;
                arrayList.add(new int[]{col, row, newmIceBlock[row][col][1]});
                size++;
                q.add(new int[]{col, row});

                while (!q.isEmpty()) {
                    int[] ar = q.poll();
                    int x = ar[0];
                    int y = ar[1];
                    int here = newmIceBlock[y][x][0];
                    if(here == 0)continue;
                    mIceBlock[y][x] = here;
                    newmIceBlock[y][x][0] = 0;

                    int index = newmIceBlock[y][x][1];
                    int oldIndex = arrayList.get(size - 1)[2];
                    if (index != oldIndex && isBetter(index, oldIndex, volumeAndField)) {
                        arrayList.get(size - 1)[2] = index;
                    }

                    for (int in = 0; in < 4; in++) {
                        //N 넘어가는거 정상화
                        int newX = (x + dxy[in][0]) % N;
                        int newY = (y + dxy[in][1]) % N;
                        //음수 정상화
                        newX = newX < 0 ? N + newX : newX;
                        newY = newY < 0 ? N + newY : newY;
                        if (newmIceBlock[newY][newX][0] == 0) {
                            continue;
                        }
                        q.add(new int[]{newX, newY});
                    }
                }
            }
        }
        M = arrayList.size();
        for(int i = 0 ; i< M;i++){
            arrayList.get(i)[2] = mIceGroup[arrayList.get(i)[2]][2];
        }

        for(int i = 0 ; i< M;i++){
            mIceGroup[i][2] =arrayList.get(i)[2];
            mIceGroup[i][1] =arrayList.get(i)[1];
            mIceGroup[i][0] =arrayList.get(i)[0];
        }
       // System.out.println(arrayList.size());
       // print1(newmIceBlock);
       // print(newmIceBlock);

        res.heights = mIceBlock;
        print(res.heights);
        return res;
    }

    // 움직이는 함수
    int[] dfsMove(int x, int y, int move, int[] volumeAndField, int [][][]newmIceBlock, int index){
        int here = mIceBlock[y][x];
        mIceBlock[y][x] = -1;
        boolean isSide = false;
        for(int i = 0; i < 4; i++){
            //N 넘어가는거 정상화
            int newX = (x+dxy[i][0])%N;
            int newY = (y+dxy[i][1])%N;
            //음수 정상화
            newX = newX <0 ? N+newX : newX;
            newY = newY <0 ? N+newY : newY;
            if(mIceBlock[newY][newX] == 0){
                isSide = true;
                break;
            }
        }
        if(isSide)here = here-1;
        for(int i = 0; i < 4; i++){
            //N 넘어가는거 정상화
            int newX = (x+dxy[i][0])%N;
            int newY = (y+dxy[i][1])%N;
            //음수 정상화
            newX = newX <0 ? N+newX : newX;
            newY = newY <0 ? N+newY : newY;
            if(mIceBlock[newY][newX] == 0 || mIceBlock[newY][newX] == -1){
                //isSide = true;
                continue;
            }

            volumeAndField = dfsMove(newX, newY, move, volumeAndField,newmIceBlock,index);
        }
        mIceBlock[y][x] = 0;
        if(here <= 0)return volumeAndField;
        int newX = (x+moveArr[move][0])%N;
        int newY = (y+moveArr[move][1])%N;
        newX = newX <0 ? N+newX : newX;
        newY = newY <0 ? N+newY : newY;
        volumeAndField[0] += here;
        volumeAndField[1] += 1;
        newmIceBlock[newY][newX][0] = Math.max(newmIceBlock[newY][newX][0], here);
        newmIceBlock[newY][newX][1] = index;
        return volumeAndField;
    }

    boolean isBetter(int a, int b, int[][] volumeAndField) {

        // 1. 부피가 큰 빙하
        if (volumeAndField[a][0] != volumeAndField[b][0])
            return volumeAndField[a][0] > volumeAndField[b][0];

        // 2. 부피가 같으면 면적이 작은 빙하
        if (volumeAndField[a][1] != volumeAndField[b][1])
            return volumeAndField[a][1] < volumeAndField[b][1];

        // 3. 면적도 같으면 Y가 작은 빙하
        if (mIceGroup[a][1] != mIceGroup[b][1])
            return mIceGroup[a][1] < mIceGroup[b][1];

        // 4. Y도 같으면 X가 작은 빙하
        return mIceGroup[a][0] < mIceGroup[b][0];
    }

    void print(){
        System.out.println("\u001B[32m");
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(mIceBlock[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("-------------------------------------"+"\u001B[0m");
    }

    void print(int [][] newmIceBlock){
        System.out.println("\u001B[32m");
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(newmIceBlock[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("-------------------------------------"+"\u001B[0m");
    }

    void print(int [][][]newmIceBlock){
        System.out.println("\u001B[31m");
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(newmIceBlock[i][j][0]+" ");
            }
            System.out.println();
        }

        System.out.println("-------------------------------------"+"\u001B[0m");
       // System.out.println("\u001B[0m");
    }

    void print1(int [][][]newmIceBlock){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(newmIceBlock[i][j][1]+" ");
            }
            System.out.println();
        }

        System.out.println("-------------------------------------");
    }
}

