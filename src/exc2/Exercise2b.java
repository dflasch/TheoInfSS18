package exc2;

import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.traverse.DepthFirstIterator;

import lib.graph.Edge;
import lib.graph.PrintingTraversalListener;
import lib.graph.Vertex;
import lib.graph.build.GraphBuilder;
import lib.graph.build.GraphDescription;
import lib.graph.build.Parser;
import lib.io.Reader;

public class Exercise2b {
    
    public static void main(String args[]) throws IOException {
        GraphDescription dijkstraGraphDescription = Parser.parseDescription(new Reader().readFile("input/Dijkstra.txt"));
        GraphDescription euler1GraphDescription = Parser.parseDescription(new Reader().readFile("input/Euler1.txt"));
        GraphDescription euler2GraphDescription = Parser.parseDescription(new Reader().readFile("input/Euler2.txt"));

        System.out.println("Depth First for Dijkstra:");
        traverseWithDepthFirst(new GraphBuilder(dijkstraGraphDescription).buildDirectedGraph());
        System.out.println("\n");
        
        System.out.println("Depth First for Euler1:");
        traverseWithDepthFirst(new GraphBuilder(euler1GraphDescription).buildDirectedGraph());
        System.out.println("\n");
        
        System.out.println("Depth First for Euler2:");
        traverseWithDepthFirst(new GraphBuilder(euler2GraphDescription).buildDirectedGraph());
        System.out.println("\n");
        
    }
    
    public static void traverseWithDepthFirst(Graph<Vertex, Edge> graph) {
        DepthFirstIterator<Vertex,Edge> depthFirstIterator = new DepthFirstIterator<Vertex,Edge>(graph); 
        
        depthFirstIterator.addTraversalListener(new PrintingTraversalListener());
        
        while(depthFirstIterator.hasNext())
            depthFirstIterator.next();
    }
    
    
}
