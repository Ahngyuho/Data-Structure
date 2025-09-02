import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Dijkstra {
    List<List<Node>> graph;
    int[] distance;
    boolean[] visited;
    int[] path;
    int nodeCount;
    int startNode;

    public Dijkstra(int n, int[][] edges) {
        nodeCount = n;
        graph = new ArrayList<>();
        distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        visited = new boolean[n];
        path = new int[n];
        for(int i = 0; i < n; i++) {
            path[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(new Node(edge[1], edge[2]));
            graph.get(edge[1]).add(new Node(edge[0], edge[2]));
        }
    }

    public void dijkstra(int start) {
        int cur = start;
        startNode = start;
        distance[cur] = 0;

        for(int i = 0; i < nodeCount - 1; i++) {
            visited[cur] = true;

            for(Node node : graph.get(cur)) {
                if (distance[node.vertex] > distance[cur] + node.cost) {
                    distance[node.vertex] = distance[cur] + node.cost;
                    path[node.vertex] = cur;
                }
            }

            cur = getSmallest();
        }
    }

    public void findPath() {
        for(int i = 0; i < nodeCount; i++) {
            if(path[i] == -1) continue;

            Stack<Integer> stack = new Stack<>();
            stack.push(i);

            int cur = i;
            while(true) {
                int next = path[cur];

                if(next == -1) {
                    break;
                }

                stack.push(next);
                cur = next;
            }

            StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty()) {
                sb.append(stack.pop());
                sb.append(" ");
            }

            System.out.println(sb.toString());
        }
    }

    private int getSmallest() {
        int min = Integer.MAX_VALUE;
        int smallest = -1;
        for(int i=0;i<nodeCount;i++) {
            if(distance[i] < min && !visited[i]) {
                min = distance[i];
                smallest = i;
            }
        }

        return smallest;
    }

    public void print() {
        System.out.println("costs:");
        for(int dist : distance) {
            System.out.println(dist);
        }
        System.out.println();
        System.out.println("path:");
        for(int path : path) {
            System.out.println(path);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] edges = {
                {0,3,4},{0,4,6},{0,2,1},{1,2,7},{1,6,9},{2,5,9},
                {2,6,8},{5,3,1},{5,7,3},{5,6,5},{4,7,3},{4,6,5}
        };
        Dijkstra dijkstra = new Dijkstra(8, edges);

        dijkstra.dijkstra(0);
        dijkstra.print();
        dijkstra.findPath();

    }
}
