package graph;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
        int[][] edges = new int[][]{
                // 0  1  2  3  4  5  6  7
                {0, 1, 1, 0, 0, 0, 0, 0}, // Node 0
                {0, 0, 0, 1, 0, 0, 0, 0}, // Node 1
                {0, 0, 0, 0, 1, 0, 0, 0}, // Node 2
                {0, 0, 0, 0, 0, 1, 0, 0}, // Node 3
                {0, 0, 0, 0, 0, 0, 1, 0}, // Node 4
                {0, 0, 0, 0, 0, 0, 0, 1}, // Node 5
                {0, 0, 0, 0, 0, 1, 0, 1}, // Node 6
                {0, 0, 0, 0, 0, 0, 0, 0}  // Node 7
        };

        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);
        boolean [][] visited = new boolean[edges.length][edges[0].length];
        while (!queue.isEmpty()) {
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                int cur = queue.poll();
                System.out.println(cur + " ");
                for(int k=0;k<edges[cur].length;k++) {
                    if(edges[cur][k] == 1 && !visited[cur][k]) {
                        queue.add(k);
                        visited[cur][k] = true;
                    }
                }
            }
        }


    }
}
