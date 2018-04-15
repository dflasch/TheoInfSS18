package test.lib.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.io.ExportException;
import org.junit.jupiter.api.Test;

import lib.graph.Edge;
import lib.graph.Vertex;
import lib.graph.algorithms.FordFulkerson;
import lib.graph.build.GraphBuilder;
import lib.graph.build.GraphDescription;
import lib.io.Exporter;

class FordFulkersonTest {

    @Test
    void test() throws ExportException, IOException {
        Vertex S = new Vertex("S");
        Vertex T = new Vertex("T");
        Vertex V1 = new Vertex("1");
        Vertex V2 = new Vertex("2");

        Edge StoV1 = new Edge(S, V1);
        Edge StoV2 = new Edge(S, V2);
        Edge V1toV2 = new Edge(V1, V2);
        Edge V1toT = new Edge(V1, T);
        Edge V2toT = new Edge(V2, T);

        GraphDescription myDescription = new GraphDescription().addVertex(S).addVertex(V1).addVertex(V2).addVertex(T)
                .addEdge(StoV1).addEdge(StoV2).addEdge(V1toV2).addEdge(V1toT).addEdge(V2toT).addWeight(2.0, StoV1)
                .addWeight(4.0, StoV2).addWeight(3.0, V1toV2).addWeight(1.0, V1toT).addWeight(5.0, V2toT);

        Graph<Vertex,Edge> myGraph = new GraphBuilder(myDescription).buildDirectedWeightedMultigraph();
        
        FordFulkerson fordFulkersonAlgorithm = new FordFulkerson(myGraph);
        
        double maxFlow = fordFulkersonAlgorithm.calculateMaxFlow(S, T);

        new Exporter(myGraph).printToFile("output/test",true);
        
        assertEquals(6.0, maxFlow, 0.1);
    }

}
