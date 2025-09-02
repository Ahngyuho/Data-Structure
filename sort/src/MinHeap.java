public class MinHeap {
    int[] heap;
    int curIdx;
    public MinHeap(int n) {
        heap = new int[n + 1];
        curIdx = 1;
    }

    public void insert(int x) {
        heap[curIdx] = x;
        int insertIdx = curIdx;
        curIdx++;

        while(insertIdx / 2 >= 1 && heap[insertIdx] < heap[insertIdx / 2]){
            swap(insertIdx, insertIdx / 2);
            insertIdx /= 2;
        }
    }

    public int extractMin() {
        int min = heap[1];
        int idx = 1;
        heap[1] = heap[curIdx - 1];
        curIdx--;

        while(curIdx > idx * 2 || curIdx > idx * 2 + 1){
            int prevIdx = idx;
            int left = -1;
            int right = -1;
            boolean flag = false;

            if(curIdx > idx * 2) left = heap[idx * 2];
            if(curIdx > idx * 2 + 1) right = heap[idx * 2 + 1];

            if(left == -1){
                if(heap[idx] > heap[idx * 2 + 1]) {
                    idx = idx * 2 + 1;
                    flag = true;
                }
            } else if(right == -1){
                if(heap[idx] > heap[idx * 2]) {
                    idx = idx * 2;
                    flag = true;
                }
            } else if(heap[idx] > left && heap[idx] > right){
                    if(left < right){
                        idx = idx * 2;
                    }else{
                        idx = idx * 2 + 1;
                    }
                    flag = true;
                }else if(left < right && heap[idx] > left){
                    idx = idx * 2;
                    flag = true;
                } else if(left > right && heap[idx] > right){
                    idx = idx * 2 + 1;
                    flag = true;
                }

            if(!flag) break;
            swap(prevIdx,idx);
        }

        return min;
    }

    public void print() {
        for(int i = 1; i < curIdx; i++) {
            System.out.print(heap[i] + " ");
        }

        System.out.println();
    }

    private void swap(int x, int y) {
        int temp = heap[x];
        heap[x] = heap[y];
        heap[y] = temp;
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(20);
        int[] arr = {
                834, 770, 871, 231, 903, 137, 937, 714, 267, 300, 717, 782, 976, 144, 724, 692, 821, 981, 501, 630
        };

        for(int i = 0; i < arr.length; i++) {
            minHeap.insert(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            minHeap.extractMin();
            minHeap.print();
        }
    }
}
