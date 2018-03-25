package exc2;

import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.traverse.DepthFirstIterator;

import lib.graph.Edge;
import lib.graph.Parser;
import lib.graph.PrintingTraversalListener;
import lib.graph.Vertex;
import lib.io.Reader;

public class Exercise2b {
    
    public static void main(String args[]) throws IOException {
        Reader fileReader = new Reader();
        Parser graphParser = new Parser();

        String graphDescription = fileReader.readFile("input/Dijkstra.txt");
        System.out.println("Depth First for Dijkstra:");
        traverseWithDepthFirst(graphParser.createDirectedGraph(graphDescription));
        System.out.println("\n");
        
        graphDescription = fileReader.readFile("input/Euler1.txt");
        System.out.println("Depth First for Euler1:");
        traverseWithDepthFirst(graphParser.createDirectedGraph(graphDescription));
        System.out.println("\n");
        
        graphDescription = fileReader.readFile("input/Euler2.txt");
        System.out.println("Depth First for Euler2:");
        traverseWithDepthFirst(graphParser.createDirectedGraph(graphDescription));
        System.out.println("\n");
        
    }
    
    public static void traverseWithDepthFirst(Graph<Vertex, Edge> graph) {
        DepthFirstIterator<Vertex,Edge> depthFirstIterator = new DepthFirstIterator<Vertex,Edge>(graph); 
        
        depthFirstIterator.addTraversalListener(new PrintingTraversalListener());
        
        while(depthFirstIterator.hasNext())
            depthFirstIterator.next();
    }
    
    
}
