import java.util.Arrays;

public class FloydWarshall {
    final int INF = 500000;
    int[][] cost;
    int[][] path;

    public FloydWarshall(int n, int[][] edges) {
        this.cost = new int[n][n];
        this.path = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], INF);
            Arrays.fill(path[i], -1);
        }

        for(int[] edge : edges) {
            cost[edge[0]][edge[1]] = edge[2];
            path[edge[0]][edge[1]] = edge[0];
        }
    }

    public void floydWarshall() {
        int n = cost.length;

        for(int k=0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(i == j || cost[i][k] == INF || cost[k][j] == INF) continue;

                    if(cost[i][k] + cost[k][j] < cost[i][j]){
                        cost[i][j] = cost[i][k] + cost[k][j];
                        path[i][j] = k;
//                        path[j][k] = path[i][k];
                    }
                }
            }
        }
    }

    public void print() {
        System.out.println("Costs Table:");
        for(int i = 0; i < cost.length; i++) {
            for(int j = 0; j < cost[i].length; j++) {
                if(cost[i][j] == INF) {
                    System.out.print("INF ");
                }
                else System.out.print(cost[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Path Table:");
        for(int i = 0; i < path.length; i++) {
            for(int j = 0; j < path[i].length; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] edges = {
                {0, 1, 9}, {0, 3, 1}, {0, 4, 6},
                {1, 2, 6}, {1, 3, 4},
                {2, 1, 1}, {2, 5, 7},
                {3,1,2},
                {4,7,1},
                {5,1,8}, {5,3,5},{5,6,8},
                {6,5,3}
        };

        int[][] edges2 = {
                {3,2,1},
                {3,1,1},
                {2,1,1},
                {0,1,1},
                {1,4,1}
        };

        FloydWarshall fw = new FloydWarshall(8, edges);
        fw.print();
        fw.floydWarshall();
        fw.print();

    }
}
