package Tree;

public class BinarySearchTree {
    private TreeNode root;

    public BinarySearchTree(TreeNode root) {
        this.root = root;
    }

    public void insert(int data) {
        root = insertRec(root, data);
    }

    //root 반환
    public TreeNode insertRec(TreeNode root, int data) {
        if (root == null) {
            root = new TreeNode(data);
            return root;
        }

        if(data < root.data) {
            root.left = insertRec(root.left, data);
        }else if(data > root.data) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    public void delete(TreeNode root, int data) {
        if(root == null) {return;}
        if(data < root.data) {
            delete(root.left,data);
        } else if(data > root.data) {
            delete(root.right,data);
        } else {
            root = null;
            return;
        }
    }

    public void inOrder(TreeNode root) {
        if(root == null) {return;}

        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);


    }


    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree(new TreeNode(1));
        bst.insert(2);
        bst.insert(3);
        bst.insert(4);
        bst.insert(5);

        bst.delete(bst.root,4);

        bst.inOrder(bst.root);
    }
}
