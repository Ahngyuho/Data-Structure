import java.util.*;
class Solution {
    static class Point implements Comparable<Point>{
        int x;
        int dis;

        public Point(int x,int dis){
            this.x = x;
            this.dis = dis;
        }

        public int compareTo(Point o){
            return this.dis - o.dis;
        }
    }
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        int[] aDis = getDis(n,fares,a);
        int[] bDis = getDis(n,fares,b);
        int[] sDis = getDis(n,fares,s);

        for(int i=1;i<=n;i++){
            answer = Math.min(answer,aDis[i] + bDis[i] + sDis[i]);
        }

        return answer;
    }



    public int[] getDis(int N, int[][] road, int startNode) {
        ArrayList<ArrayList<Point>> graph = new ArrayList<ArrayList<Point>>();
        // PriorityQueue<Point> pq = new PriorityQueue<Point>();

        int[] dis = new int[N+1];
        boolean[] visited = new boolean[N+1];

        Arrays.fill(dis, Integer.MAX_VALUE);

        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<Point>());
        }

        for(int[] x : road){
            graph.get(x[0]).add(new Point(x[1],x[2]));
            graph.get(x[1]).add(new Point(x[0],x[2]));
        }

        // pq.offer(new Point(1,0));
        dis[startNode] = 0;
        int cur = startNode;
        int nodeCount = N;

        for(int i = 0; i < nodeCount; i++) {
            visited[cur] = true;

            for(Point point : graph.get(cur)) {
                if (dis[point.x] > dis[cur] + point.dis) {
                    dis[point.x] = dis[cur] + point.dis;
                }
            }

            cur = getSmallest(dis,visited,nodeCount);
        }

        return dis;
    }

    private int getSmallest(int[] distance,boolean[] visited,int nodeCount) {
        int min = Integer.MAX_VALUE;
        int smallest = -1;
        for(int i=1;i<=nodeCount;i++) {
            if(distance[i] < min && !visited[i]) {
                min = distance[i];
                smallest = i;
            }
        }

        if(smallest == -1) {
            for(int i=1;i<=nodeCount;i++) {
                if(visited[i]) {
                    return i;
                }
            }
        }

        return smallest;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int ans = solution.solution(6, 4, 6, 2, new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}});
        int ans2 = solution.solution(7, 3, 4, 1, new int[][]{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}});
        System.out.println(ans2);
    }
}