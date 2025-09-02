package Tree.redblacktree;

public class RedBlackTree  {
    private Node root;
    private Node NIL;
    public RedBlackTree() {
        this.root = null;
    }

    public void insert(int data) {
        if(root == null) {
            Node node = new Node(data);
            node.setRed(false);
            root = node;
            NIL = new Node();
            NIL.setRed(false);
            return;
        }

        Node node = new Node(data);
        node.setRightNode(NIL);
        node.setLeftNode(NIL);
        node.setParent(null);
        insertNode(data, node);
        fixTree(node);
    }

    private void insertNode(int data, Node node) {
//        전달받은 숫자로 노드 객체를 하나 생성
        Node newNode;
        newNode = new Node(data);
        newNode.setRightNode(NIL);
        newNode.setLeftNode(NIL);
        newNode.setParent(null);
//
//		루트 노드를 저장할 변수가 비어있으면
        if (root == null) {
//			루트 노드를 저장할 변수에 방금 생성한 객체를 저장
            root = newNode;
        }
//		그렇지 않으면
        else {
//			비교할 노드를 저장할 변수 생성
            Node current;
//			비교할 노드를 저장할 변수에 루트 노드를 저장
            current = root;
//			비교할 노드가 비어있지 않으면 반복
            while (current != null) {
//				만약에 전달받은 숫자가 비교할 노드의 숫자보다 작으면
                if(data < current.getData()) {
//					비교할 노드의 왼쪽 노드가 비어있으면
                    if(current.getLeftNode() == null) {
//						비교할 노드의 왼쪽 노드에 생성한 노드를 저장
                        current.setLeftNode(newNode);
                        newNode.setParent(current);
//						반복문 중지
                        break;
                    }
//					그렇지 않으면
                    else {
//						비교할 노드 변수에 현재 비교할 노드의 왼쪽 노드를 저장
                        current = current.getLeftNode();
                    }
                }
//				만약에 전달받은 숫자가 비교할 노드의 숫자보다 크면
                else if(data > current.getData()) {
//					비교할 노드의 오른쪽 노드가 비어있으면
                    if(current.getRightNode() == null) {
//						비교할 노드의 오른쪽 노드에 생성한 노드를 저장
                        current.setRightNode(newNode);
                        newNode.setParent(current);
//						반복문 중지
                        break;
                    }
//					그렇지 않으면
                    else {
//						비교할 노드 변수에 현재 비교할 노드의 오른쪽 노드를 저장
                        current = current.getRightNode();
                    }
                }
            }
        }


    }
    private void fixTree(Node node) {

        while (node.getParent() != null && node.getParent().isRed()) {
            Node grandParentNode = node.getParent().getParent();

            if(grandParentNode.getLeftNode() == node.getParent()){
                Node uncle = grandParentNode.getRightNode();

                //삼촌이 빨강일 경우 Recoloring
                if(uncle.isRed()){
                    uncle.setRed(false);
                    node.getParent().setRed(false);
                    grandParentNode.setRed(true);
                    node = grandParentNode;
                }else{
                    //rotation
                    if(node.getParent().getRightNode() == node){
                        node = node.getParent();
                        leftRotate(node);
                    }

                    node.getParent().setRed(false);
                    grandParentNode.setRed(true);
                    rightRotate(grandParentNode);
                }
            } else {

                Node uncle = grandParentNode.getLeftNode();
                if(uncle.isRed()){
                    uncle.setRed(false);
                    node.getParent().setRed(false);
                    grandParentNode.setRed(true);
                    node = grandParentNode;
                }else{
                    if(node.getParent().getLeftNode() == node){
                        node = node.getParent();
                        rightRotate(node);
                    }

                    node.getRight().setRed(false);
                    grandParentNode.setRed(true);
                    leftRotate(grandParentNode);
                }
            }
        }
        root.setRed(false);
    }

    // 좌회전
    private void leftRotate(Node x) {
        Node y = x.getRight();
        x.setRightNode(y.getLeft());

        if (y.getLeft() != NIL) {
            y.getLeft().setParent(x);
        }

        y.setParent(x.getParent());

        if (x.getParent() == null) {
            root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeftNode(y);
        } else {
            x.getParent().setRightNode(y);
        }

        y.setLeftNode(x);
        x.setParent(y);
    }

    // 우회전
    private void rightRotate(Node x) {
        Node y = x.getLeftNode();
        x.setLeftNode(y.getRight());

        if (y.getRightNode() != NIL) {
            y.getRight().setParent(x);
            y.getRightNode().setParent(x);
        }

        y.setParent(x.getParent());

        if (x.getParent() == null) {
            root = y;
        } else if (x == x.getParent().getRight()) {
            x.getParent().setRightNode(y);
        } else {
            x.getParent().setLeftNode(y);
        }

        y.setRightNode(x);
        x.setParent(y);
    }


}
