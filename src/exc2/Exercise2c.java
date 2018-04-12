package exc2;

import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.io.ExportException;

import lib.graph.Edge;
import lib.graph.Vertex;
import lib.graph.VisitListener;
import lib.graph.build.GraphBuilder;
import lib.graph.build.GraphDescription;
import lib.graph.build.Parser;
import lib.graph.test.CyclicChecker;
import lib.io.Reader;

public class Exercise2c {

    public static void main(String args[]) throws IOException, ExportException {

        GraphDescription euler1GraphDescription = Parser.parseDescription(new Reader().readFile("input/Euler1.txt"));
        GraphDescription euler2GraphDescription = Parser.parseDescription(new Reader().readFile("input/Euler2.txt"));
        GraphDescription dijkstraGraphDescription = Parser.parseDescription(new Reader().readFile("input/Dijkstra.txt"));
        
        VisitListener visitListener = new VisitListenerPrinter();
        CyclicChecker cyclicChecker;

        Graph<Vertex, Edge> graph = new GraphBuilder(euler1GraphDescription).buildUndirectedGraph();
        cyclicChecker = new CyclicChecker(graph);
        cyclicChecker.setVisitVertexListener(visitListener);
        if (cyclicChecker.isCyclic())
            System.out.println("Euler1 is cyclic");
        else
            System.out.println("Euler1 is not cyclic");

        graph = new GraphBuilder(euler2GraphDescription).buildUndirectedGraph();
        cyclicChecker = new CyclicChecker(graph);
        cyclicChecker.setVisitVertexListener(visitListener);
        if (cyclicChecker.isCyclic())
            System.out.println("Euler2 is cyclic");
        else
            System.out.println("Euler2 is not cyclic");

        graph = new GraphBuilder(dijkstraGraphDescription).buildUndirectedGraph();
        cyclicChecker = new CyclicChecker(graph);
        cyclicChecker.setVisitVertexListener(visitListener);
        if (cyclicChecker.isCyclic())
            System.out.println("Dijkstra is cyclic");
        else
            System.out.println("Dijkstra is not cyclic");
    }

    static class VisitListenerPrinter implements VisitListener {

        @Override
        public void onVisit(Vertex vertex) {
            System.out.print(vertex.getName() + " ");
        }

    }

}
