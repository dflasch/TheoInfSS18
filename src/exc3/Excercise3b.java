package exc3;

import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.io.ExportException;

import lib.graph.Edge;
import lib.graph.Vertex;
import lib.graph.algorithms.Kruskal;
import lib.graph.build.GraphBuilder;
import lib.graph.build.GraphDescription;
import lib.graph.build.Parser;
import lib.io.Exporter;
import lib.io.Reader;

public class Excercise3b {
    
    public static void main(String[] args) throws IOException, ExportException {
        GraphDescription dijkstraGraphDescription = Parser.parseDescription(new Reader().readFile("input/Dijkstra.txt"));
        Graph<Vertex,Edge> djikstraGraph = new GraphBuilder(dijkstraGraphDescription).buildUndirectedWeightedGraph();
        
        Kruskal kruskalAlgorithm = new Kruskal(djikstraGraph);
        
        Graph<Vertex, Edge> minmumSpanningTree = kruskalAlgorithm.createMinimumSpanningTree();
        
        new Exporter(minmumSpanningTree).printToConsole().printToFile("output/kruskal-min-span-tree.dot", true);
        
        
    }
    
}
