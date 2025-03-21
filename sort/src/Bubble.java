public class Bubble {

    public static void main(String[] args) {
        int[] arr = {
                834, 770, 871, 231, 903, 137, 937, 714, 267, 300, 717, 782, 976, 144, 724, 692, 821, 981, 501, 630
        };

        for(int i = 0; i < arr.length - 1; i++) {

            for(int j=0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j + 1]){
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                }

            }

            for(int k = 0; k < arr.length; k++) {
                System.out.print(arr[k] + " ");
            }

            System.out.println();
        }
    }
}
