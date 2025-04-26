/**
 * Edge class
 * used to make memeber variables
 * @author Cody Bandrowski
 */
public class Edge {
    int v1,v2,weight;

    /**
     *Makes an aedge between vertex v1 and v2 with weight
     * @param v1     one endpoint
     * @param v2    Second endpoint
     * @param weight    weight of edge
     */
    public Edge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }
}
