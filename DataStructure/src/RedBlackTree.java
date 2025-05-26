public class RedBlackTree {
    private final Node NIL;
    private Node root;

    public RedBlackTree() {
        this.NIL = new Node();        // sentinel
        this.NIL.setRed(false);
        this.NIL.setLeftNode(NIL);
        this.NIL.setRightNode(NIL);
        this.NIL.setParent(NIL);

        this.root = NIL;
    }

    public void inOrderTraversal() {
        inOrder(this.root);
    }

    private void inOrder(Node root) {
        if(root == NIL) {
            return ;
        }
        inOrder(root.getLeftNode());
        System.out.println(root.getData());
        inOrder(root.getRightNode());
    }

    public void insert(int data) {
        Node z = new Node(data);
        z.setRed(true);
        z.setLeftNode(NIL);
        z.setRightNode(NIL);

        // BST insert (NIL 기반)
        Node y = NIL;
        Node x = root;
        while (x != NIL) {
            y = x;
            if (z.getData() < x.getData()) x = x.getLeft();
            else if (z.getData() > x.getData()) x = x.getRight();
            else {
                // 중복 정책: 1) 무시 2) 오른쪽에만 삽입 등
                // 여기서는 무시
                return;
            }
        }
        z.setParent(y);
        if (y == NIL) {
            root = z;
        } else if (z.getData() < y.getData()) {
            y.setLeftNode(z);
        } else {
            y.setRightNode(z);
        }

        fixInsert(z);
    }

    private void fixInsert(Node z) {
        // 부모가 RED인 동안
        while (z.getParent().isRed()) {
            Node p = z.getParent();
            Node g = p.getParent();

            if (p == g.getLeft()) {
                Node u = g.getRight(); // uncle
                if (u.isRed()) {
                    // Case 1: recolor
                    p.setRed(false);
                    u.setRed(false);
                    g.setRed(true);
                    z = g;
                } else {
                    if (z == p.getRight()) {
                        // Case 2: LR
                        z = p;
                        leftRotate(z);
                    }
                    // Case 3: LL
                    p.setRed(false);
                    g.setRed(true);
                    rightRotate(g);
                }
            } else {
                Node u = g.getLeft();
                if (u.isRed()) {
                    // mirror Case 1
                    p.setRed(false);
                    u.setRed(false);
                    g.setRed(true);
                    z = g;
                } else {
                    if (z == p.getLeft()) {
                        // mirror Case 2: RL
                        z = p;
                        rightRotate(z);
                    }
                    // mirror Case 3: RR
                    p.setRed(false);
                    g.setRed(true);
                    leftRotate(g);
                }
            }
        }
        root.setRed(false);
        root.setParent(NIL);
    }

    private void leftRotate(Node x) {
        Node y = x.getRight();
        x.setRightNode(y.getLeft());
        if (y.getLeft() != NIL) y.getLeft().setParent(x);

        y.setParent(x.getParent());
        if (x.getParent() == NIL) {
            root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeftNode(y);
        } else {
            x.getParent().setRightNode(y);
        }

        y.setLeftNode(x);
        x.setParent(y);
    }

    private void rightRotate(Node x) {
        Node y = x.getLeft();
        x.setLeftNode(y.getRight());
        if (y.getRight() != NIL) y.getRight().setParent(x);

        y.setParent(x.getParent());
        if (x.getParent() == NIL) {
            root = y;
        } else if (x == x.getParent().getRight()) {
            x.getParent().setRightNode(y);
        } else {
            x.getParent().setLeftNode(y);
        }

        y.setRightNode(x);
        x.setParent(y);
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        tree.insert(10);
        tree.insert(5);
        tree.insert(1);
        tree.insert(7);
        tree.insert(40);
        tree.insert(50);

        tree.inOrderTraversal();
    }
}
