public class GTPQuick {
    public void divide(int[] arr, int left, int right) {
        if (left >= right) return;

        int pivotIndex = left;
        int low = left + 1;
        int high = right;

        // Partition
        while (low <= high) {
            while (low <= right && arr[low] <= arr[pivotIndex]) low++;
            while (high > left && arr[high] >= arr[pivotIndex]) high--;

            if (low < high) {
                // Swap low and high
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
            }
        }

        // Swap pivot with high
        int temp = arr[high];
        arr[high] = arr[pivotIndex];
        arr[pivotIndex] = temp;

        // Print current state of array
        for (int i = left; i <= right; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // Recursive call for left and right partitions
        divide(arr, left, high - 1);
        divide(arr, high + 1, right);
    }

}
