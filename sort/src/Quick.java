public class Quick {
    public void sort(int[] arr, int left, int right) {
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i] + " ");
        }

        System.out.println();
        if(left >= right) {


            return;
        }

        int pivot = partition(arr, left, right);

        sort(arr,left,pivot - 1);
        sort(arr,pivot + 1,right);
    }

    private int partition(int[] arr,int left,int right){
        int pivot = left;
        int low = left + 1;
        int high = right;

        while(low <= high){
            while(low <= high && arr[low] <= arr[pivot]) low++;
            while(low <= high && arr[high] >= arr[pivot]) high--;

            if(low <= high){
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
            }
        }

        int temp = arr[high];
        arr[high] = arr[pivot];
        arr[pivot] = temp;

//        for(int i=0;i<arr.length;i++){
//            System.out.print(arr[i] + " ");
//        }
//
//        System.out.println();

        return high;
    }

    public static void main(String[] args) {
        Quick quick = new Quick();
        int[] arr = {
                834, 770, 871, 231, 903, 137, 937, 714, 267, 300, 717, 782, 976, 144, 724, 692, 821, 981, 501, 630
        };

        int[] arr2 = {
                832, 930, 610, 418, 739, 869, 742, 519, 233, 330, 812, 430, 174, 401, 657, 915, 533, 281, 988, 767
        };

        quick.sort(arr, 0, arr.length - 1);
    }
}
