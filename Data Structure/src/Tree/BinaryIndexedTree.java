package Tree;

public class BinaryIndexedTree {
    private int[] bit;
    private int n;

    public BinaryIndexedTree(int size) {
        this.n = size;
        this.bit = new int[size + 1];
    }

    // Add value to the index idx
    // 특정 인덱스에 값을 더하거나 뺄 때, 관련된 모든 노드의 값을 업데이트합니다.
    public void update(int idx, int value) {
        while (idx <= n) {
            bit[idx] += value;
            idx += idx & -idx;
        }
    }

    // Get prefix sum from 1 to idx
    // 특정 인덱스까지의 부분 합계를 구할 때, 관련된 모든 노드의 값을 더하여 결과를 계산합니다.
    public int query(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += bit[idx];
            //i 의 마지막 비트
            idx -= idx & -idx;
        }
        return sum;
    }

    // Get sum in range [l, r]
    public int rangeQuery(int l, int r) {
        return query(r) - query(l - 1);
    }

    public static void main(String[] args) {
        int size = 10;
        BinaryIndexedTree bit = new BinaryIndexedTree(size);

        // Sample updates
        bit.update(1, 1);
        bit.update(2, 2);
        bit.update(3, 3);
        bit.update(4, 4);
        bit.update(5, 5);

        // Sample queries
        System.out.println("Sum of first 5 elements: " + bit.query(5)); // Should print 15
        System.out.println("Sum of elements from index 2 to 4: " + bit.rangeQuery(2, 4)); // Should print 9
    }
}