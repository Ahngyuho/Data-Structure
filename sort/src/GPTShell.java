public class GPTShell {
    public static void shellSort(int[] arr) {
        int n = arr.length;

        // 간격 설정
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // 삽입 정렬 수행
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;

                // 간격만큼 떨어진 요소 비교 및 정렬
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }

            for(int k=0;k<n;k++){
                System.out.print(arr[k] + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = {
                834, 770, 871, 231, 903, 137, 937, 714, 267, 300, 717, 782, 976, 144, 724, 692, 821, 981, 501, 630
        };
        shellSort(arr);

    }

}
