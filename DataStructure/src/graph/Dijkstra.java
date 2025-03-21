package graph;

import java.util.PriorityQueue;


public class Dijkstra {

    public static void main(String[] args) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();

        int start = 0;
        pq.add(new Node(start, 0));
        int distance = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();

            distance += node.cost;

        }
    }
}

class Node implements Comparable<Node> {
    int cost;
    int vertex;

    public Node(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost; // 비용기준 오름차순
    }
}
