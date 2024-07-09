package Tree;

public class TreeTraversal {
    TreeNode root;

    public TreeTraversal() {
        root = null;
    }

    public void preorderTraversal(TreeNode treeNode) {
        if (treeNode == null) return;

        System.out.print(treeNode.data + " ");
        preorderTraversal(treeNode.left);
        preorderTraversal(treeNode.right);
    }

    public void inOrderTraversal(TreeNode treeNode) {
        if (treeNode == null) return;

        inOrderTraversal(treeNode.left);
        System.out.print(treeNode.data + " ");
        inOrderTraversal(treeNode.right);
    }

    public void postOrderTraversal(TreeNode treeNode) {
        if(treeNode == null) return;

        postOrderTraversal(treeNode.left);
        postOrderTraversal(treeNode.right);
        System.out.print(treeNode.data + " ");

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        TreeTraversal traversal = new TreeTraversal();
        traversal.preorderTraversal(root);
        System.out.println();
        traversal.inOrderTraversal(root);
        System.out.println();
        traversal.postOrderTraversal(root);
    }
}
