package exc2;

import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.io.ExportException;

import lib.graph.Edge;
import lib.graph.Vertex;
import lib.graph.build.GraphBuilder;
import lib.graph.build.GraphDescription;
import lib.graph.build.Parser;
import lib.graph.test.EulerianChecker;
import lib.io.Exporter;
import lib.io.Reader;

public class Exercise2a {

    public static void main(String args[]) throws IOException, ExportException {
        GraphDescription Euler1GraphDescription = Parser.parseDescription(new Reader().readFile("input/Euler1.txt"));
        GraphDescription Euler2GraphDescription = Parser.parseDescription(new Reader().readFile("input/Euler2.txt"));

        Graph<Vertex, Edge> graph;
            
        graph = new GraphBuilder(Euler1GraphDescription).buildDirectedGraph();
        new Exporter(graph).printToFile("output/exercise-2-a-euler-1.dot", false);

        if (EulerianChecker.isGraphEulerian(graph))
            System.out.println("Graph Euler1 is eulerian");
        else
            System.out.println("Graph Euler1 is not eulerian");

        if (EulerianChecker.hasGraphEulerianPath(graph))
            System.out.println("Graph Euler1 has eulerian path");
        else
            System.out.println("Graph Euler1 has no eulerian path");

        graph = new GraphBuilder(Euler2GraphDescription).buildDirectedGraph();
        new Exporter(graph).printToFile("output/exercise-2-a-euler-2.dot", false);

        if (EulerianChecker.isGraphEulerian(graph))
            System.out.println("Graph Euler2 is eulerian");
        else
            System.out.println("Graph Euler2 is not eulerian");

        if (EulerianChecker.hasGraphEulerianPath(graph))
            System.out.println("Graph Euler2 has eulerian path");
        else
            System.out.println("Graph Euler2 has no eulerian path");
    }

}
