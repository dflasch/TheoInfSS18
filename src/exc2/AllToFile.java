package exc2;

import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.io.ExportException;

import lib.graph.Edge;
import lib.graph.Vertex;
import lib.graph.build.GraphBuilder;
import lib.graph.build.GraphDescription;
import lib.graph.build.Parser;
import lib.io.Exporter;
import lib.io.Reader;

public class AllToFile {

    public static void main(String args[]) throws IOException, ExportException {
        GraphDescription euler1GraphDescription = Parser.parseDescription(new Reader().readFile("input/Euler1.txt"));
        GraphDescription euler2GraphDescription = Parser.parseDescription(new Reader().readFile("input/Euler2.txt"));
        GraphDescription dijkstraGraphDescription = Parser.parseDescription(new Reader().readFile("input/Dijkstra.txt"));
        
        printGraphUndirected(euler1GraphDescription, "output/undirected/Euler1.dot");
        printGraphUndirected(euler2GraphDescription, "output/undirected/Euler2.dot");
        printGraphUndirected(dijkstraGraphDescription, "output/undirected/Dijkstra.dot");
        
        printGraphUndirectedWeighted(euler1GraphDescription, "output/undirected-weighted/Euler1.dot");
        printGraphUndirectedWeighted(euler2GraphDescription, "output/undirected-weighted/Euler2.dot");
        printGraphUndirectedWeighted(dijkstraGraphDescription, "output/undirected-weighted/Dijkstra.dot");
        
    }

    public static void printGraphUndirected(GraphDescription graphDescription, String path)
            throws ExportException, IOException {
        Graph<Vertex, Edge> myGraph = new GraphBuilder(graphDescription).buildUndirectedGraph();
        new Exporter(myGraph).printToConsole().printToFile(path, false);

    }

    public static void printGraphUndirectedWeighted(GraphDescription graphDescription, String path)
            throws ExportException, IOException {
        Graph<Vertex, Edge> myGraph = new GraphBuilder(graphDescription).buildUndirectedWeightedGraph();
        new Exporter(myGraph).printToConsole().printToFile(path, true);
    }

}
