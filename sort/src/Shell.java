public class Shell {

    public static void main(String[] args) {
//        int[] arr = {
//                834, 770, 871, 231, 903, 137, 937, 714, 267, 300, 717, 782, 976, 144, 724, 692, 821, 981, 501, 630
//        };

        int[] arr = {
                832, 930, 610, 418, 739, 869, 742, 519, 233, 330, 812, 430, 174, 401, 657, 915, 533, 281, 988, 767
        };
        int n = arr.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            if(gap % 2 == 0) gap++;
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;

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
}
