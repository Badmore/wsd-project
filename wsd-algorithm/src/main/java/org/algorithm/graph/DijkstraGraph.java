package org.algorithm.graph;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * <h3>wsd-project</h3>
 * <p>迪杰斯特拉最短路径算法</p>
 *
 * @author : 王松迪
 * 2024-04-24 09:29
 **/

public class DijkstraGraph {


    private Graph graph;


    public void initGraph(Graph graph) {
        graph.vertices[0] = new Vertex("A");
        graph.vertices[1] = new Vertex("B");
        graph.vertices[2] = new Vertex("C");
        graph.vertices[3] = new Vertex("D");
        graph.vertices[4] = new Vertex("E");
        graph.vertices[5] = new Vertex("F");
        graph.vertices[6] = new Vertex("G");
        graph.adj[0].add(new Edge(1, 5));
        graph.adj[0].add(new Edge(2, 2));
        graph.adj[1].add(new Edge(0, 5));
        graph.adj[1].add(new Edge(3, 1));
        graph.adj[1].add(new Edge(4, 6));
        graph.adj[2].add(new Edge(0, 2));
        graph.adj[2].add(new Edge(3, 6));
        graph.adj[2].add(new Edge(5, 8));
        graph.adj[3].add(new Edge(1, 1));
        graph.adj[3].add(new Edge(2, 6));
        graph.adj[3].add(new Edge(4, 1));
        graph.adj[3].add(new Edge(5, 2));
        graph.adj[4].add(new Edge(1, 6));
        graph.adj[4].add(new Edge(3, 1));
        graph.adj[4].add(new Edge(6, 7));
        graph.adj[5].add(new Edge(2, 8));
        graph.adj[5].add(new Edge(3, 2));
        graph.adj[5].add(new Edge(6, 3));
        graph.adj[6].add(new Edge(4, 7));
        graph.adj[6].add(new Edge(5, 3));
    }

    public DijkstraGraph() {
        graph = new Graph(7);
        initGraph(graph);
    }

    public int[] dijkstra(int startIndex) {
        //图的顶点数量
        int size = graph.vertices.length;
        //记录每个顶点的最短路径
        int[] distance = new int[size];
        Arrays.fill(distance, Integer.MAX_VALUE);

        //记录每个顶点是否被访问过
        boolean[] access = new boolean[size];

        //遍历起点，从玲姐表中获取顶点的权重
        access[0] = true;
        LinkedList<Edge> edgesFromStart = graph.adj[startIndex];
        for (Edge edge : edgesFromStart) {
            distance[edge.index] = edge.weight;
        }

        //住循环，重复遍历最短距离和刷新距离表的操作
        for (int i = 0; i < distance.length; i++) {
            //寻找最短距离的顶点
            int minDistanceFromStart = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < distance.length; j++) {
                if(!access[j] && (distance[j] < minDistanceFromStart)) {
                    minDistanceFromStart = distance[j];
                    minIndex = j;
                }
            }

            if(minIndex == -1) {
                break;
            }

            //遍历顶点，刷新距离表
            access[minIndex] = true;
            for (Edge edge : graph.adj[minIndex]) {
                if(access[edge.index]) {
                    continue;
                }
                int weight = edge.weight;
                int preDistance = distance[edge.index];
                if(weight != Integer.MAX_VALUE && (minDistanceFromStart + weight) < preDistance) {
                    distance[edge.index] = minDistanceFromStart + weight;
                }
            }

        }

        return distance;
    }

    public int[] dijkstraWithPath(int startIndex) {
        //图的顶点数量
        int size = graph.vertices.length;
        //记录每个顶点的最短路径
        int[] distance = new int[size];

        int[] path = new int[size];
        Arrays.fill(distance, Integer.MAX_VALUE);

        //记录每个顶点是否被访问过
        boolean[] access = new boolean[size];

        //遍历起点，从玲姐表中获取顶点的权重
        access[0] = true;
        LinkedList<Edge> edgesFromStart = graph.adj[startIndex];
        for (Edge edge : edgesFromStart) {
            distance[edge.index] = edge.weight;
            path[edge.index] = 0;
        }

        //住循环，重复遍历最短距离和刷新距离表的操作
        for (int i = 0; i < distance.length; i++) {
            //寻找最短距离的顶点
            int minDistanceFromStart = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < distance.length; j++) {
                if(!access[j] && (distance[j] < minDistanceFromStart)) {
                    minDistanceFromStart = distance[j];
                    minIndex = j;
                }
            }

            if(minIndex == -1) {
                break;
            }

            //遍历顶点，刷新距离表
            access[minIndex] = true;
            for (Edge edge : graph.adj[minIndex]) {
                if(access[edge.index]) {
                    continue;
                }
                int weight = edge.weight;
                int preDistance = distance[edge.index];
                if(weight != Integer.MAX_VALUE && (minDistanceFromStart + weight) < preDistance) {
                    distance[edge.index] = minDistanceFromStart + weight;
                    path[edge.index] = minIndex;
                }
            }

        }

        return path;
    }


    private  void printPath(int[] prev, int i) {
        if (i > 0) {
            printPath(prev, prev[i]);
        }

        System.out.println(graph.vertices[i].data);
    }

    public static void main(String[] args) {

        DijkstraGraph dijkstraGraph = new DijkstraGraph();
        int[] dijkstra = dijkstraGraph.dijkstra(0);

        System.out.println(dijkstra[6]);


        int[] dijkstraWithPath = dijkstraGraph.dijkstraWithPath(0);

        dijkstraGraph.printPath(dijkstraWithPath, 6);
    }



}

class Graph {
    /**
     * 顶点数组
     */
    Vertex[] vertices;
    /**
     * 邻接表
     */
    LinkedList<Edge>[] adj;

    public Graph(int size) {
        vertices = new Vertex[size];
        this.adj = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            adj[i] = new LinkedList<>();
        }
    }
}

class Edge {

    int weight;

    int index;

    public Edge(int index, int weight) {
        this.weight = weight;
        this.index = index;
    }
}

class Vertex {

    String data;

    public Vertex(String data) {
        this.data = data;
    }
}