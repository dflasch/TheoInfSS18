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
import lib.io.Exporter;
import lib.io.Reader;

public class Exercise4a {
    
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

        new Exporter(FlussGraph).printToFile("output/Ex4/Fluss-Untouched.dot", true);

        double maxFlow = fordFulkersonAlgorithm.calculateMaxFlow(source, target);
        System.out.println("Maximum Flow of Fluss is: " + maxFlow);

        new Exporter(FlussGraph).printToFile("output/Ex4/Fluss-Maniuplated.dot", true);
    }

    private static void doFluss2() throws IOException, ExportException {
        GraphDescription FlussDescription = Parser.parseDescription(new Reader().readFile("input/Fluss2.txt"));
        Graph<Vertex, Edge> FlussGraph = new GraphBuilder(FlussDescription).buildDirectedWeightedMultigraph();

        FordFulkerson fordFulkersonAlgorithm = new FordFulkerson(FlussGraph);

        Vertex source = FlussDescription.getVerticesMap().get("S");
        Vertex target = FlussDescription.getVerticesMap().get("T");

        new Exporter(FlussGraph).printToFile("output/Ex4/Fluss2-Untouched.dot", true);

        double maxFlow = fordFulkersonAlgorithm.calculateMaxFlow(source, target);
        System.out.println("Maximum Flow of Fluss2 is: " + maxFlow);

        new Exporter(FlussGraph).printToFile("output/Ex4/Fluss2-Maniuplated.dot", true);
    }

}
