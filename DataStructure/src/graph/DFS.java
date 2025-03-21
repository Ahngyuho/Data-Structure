package graph;

import java.util.Stack;

public class DFS {
    int[][] edges;

    public static void main(String[] args) {
        DFS dfs = new DFS();
        dfs.edges = new int[][]{
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

        Stack<Integer> stack = new Stack<>();
        int start = 0;
        stack.push(start);
        boolean[] visited = new boolean[8];
        visited[start] = true;
        System.out.println(start + " ");
        while(!stack.isEmpty()) {
            int cur = stack.peek();
            boolean flag = false;

            for (int i = 0; i < dfs.edges[0].length; i++) {
                if(i != cur && dfs.edges[cur][i] == 1 && !visited[i]){
                    stack.push(i);
                    System.out.println(i + " ");
                    visited[i] = true;
                    flag = true;
                    break;
                }
            }

            if(!flag) {
                stack.pop();
            }

        }
    }
}
