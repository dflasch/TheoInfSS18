package exc2;

import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.io.ExportException;

import lib.graph.Edge;
import lib.graph.Parser;
import lib.graph.Vertex;
import lib.graph.test.CyclicChecker;
import lib.io.Exporter;
import lib.io.Reader;

public class Exercise2c {
    
    public static void main(String args[]) throws IOException, ExportException {
        Reader fileReader = new Reader();
        Parser graphParser = new Parser();

        String graphDescription = fileReader.readFile("input/Euler1.txt");
        Graph<Vertex, Edge> graph = graphParser.createUndirectedGraph(graphDescription);
        new Exporter(graph).printToFile("output/exercise-2-c-euler-1.dot",false);
        
        if(new CyclicChecker(graph).isCyclic())
            System.out.println("Euler1 is cyclic");
        else
            System.out.println("Euler1 is not cyclic");
        
        graphDescription = fileReader.readFile("input/Euler2.txt");
        graph = graphParser.createUndirectedGraph(graphDescription);
        if(new CyclicChecker(graph).isCyclic())
            System.out.println("Euler2 is cyclic");
        else
            System.out.println("Euler2 is not cyclic");
        
        graphDescription = fileReader.readFile("input/Dijkstra.txt");
        graph = graphParser.createUndirectedGraph(graphDescription);
        if(new CyclicChecker(graph).isCyclic())
            System.out.println("Dijkstra is cyclic");
        else
            System.out.println("Dijkstra is not cyclic");
    }

}
