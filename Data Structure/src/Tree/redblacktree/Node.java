package Tree.redblacktree;

public class Node implements TreePrinter.PrintableNode {
    private int data;
    private boolean isRed;
    private Node leftNode;
    private Node rightNode;
    private Node parent;

    public Node() {

    }

    public Node(int data) {
        this.isRed = true;
        this.data = data;
        this.leftNode = null;
        this.rightNode = null;
    }



    public Node getLeft() {
        return leftNode;
    }

    public Node getRight() {
        return rightNode;
    }

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    public String getText() {
        return "["+data+"]";
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }


}
