package exc3;

import java.io.IOException;

import org.jgrapht.Graph;

import lib.graph.Edge;
import lib.graph.Vertex;
import lib.graph.algorithms.Djisktra;
import lib.graph.build.GraphBuilder;
import lib.graph.build.GraphDescription;
import lib.graph.build.Parser;
import lib.io.Reader;

public class Excercise3a {
    
    public static void main(String[] args) throws IOException {
        GraphDescription dijkstraGraphDescription = Parser.parseDescription(new Reader().readFile("input/Dijkstra.txt"));
        Graph<Vertex,Edge> djikstraGraph = new GraphBuilder(dijkstraGraphDescription).buildUndirectedWeightedGraph();
        
        Vertex A = dijkstraGraphDescription.getVerticesMap().get("A");
        Vertex B = dijkstraGraphDescription.getVerticesMap().get("B");
        Vertex D = dijkstraGraphDescription.getVerticesMap().get("D");
        Vertex H = dijkstraGraphDescription.getVerticesMap().get("H");
        
        Djisktra djisktraAlgorithm = new Djisktra(djikstraGraph);
        
        System.out.println("Shortest path from A -> D: " + djisktraAlgorithm.findShortestPathBetween(A, D));
        System.out.println("Shortest path from H -> B: " + djisktraAlgorithm.findShortestPathBetween(H, B));
        
        
    }
    
}
