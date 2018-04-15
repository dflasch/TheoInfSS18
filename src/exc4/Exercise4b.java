package exc4;

import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.io.ExportException;

import lib.graph.Edge;
import lib.graph.Vertex;
import lib.graph.algorithms.FordFulkerson;
import lib.graph.build.GraphBuilder;
import lib.graph.build.GraphDescription;
import lib.graph.build.Parser;
import lib.io.Reader;

public class Exercise4b {
    
    public static void main(String[] args) throws IOException, ExportException {
        doFluss1();
        doFluss2();
    }

    private static void doFluss1() throws ExportException, IOException {
        GraphDescription FlussDescription = Parser.parseDescription(new Reader().readFile("input/Fluss.txt"));
        Graph<Vertex, Edge> FlussGraph = new GraphBuilder(FlussDescription).buildDirectedWeightedMultigraph();

        FordFulkerson fordFulkersonAlgorithm = new FordFulkerson(FlussGraph);

        Vertex source = FlussDescription.getVerticesMap().get("S");
        Vertex target = FlussDescription.getVerticesMap().get("T");

        int countDisjointPathes = fordFulkersonAlgorithm.calculateDisjointPathes(source, target);
        System.out.println("Cound disjoint pathes in Fluss is: " + countDisjointPathes);

    }

    private static void doFluss2() throws IOException, ExportException {
        GraphDescription FlussDescription = Parser.parseDescription(new Reader().readFile("input/Fluss2.txt"));
        Graph<Vertex, Edge> FlussGraph = new GraphBuilder(FlussDescription).buildDirectedWeightedMultigraph();

        FordFulkerson fordFulkersonAlgorithm = new FordFulkerson(FlussGraph);

        Vertex source = FlussDescription.getVerticesMap().get("S");
        Vertex target = FlussDescription.getVerticesMap().get("T");

        int countDisjointPathes = fordFulkersonAlgorithm.calculateDisjointPathes(source, target);
        System.out.println("Cound disjoint pathes in Fluss2 is: " + countDisjointPathes);

    }

}
