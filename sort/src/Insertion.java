public class Insertion {

    public static void main(String[] args) {
        int[] arr = {
                834, 770, 871, 231, 903, 137, 937, 714, 267, 300, 717, 782, 976, 144, 724, 692, 821, 981, 501, 630
        };

        for(int i = 1; i < arr.length ; i++) {
            for(int j = i; j > 0 ; j--) {
                if(arr[j - 1] > arr[j]) {
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }

            for(int k = 0; k < arr.length; k++) {
                System.out.print(arr[k] + " ");
            }

            System.out.println();
        }
    }
}
