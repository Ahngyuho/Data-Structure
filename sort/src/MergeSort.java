public class MergeSort {

    //1개만
    int[] mergeSort(int[] arr, int left, int right) {
        if(left == right) return new int[]{arr[left]};
        int mid = (left + right) / 2;

        int[] merged = new int[right - left + 1];

        int[] firstArr = mergeSort(arr, left, mid);
        int[] secondArr = mergeSort(arr, mid + 1, right);
        if(firstArr.length == 1) System.out.print("["+firstArr[0]+"]");
        else {
            for(int i = 0; i < firstArr.length; i++) {
                if(i == 0) {
                    System.out.print("["+firstArr[i]+", ");
                } else if(i == firstArr.length-1) {
                    System.out.print(firstArr[i]+"]");
                } else {
                    System.out.print(firstArr[i]+", ");
                }
            }
        }

        System.out.print(" - ");

        if(secondArr.length == 1) System.out.print("["+secondArr[0]+"]");
        else {
            for(int i = 0; i < secondArr.length; i++) {
                if(i == 0) {
                    System.out.print("["+secondArr[i]+", ");
                } else if(i == secondArr.length-1) {
                    System.out.print(secondArr[i]+"]");
                } else {
                    System.out.print(secondArr[i]+", ");
                }
            }
        }

        System.out.println();

        int mergedIdx = 0;
        int firstIdx = 0;
        int secondIdx = 0;
        //합쳐지는 과정?
        while(firstIdx < firstArr.length && secondIdx < secondArr.length) {
            if(firstArr[firstIdx] < secondArr[secondIdx]) {
                merged[mergedIdx++] = firstArr[firstIdx++];
            }else{
                merged[mergedIdx++] = secondArr[secondIdx++];
            }
        }

        while(firstIdx < firstArr.length) {
            merged[mergedIdx++] = firstArr[firstIdx++];
        }

        while(secondIdx < secondArr.length) {
            merged[mergedIdx++] = secondArr[secondIdx++];
        }

        for(int i = 0; i < mergedIdx; i++) {
            if(i == 0){
                System.out.print("[" + merged[i] + ", ");
            }else if(i == mergedIdx-1) {
                System.out.print(merged[i] + "]");
            }else{
                System.out.print(merged[i]+", ");
            }
        }

        System.out.println();

        return merged;
    }

    public static void main(String[] args) {
        int[] arr = {
                834, 770, 871, 231, 903, 137, 937, 714, 267, 300, 717, 782, 976, 144, 724, 692, 821, 981, 501, 630
        };

        MergeSort mergeSort = new MergeSort();
        int[] merged = mergeSort.mergeSort(arr, 0, arr.length - 1);
//        for(int i : merged) {
//            System.out.print(i + " ");
//        }

    }
}
