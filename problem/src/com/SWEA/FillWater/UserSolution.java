package FillWater;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

class UserSolution {

    private class Node implements Comparable<Node> {

        int height = 0;
        int water = 0;
        int index = 0;
        public Node(int height, int index){
            this.height = height;
            this.index = index;
        }
        @Override
        public int compareTo(Node other) {
            int myTotal = this.height + this.water;
            int otherTotal = other.height + other.water; // 오타 수정: o2.height + o2.height -> other.water

            if (myTotal != otherTotal) {
                // 기존 로직(others, me)에 따른 내림차순 정렬
                return Integer.compare(myTotal, otherTotal);
            } else {
                // 물이 있는기준으로
                return Integer.compare(other.water, this.water);
            }
        }
    }

//    _____N : 어항의 개수 (5 ≤ N ≤ 20)
//
//    _____mWidth : 어항의 가로 길이 (5 ≤ mWidth ≤ 500)
//    _____mHeight : 어항의 세로 길이 (10 ≤ mHeight ≤ 1,500)
//    _____mIDs[] : 어항의 ID (1 ≤ mIDs[] ≤ 1,000,000)
//    ____ mLengths[][] : 각 어항 별, 열 별 설치된 구조물의 높이 (1 ≤ mLengths[][] ≤ mHeight)
//    ____ mUpShapes[][] : 각 어항 별, 열 별 설치된 구조물의 위쪽 결합판의 종류 (0 ≤ mUpShapes[][] ≤ 3)
    int N;
    int mWidth;
    int mHeight;
    int mIDs[];
    int mLengths[][];
    int mUpShapes[][];
    TreeMap<Integer, Integer> map = new TreeMap<>();
    public void init(int N, int mWidth, int mHeight, int mIDs[], int mLengths[][], int mUpShapes[][]) {
        this.N = N;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        this.mIDs = mIDs;
        this.mLengths = mLengths;
        this.mUpShapes = mUpShapes;
        map = new TreeMap<>();
        for(int i = 0 ; i < N; i++){
            map.put(mIDs[i], i);
        }
    }

//    1. 어항의 ID가 작을수록 우선순위가 높다.
//    2. 같은 어항내에서는 왼쪽으로 갈수록 우선순위가 더 높다.
    public int checkStructures(int mLengths[], int mUpShapes[], int mDownShapes[]) {
        int count  = 0;
        for(Integer key : map.keySet()){
            int Lengths[] = this.mLengths[map.get(key)];
            int UpShapes[] = this.mUpShapes[map.get(key)];
            for(int i = 0 ; i < mWidth-2; i++){
                if(mDownShapes[0] == UpShapes[i] && Lengths[i] + mLengths[0]<= mHeight ){
                    if(fillChcker(mLengths, mDownShapes, Lengths, UpShapes, i+1, 1))count++;
                }
            }
        }
        return count;
    }

    public int addStructures(int mLengths[], int mUpShapes[], int mDownShapes[]) {
        for(Integer key : map.keySet()){
            int Lengths[] = this.mLengths[map.get(key)];
            int UpShapes[] = this.mUpShapes[map.get(key)];
            for(int i = 0 ; i < mWidth-2; i++){
                if(mDownShapes[0] == UpShapes[i] && Lengths[i] + mLengths[0]<= mHeight ){
                    if(fill(mLengths, mDownShapes,mUpShapes, Lengths, UpShapes, i+1, 1)){

                        UpShapes[i] = mUpShapes[0];
                        Lengths[i] += mLengths[0];

                        int answer = key * 1000 + i+1;
                        return answer;
                    }
                }
            }
        }
        return 0;
    }

    boolean fillChcker(int mLengths[], int mDownShapes[],int Lengths[],int UpShapes[],int start, int index){
        if(index == 3)return true;
        if(start>= mWidth)return false;
        boolean checker = false;
        if(mDownShapes[index] == UpShapes[start] && Lengths[start] + mLengths[index] <= mHeight && Lengths[start] < Lengths[start-1] + mLengths[index - 1] && Lengths[start] + mLengths[index] > Lengths[start-1]){
            checker =  fillChcker(mLengths, mDownShapes, Lengths, UpShapes, start+1, index+1);
        }
        return checker;
    }


    boolean fill(int mLengths[], int mDownShapes[],int mUpShapes[],int Lengths[],int UpShapes[],int start, int index){
        if(index == 3)return true;
        if(start>= mWidth)return false;
        boolean checker = false;
        if(mDownShapes[index] == UpShapes[start] && Lengths[start] + mLengths[index] <= mHeight && Lengths[start] < Lengths[start-1] + mLengths[index - 1] && Lengths[start] + mLengths[index] > Lengths[start-1]){
            checker =  fill(mLengths, mDownShapes,mUpShapes, Lengths, UpShapes, start+1, index+1);
        }
        if(checker){
            UpShapes[start] = mUpShapes[index];
            Lengths[start] += mLengths[index];
        }
        return checker;
    }

    int[] calculateWater(int[] lengths, int mWater) {

        // 실제 유효한 mWidth개만 복사
        int[] arr = java.util.Arrays.copyOf(lengths, mWidth);
        java.util.Arrays.sort(arr);

        int height = arr[0];
        int count = 1;
        int used = 0;
        int remain = mWater;

        for (int i = 1; i < mWidth; i++) {

            // 같은 높이의 열 추가
            if (arr[i] == height) {
                count++;
                continue;
            }

            int nextHeight = arr[i];

            // 현재 낮은 count개를 nextHeight까지 올리는 물의 양
            int need = (nextHeight - height) * count;

            if (remain < need) {

                int rise = remain / count;

                height += rise;
                used += rise * count;

                return new int[]{height, used};
            }

            remain -= need;
            used += need;

            height = nextHeight;
            count++;
        }

        // 모든 열이 같은 높이가 된 후
        int rise = remain / count;

        rise = Math.min(rise, mHeight - height);

        height += rise;
        used += rise * count;

        return new int[]{height, used};
    }
    //우선순위 큐 사용해서 해결
    public Solution.Result pourIn(int mWater) {

        Solution.Result ret = new Solution.Result();
        ret.ID = ret.height = ret.used = 0;

        for (Integer key : map.keySet()) {

            int index = map.get(key);

            int[] result = calculateWater(mLengths[index], mWater);

            int height = result[0];
            int used = result[1];

            // 문제 조건: 물을 최소 1 이상 사용해야 함
            if (used == 0) {
                continue;
            }

            // 1. 최대 높이가 높은 어항
            if (height > ret.height) {

                ret.ID = key;
                ret.height = height;
                ret.used = used;
            }

            // 2. 높이가 같으면 사용한 물이 많은 어항
            else if (height == ret.height && used > ret.used) {

                ret.ID = key;
                ret.used = used;
            }

            // 3. height, used가 같으면 ID 작은 것
            // TreeMap을 오름차순으로 돌기 때문에 별도 처리 필요 없음
        }

        return ret;
    }
}
