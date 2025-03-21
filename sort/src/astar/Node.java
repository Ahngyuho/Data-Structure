package astar;

public class Node implements Comparable<Node> {
    public Node parent;
    public int f;
    public int g;
    public int h;
    public int x;
    public int y;

    public Node(Node parent, int f, int g, int h, int x, int y) {
        this.parent = parent;
        this.f = f;
        this.g = g;
        this.h = h;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Node o) {
        return this.f - o.f;
    }

    // equals 재정의: x, y만 비교
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;           // 같은 객체 참조
        if (obj == null || getClass() != obj.getClass()) return false;

        Node other = (Node) obj;
        return this.x == other.x && this.y == other.y;
    }

    // hashCode 재정의: x, y만 반영
    @Override
    public int hashCode() {
        // 간단히 prime factor를 사용한 예시
        int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

}
