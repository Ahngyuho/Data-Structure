package Tree;

public class NumArray {
    private int[] tree;
    private int n;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            tree = new int[2 * n];
            buildTree(nums);
        }
    }

    private void buildTree(int[] nums) {
        // Initialize leaves of the segment tree
        for (int i = n, j = 0; i < 2 * n; i++, j++) {
            tree[i] = nums[j];
        }
        // Build the segment tree by calculating parents
        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    public void update(int pos, int val) {
        pos += n;
        tree[pos] = val;

        while (pos > 0) {
            int left = pos;
            int right = pos;

            if(pos % 2 == 0) {
                //pos 가 부모노드 기준으로 왼쪽에 존재
                right = pos + 1;
            }else {
                //pos 가 부모노드 기준으로 오른쪽에 존재
                left = pos - 1;
            }

            tree[pos / 2] = tree[left] + tree[right];
            pos /= 2;
        }
//        // Update the value at position pos
//        pos += n;
//        tree[pos] = val;
//
//        // Update the segment tree
//        while (pos > 0) {
//            int left = pos;
//            int right = pos;
//            if (pos % 2 == 0) {
//                right = pos + 1;
//            } else {
//                left = pos - 1;
//            }
//            // Parent is updated by the sum of left and right child
//            tree[pos / 2] = tree[left] + tree[right];
//            pos /= 2;
//        }
    }

    public int sumRange(int left, int right) {
        // Get the sum of the segment [left, right]
        left += n;
        right += n;
        int sum = 0;

        while (left <= right) {
//            System.out.print(left + " "+ right);
            if (left % 2 == 1) {
                System.out.println(left + " " + "sum");
                sum += tree[left];
                left++;
            }
            if (right % 2 == 0) {
                System.out.println(right + " " + "sum");
                sum += tree[right];
                right--;
            }
            left /= 2;
            right /= 2;
        }
        return sum;
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1,2, 3,4 ,5,6,7,8,9,10});
//        NumArray numArray = new NumArray(new int[]{1, 3, 5, 7, 9, 11});
        System.out.println(numArray.sumRange(0, 9));
    }
}