package lib.graph.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;

import lib.graph.Edge;
import lib.graph.Vertex;
import lib.graph.test.CyclicChecker;

public class Kruskal {
    private Graph<Vertex, Edge> graph;
    private List<Edge> edgesAscSortedByWeight;

    public Kruskal(Graph<Vertex, Edge> graph) {
        this.graph = graph;
    }

    public Graph<Vertex, Edge> createMinimumSpanningTree() {
        SimpleWeightedGraph<Vertex, Edge> minimumSpanningTree;
        CyclicChecker cycleChecker;

        edgesAscSortedByWeight = new ArrayList<Edge>(graph.edgeSet());
        Collections.sort(edgesAscSortedByWeight, new EdgeWeightComparator());

        minimumSpanningTree = new SimpleWeightedGraph<Vertex, Edge>(Edge.class);
        for (Vertex eachVertex : graph.vertexSet())
            minimumSpanningTree.addVertex(eachVertex);
        
        cycleChecker = new CyclicChecker(minimumSpanningTree);
        
        while (!edgesAscSortedByWeight.isEmpty()) {
            Edge possibleEdge = edgesAscSortedByWeight.get(0);
            edgesAscSortedByWeight.remove(0);
            
            minimumSpanningTree.addEdge(possibleEdge.getFrom(), possibleEdge.getTo(),possibleEdge);
            if (cycleChecker.isCyclic())
                minimumSpanningTree.removeEdge(possibleEdge);
            
        }

        return minimumSpanningTree;
    }

    private class EdgeWeightComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            double firstWeight = graph.getEdgeWeight(o1);
            double secondWEight = graph.getEdgeWeight(o2);
            return Double.compare(firstWeight, secondWEight);
        }

    }

}
