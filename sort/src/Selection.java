public class Selection {

    public static void main(String[] args) {
        int[] arr = {
                834, 770, 871, 231, 903, 137, 937, 714, 267, 300, 717, 782, 976, 144, 724, 692, 821, 981, 501, 630
        };

        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int minIdx = i;

            for(int j=i+1;j<arr.length;j++) {
                if(arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            int tmp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = tmp;

            for(int k=0;k<arr.length;k++) {
                System.out.print(arr[k] + " ");
            }

            System.out.println();
        }


    }
}
