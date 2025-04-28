import java.util.*;

/**
 * MyGraph class
 * Using Edge class to make and keep track of your graph and its components
 * @author  Cody Bandrowski
 */
public class MyGraph {
     private List<Integer> vertices;
    private Map<Integer, List<Edge>> adjacencyList;

    /**
     * Default Constructor
     * Initializes the vertex list and the adjacency list map
     */
    public MyGraph() {
        vertices = new LinkedList<>();
        adjacencyList = new HashMap<>();
    }

    /**
     * addVertex
     * adds v to the vertex list
     * puts an empty list of edges into the map for v
     * @param v
     */
    public void addVertex(int v) {
        vertices.add(v);
        adjacencyList.put(v, new LinkedList<>());

    }

    /**
     * addEdge
     * v1 and v2 have already been added as vertices
     * @param v1
     * @param v2
     * @param weight
     */
    public void addEdge(int v1, int v2, int weight) {
        Edge e = new Edge(v1, v2, weight);
        adjacencyList.get(v1).add(e);
        adjacencyList.get(v2).add(e);

        //List<Edge> v1Adj = adjacencyList.get(v1);
        //List<Edge> v2Adj = adjacencyList.get(v2);
        //v1Adj.add(e);
        //v2Adj.add(e);
    }

    /**
     * hasEdge
     * checks is v1 and v2 have edges
     * @param v1
     * @param v2
     * @return
     */
    public boolean hasEdge(int v1, int v2) {
        for (Edge e : adjacencyList.get(v1)) {
            if ((e.v1 == v1 && e.v2 == v2) || (e.v1 == v2 && e.v2 == v1)) {
                return true;
            }
        }
        return false;
    }
    /**
     * getVertices
     *
     */
    public List<Integer> getVertices() {
        return vertices;
    }

    /**
     * getAdjacencyList
     * @param v
     * @return
     */
    public List<Edge> getAdjacencyList(int v) {
        return adjacencyList.get(v);
    }

    /**
     * showGraph
     * used to print out and show the graph
     */
    public void showGraph() {
        System.out.print("V = { ");
        for (int v : vertices) {
            System.out.print(v + " ");
        }
        System.out.println("}");
        System.out.println("Adj Lists");
        for (int v : vertices) {
            System.out.print(v + ": ");
            for (Edge e : adjacencyList.get(v)) {
                // Show each edge only if the current vertex is the source
                if (e.v1 == v) {
                    System.out.print("(" + e.v1 + ", " + e.v2 + ", " + e.weight + ") ");
                } else if (e.v2 == v) {
                    System.out.print("(" + e.v2 + ", " + e.v1 + ", " + e.weight + ") ");
                }
            }
            System.out.println();
        }
    }
    //Part 4 Connected Components -------------------------------------------------------
    /**
     * calculateConnectedComponents
     * used to find number of components in Graph
     * @param g
     * @return
     */
    public static int[] calculateConnectedComponents(MyGraph g){
        int n = g.getVertices().size(); //number of vertices
        int[] connectedComponents = new int[n]; //holding component number of each vertices
        boolean[] visited = new boolean[n]; // false is unvisited
        int numConnectedComponents = 0;

        Queue<Integer> bfsQueue = new LinkedList<>();
        //loop over all vertices
        for(int i = 0; i < n; i++){
            if(!visited[i]){//if currVertex is unvisited
                numConnectedComponents++;//increment numComponents
                visited[i] = true; //mark as visited
                bfsQueue.add(i);

                while(!bfsQueue.isEmpty()){ //while bfsQueue is not empty
                    int compV = bfsQueue.remove(); //remove a vertex from bfsQueue
                    connectedComponents[compV] = numConnectedComponents; // inputting # of components

                    for(Edge e : g.adjacencyList.get(compV)){//For each edge e in compV's adjacency list
                        int destVertex = (e.v1 == compV ? e.v2 : e.v1);
                        if(!visited[destVertex]){
                            visited[destVertex] = true;
                            bfsQueue.add(destVertex);
                        }
                    }
                }
            }
        }
        return connectedComponents;
    }

    }


