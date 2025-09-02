package astar;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Astar {
    PriorityQueue<Node> open = new PriorityQueue<>();
    List<Node> closed = new ArrayList<>();
    int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1}; // x 방향 (행)
    int[] dy = {0, 0, -1, 1, -1, 1, -1, 1}; // y 방향 (열)
    int[] end;
    int[] start;
    private int BLOCK = 3;
    private int START = 1;
    private int END = 2;
    int[][] board;
    boolean isFinished = false;

    public Astar(int[][] board) {
        this.board = board;
        System.out.println("초기 상태");
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == START){
                    this.start = new int[]{i, j};
                }

                if(board[i][j] == END){
                    this.end = new int[]{i, j};
                }

                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void start() {
        int h = manhattan(start[0], start[1], end[0], end[1]);
        open.add(new Node(null,h,0,h,start[0],start[1]));

        while(!open.isEmpty()){
            move();
            if(isFinished){
                break;
            }
        }

        if(!isFinished){
            System.out.println("도착점으로 도달 불가");
        }
    }

    public void move() {
        Node node = open.poll();
        if(node.x == end[0] && node.y == end[1]) {
            System.out.println("경로 탐색 결과");
            Node cur = node.parent;
            while(cur != null) {
//                System.out.println(cur.x + " " + cur.y);
                board[cur.x][cur.y] = 5;
                cur = cur.parent;
            }

            for(int i =0;i<board.length;i++){
                for(int j =0;j<board[i].length;j++){
                    if(i == start[0] && j == start[1]){
                        board[i][j] = START;
                    }
                    System.out.print(board[i][j] + " ");
                }

                System.out.println();
            }
            isFinished = true;
            return;
        }
        for(int dir=0;dir<4;dir++) {
            int x = node.x;
            int y= node.y;

            x += dx[dir];
            y += dy[dir];

            if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == BLOCK || closed.contains(node)) continue;

            int curG = node.g + 10;
            int curH = manhattan(x, y, end[0], end[1]);
            Node n = new Node(node,curG+curH,curG,curH,x,y);
            open.add(n);
        }

        for(int dir=4;dir<8;dir++) {
            int x = node.x;
            int y= node.y;

            x += dx[dir];
            y += dy[dir];

            if(x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == 3 || closed.contains(node)) continue;

            int curG = node.g + 14;
            int curH = manhattan(x, y, end[0], end[1]);
            Node n = new Node(node,curG+curH,curG,curH,x,y);
            open.add(n);
        }

        closed.add(node);
    }

    //h
    private int manhattan(int x, int y, int endX, int endY) {
        return Math.abs(endX - x) + Math.abs(endY - y);
    }

    public static void main(String[] args) {


        int[][] board = {
                {0,0,1,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,3,0,0,3,3,3,3,0},
                {0,0,0,0,0,0,0,2,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
        };

        int[][] board2 = {
                {0,0,1,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {3,3,3,3,3,3,3,3,3,3},
                {0,0,0,0,0,0,0,2,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
        };

        int[][] board3 = {
                {0,0,1,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {3,3,3,3,3,3,3,3,3,0},
                {0,0,0,0,0,0,0,2,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
        };

        Astar a = new Astar(board3);
        a.start();
    }

}
