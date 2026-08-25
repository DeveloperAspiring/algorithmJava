package com.SWEA.EVCharger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class UserSolution {
    class Doro{
        int id, sCity,eCity,mDistance;
        int cost = Integer.MAX_VALUE;

        public Doro(int id,int sCity, int mDistance, int eCity) {
            this.id = id;
            this.sCity = sCity;
            this.mDistance = mDistance;
            this.eCity = eCity;
        }
    }
    class Node implements Comparable<Node> {
        int id;
        int cost;
        long sum;

        Node(int id, int cost, long sum) {
            this.id = id;
            this.cost = cost;
            this.sum = sum;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.sum, other.sum);
        }
    }

    //    N개의 도시가 주어진다. 각 도시는 0부터 N-1까지 ID값을 가진다.
//
//    N개의 단위 거리당 충전 비용이 mCost 배열로 주어진다.
//
//    K개의 도로 정보가 주어진다. 각 도로의 ID, 출발 도시와 도착 도시, 그리고 거리가 주어진다.
    static int N,K;
    static int[] mCost;
    //스타트 위치를 키값
     Map<Integer, ArrayList<Doro>> doroMapS = new HashMap<>();
    //id를 키값
     Map<Integer, Doro> doroMapId = new HashMap<>();
    public void init(int N, int mCost[], int K, int mId[], int sCity[], int eCity[], int mDistance[]) {
        UserSolution.N = N;
        UserSolution.mCost = mCost;
        UserSolution.K = K;
        doroMapS = new HashMap<>();
        doroMapId= new HashMap<>();
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
        if(doros.isEmpty()){
            doroMapS.remove(d.sCity);
        }
        return;
    }
//sCity에서 eCity로 가는데 필요한 최소 충전 비용을 반환한다.
//
//sCity와 eCity가 서로 같은 경우는 없다.
public int cost(int sCity, int eCity) {

    PriorityQueue<Node> queue = new PriorityQueue<>();

    ArrayList<ArrayList<Node>> states = new ArrayList<>();

    for (int i = 0; i < N; i++) {
        states.add(new ArrayList<>());
    }

    Node start = new Node(
            sCity,
            mCost[sCity],
            0
    );

    queue.add(start);
    states.get(sCity).add(start);

    while (!queue.isEmpty()) {

        Node n = queue.poll();

        if (n.id == eCity) {
            return (int)n.sum;
        }

        if (!doroMapS.containsKey(n.id)) {
            continue;
        }

        for (Doro doro : doroMapS.get(n.id)) {

            int nextCity = doro.eCity;

            long nextSum =
                    n.sum +
                            (long)n.cost * doro.mDistance;

            int nextCost =
                    Math.min(
                            n.cost,
                            mCost[nextCity]
                    );

            // 1. 새 상태가 기존 상태에 지배되는지 확인
            boolean dominated = false;

            for (Node old : states.get(nextCity)) {

                if (old.sum <= nextSum &&
                        old.cost <= nextCost) {

                    dominated = true;
                    break;
                }
            }

            if (dominated) {
                continue;
            }

            // 2. 새 상태가 기존 상태를 지배하면 삭제
            states.get(nextCity).removeIf(old ->
                    nextSum <= old.sum &&
                            nextCost <= old.cost
            );

            // 3. 새로운 상태 등록
            Node nextNode =
                    new Node(
                            nextCity,
                            nextCost,
                            nextSum
                    );

            states.get(nextCity).add(nextNode);
            queue.add(nextNode);
        }
    }

    return -1;
}
}
