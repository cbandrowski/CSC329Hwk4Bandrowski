//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Arrays;

/**
 * Main
 * @author Cody Bandrowski
 */
public class Main {
    public static void main(String[] args) {
        //First Graph Picture------------------------
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
        //calculateConnectedComponents
        int[] comp = MyGraph.calculateConnectedComponents(g1);

        System.out.println("Connected components ");
        System.out.println("Vertex and Comp #");
        for(int v : g1.getVertices()) {
            System.out.printf("%-8d%d%n", v, comp[v]);
        }

    }
}