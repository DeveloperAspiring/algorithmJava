package com.SWEA.EVCharger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class UserSolution {
    class Doro{
        int id, sCity,eCity,mDistance;

        public Doro(int id,int sCity, int mDistance, int eCity) {
            this.id = id;
            this.sCity = sCity;
            this.mDistance = mDistance;
            this.eCity = eCity;
        }
    }

//    N개의 도시가 주어진다. 각 도시는 0부터 N-1까지 ID값을 가진다.
//
//    N개의 단위 거리당 충전 비용이 mCost 배열로 주어진다.
//
//    K개의 도로 정보가 주어진다. 각 도로의 ID, 출발 도시와 도착 도시, 그리고 거리가 주어진다.
    static int total = Integer.MAX_VALUE;
    static int N,K;
    static int[] mCost;
    //스타트 위치를 키값
    static Map<Integer, ArrayList<Doro>> doroMapS = new HashMap<>();
    //id를 키값
    static Map<Integer, Doro> doroMapId = new HashMap<>();
    public void init(int N, int mCost[], int K, int mId[], int sCity[], int eCity[], int mDistance[]) {
        UserSolution.N = N;
        UserSolution.mCost = mCost;
        UserSolution.K = K;
        for(int i = 0 ; i < K; i++){
            if(!doroMapS.containsKey(sCity[i])){
                doroMapS.put(sCity[i], new ArrayList<Doro>());
            }
            doroMapS.get(sCity[i]).add(new Doro(mId[i],sCity[i],mDistance[i],eCity[i]));
            doroMapId.put(mId[i], new Doro(mId[i],sCity[i],mDistance[i],eCity[i]));
        }
        
        return;
    }

//    단방향 도로이기 때문에 출발 도시에서 도착 도시로만 갈 수 있다.
//
//    init()에 없던 새로운 도시는 주어지지 않는다.
//
//    sCity에서 eCity로 가는 도로가 이미 존재하는 경우는 없다.
//
//    sCity와 eCity가 서로 같은 경우는 없다.
//
//    mId 값으로 이미 존재하는 도로의 ID가 주어지는 경우는 없다.
//
//
//
//            Parameters
//
//    mId: 도로의 ID ( 1 ≤ mId ≤ 1,000,000,000 )
//
//    sCity: 도로의 출발 도시 ( 0 ≤ sCity < N )
//
//    eCity: 도로의 도착 도시 ( 0 ≤ eCity < N )
//
//    mDistance: 도로의 거리 ( 1 ≤ mDistance ≤ 2,000 )
    public void add(int mId, int sCity, int eCity, int mDistance) {
        doroMapId.put(mId, new Doro(mId,sCity,mDistance,eCity));
        if(!doroMapS.containsKey(sCity))doroMapS.put(sCity, new ArrayList<Doro>());

        doroMapS.get(sCity).add( new Doro(mId,sCity,mDistance,eCity));
        return;
    }

//    mId 도로를 제거한다.
//
//    존재하지 않는 도로의 ID가 주어지는 경우는 없다.
    public void remove(int mId) {
        Doro d = doroMapId.get(mId);
        doroMapId.remove(mId);
        ArrayList<Doro> doros = doroMapS.get(d.sCity);
        for(int i = 0; i <doros.size();i++){
            if(doros.get(i).id == mId){
                doros.remove(i);
            }
        }
        if(doros.size() == 0){
            doroMapS.remove(d.sCity);
        }
        return;
    }
//sCity에서 eCity로 가는데 필요한 최소 충전 비용을 반환한다.
//
//sCity와 eCity가 서로 같은 경우는 없다.
    public int cost(int sCity, int eCity) {
        total = Integer.MAX_VALUE;
        boolean[] checker = new boolean[N];
        bfs1(eCity,sCity,0,0, checker);
        System.out.println(total);
        return total;
    }
    //end to start
    public void bfs1(int end, int start, int sum,int sumDistance, boolean[] checker){
        if(end == start){
            total = Math.min(sum, total);
            return;
        }
        if(total < sum)return;
        for(Doro doro : doroMap.values()){
            //end 도로 체크
            if(end != doro.eCity)continue;
            if(checker[end])continue;

            // 가격 비교
            checker[end] = true;
            if(sum + doro.mDistance* mCost[doro.sCity] < mCost[doro.sCity] * (sumDistance + doro.mDistance)){
                bfs1(doro.sCity, start, sum + doro.mDistance* mCost[doro.sCity], sumDistance +doro.mDistance,checker);
                checker[end] = false;
            }else{
                bfs1(doro.sCity, start, mCost[doro.sCity] * (sumDistance + doro.mDistance), sumDistance +doro.mDistance,checker);
                checker[end] = false;
            }
        }
    }
}
