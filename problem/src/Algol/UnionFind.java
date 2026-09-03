package Algol;

public class UnionFind {
    private int[] parent;
    private int[] rank;

    // 1. 초기화: 각 노드는 자기 자신을 부모(루트)로 가집니다.
    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i; // 처음에는 모든 노드가 독립된 집합의 루트입니다.
            rank[i] = 0;   // 트리의 높이(Rank)를 0으로 초기화합니다.
        }
    }

    // 2. Find 연산: 노드 x가 속한 집합의 루트 노드를 찾습니다.
    public int find(int x) {
        // 자기 자신이 루트가 아니라면, 부모 노드를 찾아 올라갑니다.
        if (parent[x] != x) {
            // [경로 압축] 루트를 찾는 과정에서 만나는 모든 노드의 부모를 최상위 루트로 직접 연결합니다.
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // 3. Union 연산: 노드 x가 속한 집합과 노드 y가 속한 집합을 합칩니다.
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        // 이미 같은 집합에 속해 있다면 합칠 필요가 없습니다.
        if (rootX == rootY) {
            return;
        }

        // [Union by Rank] 트리의 높이가 더 낮은 쪽을 높은 쪽 밑에 붙입니다.
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            // 두 트리의 높이가 같다면 한쪽을 부모로 정하고 그쪽의 높이(Rank)를 1 늘립니다.
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    // 4. 두 노드가 같은 집합에 속해 있는지 확인하는 편의 메서드입니다.
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    // 테스트 실행을 위한 메인 메서드
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(6); // 0번부터 5번까지 6개의 노드 생성

        // 노드 연결 실행
        uf.union(1, 2); // 1과 2 연결
        uf.union(3, 4); // 3과 4 연결
        uf.union(2, 3); // 2와 3 연결 -> 결과적으로 1, 2, 3, 4가 하나의 집합이 됨

        // 연결 상태 확인
        System.out.println("1과 4는 연결되어 있나요? " + uf.isConnected(1, 4)); // true
        System.out.println("1과 5는 연결되어 있나요? " + uf.isConnected(1, 5)); // false
    }
}
