package exc1;

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

public class Exercise1d {

    public static void main(String[] args) throws ExportException, IOException {
        
        GraphDescription graphDescription = Parser.parseDescription(new Reader().readFile("input/Dijkstra.txt"));

        Graph<Vertex, Edge> myGraph = new GraphBuilder(graphDescription).buildDirectedWeightedGraph();

        new Exporter(myGraph).printToConsole().printToFile("output/exercise-1-d.dot",true);

    }

}
