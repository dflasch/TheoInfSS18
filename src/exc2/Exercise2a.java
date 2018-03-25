package exc2;

import java.io.IOException;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.io.ExportException;

import lib.graph.Edge;
import lib.graph.Parser;
import lib.graph.Vertex;
import lib.graph.test.EulerianChecker;
import lib.io.Exporter;
import lib.io.Reader;

public class Exercise2a {

    public static void main(String args[]) throws IOException, ExportException {
        Reader fileReader = new Reader();
        Parser graphParser = new Parser();

        String graphDescription = fileReader.readFile("input/Euler1.txt");
        DefaultDirectedGraph<Vertex, Edge> graph = graphParser.createDirectedGraph(graphDescription);
        new Exporter(graph).printToFile("output/exercise-2-a-euler-1.dot",false);
        
        if(EulerianChecker.isGraphEulerian(graph)) 
            System.out.println("Graph Euler1 is eulerian");
        else
            System.out.println("Graph Euler1 is not eulerian");
        
        if(EulerianChecker.hasGraphEulerianPath(graph)) 
            System.out.println("Graph Euler1 has eulerian path");
        else
            System.out.println("Graph Euler1 has no eulerian path");
        
        graphDescription = fileReader.readFile("input/Euler2.txt");
        graph = graphParser.createDirectedGraph(graphDescription);
        new Exporter(graph).printToFile("output/exercise-2-a-euler-2.dot",false);
        
        if(EulerianChecker.isGraphEulerian(graph)) 
            System.out.println("Graph Euler2 is eulerian");
        else
            System.out.println("Graph Euler2 is not eulerian");
        
        if(EulerianChecker.hasGraphEulerianPath(graph)) 
            System.out.println("Graph Euler2 has eulerian path");
        else
            System.out.println("Graph Euler2 has no eulerian path");
    }
    
}
