package bst;

import java.util.Stack;

public class Bst {
    private Node root;

    public Bst() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    // 숫자를 하나 전달받아서 데이터를 추가하는 기능
    public void insert(int data) {
//		전달받은 숫자로 노드 객체를 하나 생성
        Node newNode;
        newNode = new Node(data);
//
//		루트 노드를 저장할 변수가 비어있으면
        if (root == null) {
//			루트 노드를 저장할 변수에 방금 생성한 객체를 저장
            root = newNode;
        }
//		그렇지 않으면
        else {
//			비교할 노드를 저장할 변수 생성
            Node curret;
//			비교할 노드를 저장할 변수에 루트 노드를 저장
            curret = root;
//			비교할 노드가 비어있지 않으면 반복
            while (curret != null) {
//				만약에 전달받은 숫자가 비교할 노드의 숫자보다 작으면
                if(data < curret.getData()) {
//					비교할 노드의 왼쪽 노드가 비어있으면
                    if(curret.getLeftNode() == null) {
//						비교할 노드의 왼쪽 노드에 생성한 노드를 저장
                        curret.setLeftNode(newNode);
//						반복문 중지
                        break;
                    }
//					그렇지 않으면
                    else {
//						비교할 노드 변수에 현재 비교할 노드의 왼쪽 노드를 저장
                        curret = curret.getLeftNode();
                    }
                }
//				만약에 전달받은 숫자가 비교할 노드의 숫자보다 크면
                else if(data > curret.getData()) {
//					비교할 노드의 오른쪽 노드가 비어있으면
                    if(curret.getRightNode() == null) {
//						비교할 노드의 오른쪽 노드에 생성한 노드를 저장
                        curret.setRightNode(newNode);
//						반복문 중지
                        break;
                    }
//					그렇지 않으면
                    else {
//						비교할 노드 변수에 현재 비교할 노드의 오른쪽 노드를 저장
                        curret = curret.getRightNode();
                    }
                }
            }
        }
    }


    public void preorder(Node node) {
        if(node == null) {return;}
        System.out.println(node.getData() + " - ");
        preorder(node.getLeftNode());
        preorder(node.getRightNode());
    }
    public void inorder(Node node) {
        if(node == null) {return;}
        inorder(node.getLeftNode());
        System.out.println(node.getData() + " - ");
        inorder(node.getRightNode());
    }
    public void postorder(Node node) {
        if(node == null) {return;}
        postorder(node.getLeftNode());
        postorder(node.getRightNode());
        System.out.println(node.getData() + " - ");
    }

    public void preOrder2(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.getData() + " - ");
            if (cur.getLeftNode() != null) {
                stack.push(cur.getLeftNode());
            }
            if (cur.getRightNode() != null) {
                stack.push(cur.getRightNode());
            }
        }
    }

}
