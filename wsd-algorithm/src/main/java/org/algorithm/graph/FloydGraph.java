package org.algorithm.graph;

/**
 * <h3>wsd-project</h3>
 * <p>佛洛依德多源最短路径</p>
 *
 * @author : 王松迪
 * 2024-04-25 09:44
 **/
public class FloydGraph {

    private final static int INF = Integer.MAX_VALUE;

    public static void floyd(int[][] graph) {
        int n = graph.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][k] == Integer.MAX_VALUE || graph[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        System.out.printf("最短路径矩阵: \n");

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                System.out.printf("%3d  ", graph[i][j]);
            }
            System.out.printf("\n");
        }
    }

    public static void main(String[] args) {

        //构造矩阵
        int[][] graph = {
                { 0, 5, 2, INF, INF, INF, INF },
                { 5, 0, INF, 1, 6, INF, INF },
                { 2, INF, 0, 6, INF, 8, INF },
                { INF, 1, 6, 0, 1, 2, INF },
                { INF, 6, INF, 1, 0, INF, 7 },
                { INF, INF, 8, 2, INF, 0, 3 },
                { INF, INF, INF, INF, 7, 3, 0 }
        };

        floyd(graph);

    }

}
