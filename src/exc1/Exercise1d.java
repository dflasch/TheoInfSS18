package exc1;

import java.io.IOException;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.io.ExportException;

import lib.Edge;
import lib.Exporter;
import lib.Parser;
import lib.Reader;
import lib.Vertex;

public class Exercise1d {

    public static void main(String[] args) throws ExportException, IOException {
        Reader fileReader = new Reader();
        Parser graphParser = new Parser();
        
        String graphDescription = fileReader.readFile("input/Dijkstra.txt");
        
        DefaultDirectedWeightedGraph<Vertex, Edge> graph = graphParser.createDirectedWeightedGraph(graphDescription);

        new Exporter(graph).printToConsole().printToFile("output/exercise-1-d.dot");
        
    }

}
