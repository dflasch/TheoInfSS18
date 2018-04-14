package lib.graph.test;

import static org.junit.Assert.*;

import org.jgrapht.Graph;
import org.junit.Test;

import lib.graph.Edge;
import lib.graph.Vertex;
import lib.graph.algorithms.Djisktra;
import lib.graph.build.GraphBuilder;
import lib.graph.build.GraphDescription;

public class DjisktraTest {

    @Test
    public void djikstraInPath() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");

        Edge AtoB = new Edge(A, B);
        Edge BtoC = new Edge(B, C);

        GraphDescription myDescription = new GraphDescription()
                .addVertex(A).addVertex(B).addVertex(C)
                .addEdge(AtoB).addEdge(BtoC)
                .addWeight(3.0, AtoB).addWeight(5.0, BtoC);
        
        Graph<Vertex,Edge> myGraph = new GraphBuilder(myDescription).buildUndirectedWeightedGraph();
        
        Djisktra djisktraAlgorithm = new Djisktra(myGraph);
        
        assertEquals(3.0, djisktraAlgorithm.findShortestPathBetween(A, B), 0.1);
        assertEquals(8.0, djisktraAlgorithm.findShortestPathBetween(A, C), 0.1);
        
    }
    
    @Test
    public void djikstraInCircle() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        
        Edge AtoB = new Edge(A, B);
        Edge AtoC = new Edge(A, C);
        Edge BtoC = new Edge(B, C);
        
        GraphDescription myDescription = new GraphDescription()
                .addVertex(A).addVertex(B).addVertex(C)
                .addEdge(AtoB).addEdge(BtoC).addEdge(AtoC)
                .addWeight(1.0, AtoB).addWeight(4.0, BtoC).addWeight(3.0, AtoC);
        
        Graph<Vertex,Edge> myGraph = new GraphBuilder(myDescription).buildUndirectedWeightedGraph();
        
        Djisktra djisktraAlgorithm = new Djisktra(myGraph);
        
        assertEquals(djisktraAlgorithm.findShortestPathBetween(A, B), 1, 0.1);
        assertEquals(djisktraAlgorithm.findShortestPathBetween(A, C), 3, 0.1);
        
    }
    
    @Test
    public void djikstraInBiggerCircle() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        
        Edge AtoB = new Edge(A, B);
        Edge AtoC = new Edge(A, C);
        Edge BtoD = new Edge(B, D);
        Edge CtoD = new Edge(B, D);
        
        GraphDescription myDescription = new GraphDescription()
                .addVertex(A).addVertex(B).addVertex(C).addVertex(D)
                .addEdge(AtoB).addEdge(AtoC).addEdge(BtoD).addEdge(CtoD)
                .addWeight(6.0, AtoB).addWeight(5.0, AtoC).addWeight(1.0, BtoD).addWeight(8.0,CtoD);
        
        Graph<Vertex,Edge> myGraph = new GraphBuilder(myDescription).buildUndirectedWeightedGraph();
        
        Djisktra djisktraAlgorithm = new Djisktra(myGraph);
        
        assertEquals(djisktraAlgorithm.findShortestPathBetween(A, D), 7, 0.1);
        
    }
    
    @Test
    public void djikstraInBiggerDirectedCircle() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        
        Edge AtoB = new Edge(A, B);
        Edge AtoC = new Edge(A, C);
        Edge BtoD = new Edge(B, D);
        Edge CtoD = new Edge(B, D);
        
        GraphDescription myDescription = new GraphDescription()
                .addVertex(A).addVertex(B).addVertex(C).addVertex(D)
                .addEdge(AtoB).addEdge(AtoC).addEdge(BtoD).addEdge(CtoD)
                .addWeight(6.0, AtoB).addWeight(5.0, AtoC).addWeight(1.0, BtoD).addWeight(8.0,CtoD);
        
        Graph<Vertex,Edge> myGraph = new GraphBuilder(myDescription).buildDirectedWeightedGraph();
        
        Djisktra djisktraAlgorithm = new Djisktra(myGraph);
        
        assertEquals(djisktraAlgorithm.findShortestPathBetween(A, D), 7, 0.1);
        
    }
    
    @Test
    public void djikstraNoWayToTarget() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        
        Edge AtoB = new Edge(A, B);
        Edge CtoB = new Edge(C, B);
        
        GraphDescription myDescription = new GraphDescription()
                .addVertex(A).addVertex(B).addVertex(C)
                .addEdge(AtoB).addEdge(CtoB)
                .addWeight(1.0, AtoB).addWeight(1.0, CtoB);
        
        Graph<Vertex,Edge> myGraph = new GraphBuilder(myDescription).buildDirectedWeightedGraph();
        
        Djisktra djisktraAlgorithm = new Djisktra(myGraph);
        
        assertEquals(djisktraAlgorithm.findShortestPathBetween(A, C), Double.MAX_VALUE, 0.1);
        
    }

}
