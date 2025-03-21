package bst;

public class Node implements TreePrinter.PrintableNode{
    private int data;
    private Node leftNode;
    private Node rightNode;

    @Override
    public TreePrinter.PrintableNode getLeft() {
        return leftNode;
    }

    @Override
    public TreePrinter.PrintableNode getRight() {
        return rightNode;
    }

    @Override
    public String getText() {
        return "["+data+"]";
    }

    public Node(int data) {
        this.data = data;
        this.leftNode = null;
        this.rightNode = null;
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
}
