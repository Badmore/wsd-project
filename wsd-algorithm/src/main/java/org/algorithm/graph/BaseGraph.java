package org.algorithm.graph;

import com.sun.istack.internal.NotNull;
import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.LinkedList;

/**
 * <h3>wsd-project</h3>
 * <p>图的基础</p>
 *
 * @author : 王松迪
 * 2024-04-23 09:33
 **/
public class BaseGraph {

    private Graph<Integer> graph;
    /**
     * 深度优先遍历
     * @param start 开始做标点
     */
    public void dfs(int start, boolean[] visited) {
        System.out.println(graph.vertices[start].data);
        visited[start] = true;
        for (int i = 0; i < graph.adj[start].size(); i++) {
            Integer v = graph.adj[start].get(i);
            if(!visited[v]) {
                dfs(v, visited);
            }
        }
    }

    public void bsf(int start, boolean[] visited, LinkedList<Integer> queue) {
        queue.offer(start);
        while(!queue.isEmpty()) {
            Integer front = queue.poll();
            if(visited[front]) {
                continue;
            }

            System.out.println(graph.vertices[front].data);
            visited[front] = true;
            graph.adj[front].forEach(queue::offer);
        }

    }


    public int mock() {
        Graph<Integer> integerGraph = new Graph<>(1,2,3,4,5,6);
        integerGraph.adj[0].add(1);
        integerGraph.adj[0].add(2);
        integerGraph.adj[0].add(3);

        integerGraph.adj[1].add(1);
        integerGraph.adj[1].add(3);
        integerGraph.adj[1].add(4);

        integerGraph.adj[2].add(0);

        integerGraph.adj[3].add(0);
        integerGraph.adj[3].add(1);
        integerGraph.adj[3].add(4);
        integerGraph.adj[3].add(5);

        integerGraph.adj[4].add(1);
        integerGraph.adj[4].add(3);
        integerGraph.adj[4].add(5);

        integerGraph.adj[5].add(3);
        integerGraph.adj[5].add(4);

        graph = integerGraph;

        return integerGraph.vertexCount;
    }


    public static class Graph<T extends Comparable<T>> {

        /**
         * 顶点个数
         */
        int vertexCount;
        /**
         * 顶点对象
         */
        Vertex<T>[] vertices;

        /**
         * 邻接表
         */
        LinkedList<T>[] adj;

        @SafeVarargs
        public Graph(T... data) {
            if(data == null || data.length == 0) {
                return ;
            }
            this.vertexCount = data.length;
            vertices = new Vertex[vertexCount];
            adj = new LinkedList[vertexCount];
            for (int i = 0; i < data.length; i++) {
                vertices[i] = new Vertex<>(data[i]);
                adj[i] = new LinkedList<>();
            }
        }
    }

    public static class Edge<T extends Comparable<T>> {

        private int weight;

        public int getWeight() {
            return weight;
        }
    }

    public static class Vertex<T extends Comparable<T>> {

        T data;

        public Vertex(T data) {
            this.data = data;
        }
    }


    public static void main(String[] args) {
        BaseGraph baseGraph = new BaseGraph();
        int mockCount = baseGraph.mock();
        System.out.println("图的深度优先遍历：");
        baseGraph.dfs(0, new boolean[mockCount]);

        System.out.println("图的广度有限遍历：");
        baseGraph.bsf(0, new boolean[mockCount], new LinkedList<>());


    }

}


