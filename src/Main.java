//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.*;

/**
 * Main
 * @author Cody Bandrowski
 */
public class Main {
    public static void main(String[] args) {
        //First Graph Picture------------------------
        System.out.println("First Graph picture -------------------------------------");
        MyGraph g1 = new MyGraph();
        for(int v = 0; v < 10; v++) {
            g1.addVertex(v);
        }
        g1.addEdge(0,2,13);
        g1.addEdge(0,1,2);
        g1.addEdge(3,5,1);
        g1.addEdge(4,7,1);
        g1.addEdge(6,8,1);
        g1.addEdge(6,9,9);
        g1.addEdge(8,9,4);
        g1.showGraph();
        System.out.println("First Graph picture Connected Components ");
        //calculateConnectedComponents of first graph
        int[] comp = MyGraph.calculateConnectedComponents(g1);

        System.out.println("Connected components ");
        System.out.println("Vertex and Comp #");
        for(int v : g1.getVertices()) {
            System.out.printf("%-8d%d%n", v, comp[v]);
        }

        //Second Graph Picture ------------------
        System.out.println("Second Graph picture ------------------------------------");
        MyGraph g2 = new MyGraph();
        for(int v = 0; v < 10; v++) {
            g2.addVertex(v);
        }
        g2.addEdge(0,2,13);
        g2.addEdge(0,1,2);
        g2.addEdge(3,5,1);
        g2.addEdge(4,7,1);
        g2.addEdge(6,8,1);
        g2.addEdge(6,9,9);
        g2.addEdge(8,9,4);
        g2.addEdge(0,3,7);
        g2.addEdge(1,3,2);
        g2.addEdge(3,6,5);
        g2.addEdge(2,5,7);
        g2.addEdge(2,4,2);
        g2.addEdge(5,7,4);
        g2.addEdge(5,8,1);
        g2.addEdge(7,8,2);
        System.out.println("Shortest path ------------------------------");
        shortestPath(g2,0);
        System.out.println("Minimum Spanning Tree -----------------------------\n");
        MyGraph g3 = minimumSpanningTree(g2,0);
        System.out.println("Original Graph");
        g2.showGraph();
        System.out.println("Minimum Spanning Tree");
        g3.showGraph();
    }

    //Part 5 Minimum Spanning tree-------------------------------------------------------
    /**
     * getMinFrontierEdge
     * gets min-weight of edge in Graph
     * @param g
     * @param visited
     * @return
     */
    public static Edge getMinFrontierEdge(MyGraph g, boolean[] visited){
        // minEdge = (0,0,Infinite)
        Edge minEdge = new Edge(0,0,Integer.MAX_VALUE);
        //For all vertices in graph (call current vertex v)
        for(int v : g.getVertices()){
            //if (visited[v] is true)
            if(visited[v]){
                //Get adjList for v
                List<Edge> adj = g.getAdjacencyList(v);
                //For all edges e in v's adjList
                for(Edge e : adj){
                    int unvisited = (e.v1 == v ? e.v2 : e.v1);
                    //if (one of e's vertices is visited and the other is not)
                    // && (e.weight < minEdge.weight))
                    if(!visited[unvisited] && (minEdge.weight > e.weight)){
                        //Set minEdge to e
                        minEdge = e;
                    }
                }

            }
        }
        // return minEdge
        return minEdge;
    }
    /**
     * minimumSpanningTree
     * Builds MST of g using the starting vertex
     * @param g
     * @param startingVertex
     * @return
     */
    public static MyGraph minimumSpanningTree(MyGraph g, int startingVertex){
        //Create a visited array of Boolean and set all elements to false.
        int n = g.getVertices().size();
        boolean[] visited = new boolean[n];

        //Add all vertices to the MST
        MyGraph mst = new MyGraph();
        for(int v : g.getVertices()){
            mst.addVertex(v);
        }
        //Pick any vertex to be the starting vertex of the MST.
        //Set visited[startVertex] to true
        visited[startingVertex] = true;
        int visitedCount =1;
        //While (there is an unvisited vertex)
        while(visitedCount < n) {
            //Set minEdge to GetMinFrontierEdge()
            Edge minEdge = getMinFrontierEdge(g, visited);
            //Set visited[minEdge.source] to true
            if (!visited[minEdge.v1]) {
                visited[minEdge.v1] = true;
                visitedCount++;
            }
            //Set visited[minEdge.dest] to true
            if (!visited[minEdge.v2]) {
                visited[minEdge.v2] = true;
                visitedCount++;
            }
            //Add minEdge to MST
            mst.addEdge(minEdge.v1, minEdge.v2, minEdge.weight);

        }
        //return MST
        return mst;
    }
    // Part 6 - Shortest Path -----------------------------------------------------------------------------

    /**
     *getMinDistVertex
     * Searches list of unvisited vertices for one with minimum distance
     * @param g         the graph
     * @param unvisitedList     list of vertices not yet processed
     * @param dist      array where dist[v] is shortest distance
     * @return          the vertex in unvisitedList with smallest dist
     */
    public static int getMinDistVertex(MyGraph g, List<Integer> unvisitedList, int[] dist){
        //minNeighborVertex = unvisitedList first element
        int minNeighborVertex = unvisitedList.get(0);
        //minNeighborDist = unvisitedList first element dist
        int minNeighborDist = dist[minNeighborVertex];

        //For all vertex v in unvisitedList
        for(int v : unvisitedList) {
            //if dist[v] < minNeighborDist
            if (dist[v] < minNeighborDist) {
                //minNeighborDist = dist[v]
                minNeighborDist = dist[v];
                //minNeighborVertex = v
                minNeighborVertex = v;
            }
        }
        //End for
        //return minNeighborVertex
        return minNeighborVertex;
    }

    /**
     *shortestPath
     * using getMinDistVertex it finds the shortest path
     * @param g
     * @param startingVertex
     */
    public static void shortestPath(MyGraph g, int startingVertex) {
        //Set all vertex distances to the max value (∞)
        int n = g.getVertices().size();
        int[] dist = new int[n];
        //Set all previous to -1
        int[] previous = new int[n];
        //Set all visited to false
        boolean[] visited = new boolean[n];
        //Put all vertices in unvisitedList
        List<Integer> unvisitedList = new ArrayList<>(g.getVertices());

        for (int v : g.getVertices()) {
            dist[v] = Integer.MAX_VALUE;
            previous[v] = -1;
            visited[v] = false;
        }

        //Pick a starting vertex and set its distance to 0
        dist[startingVertex] = 0;
        //While unvisitedList not empty
        while (!unvisitedList.isEmpty()) {
            //currV = GetMinDistVertex(unvisitedList, dist)
            int currV = getMinDistVertex(g, unvisitedList, dist);
            //Remove currV from unvisitedList and set visited[currV] to true
            unvisitedList.remove(Integer.valueOf(currV));
            visited[currV] = true;
            //For all unvisited neighbors of currV (call them n)
            for (Edge e : g.getAdjacencyList(currV)) {
                int nVertex = (e.v1 == currV ? e.v2 : e.v1);
                //Set possibleDist to (distance[currV] + weight of edge to n)
                int possibleDist = dist[currV] + e.weight;
                //if possibleDist < dist[n]
                if (possibleDist < dist[nVertex]) {
                    //Set dist[n] to possibleDist
                    dist[nVertex] = possibleDist;
                    //Set previous[n] to currV
                    previous[nVertex] = currV;
                }
            }
        }
        g.showGraph();
        System.out.println("Shortest path from \n" + "Starting V›ertex    : " + startingVertex + "\n");
        System.out.println("Vertex Dist");
        for(int v: g.getVertices()){
            System.out.printf("%-8d%d\n", v, dist[v]);

        }
        System.out.println("Vertex Previous");
        for(int v: g.getVertices()){
            System.out.printf("%-8d%d\n", v, previous[v]);

        }
    }
}