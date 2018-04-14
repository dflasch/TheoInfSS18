package lib.graph.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;

import lib.graph.Edge;
import lib.graph.Vertex;

public class Djisktra {

    private Graph<Vertex, Edge> graph;

    private Map<Vertex, Double> distances;
    private Map<Vertex, Vertex> predecessors;
    private ArrayList<Vertex> verticesToProcess;

    public Djisktra(Graph<Vertex, Edge> graph) {
        this.graph = graph;
    }

    public double findShortestPathBetween(Vertex source, Vertex target) {
        doDjikstra(source);
        return distances.get(target);
    }

    private Map<Vertex, Vertex> doDjikstra(Vertex source) {
        Vertex vertexToProcess;
        List<Vertex> neighborsOfVertexToProcess;
        init(source);

        while (!verticesToProcess.isEmpty()) {
            Collections.sort(verticesToProcess, new DistanceComparator());
            vertexToProcess = verticesToProcess.get(0);
            verticesToProcess.remove(0);

            neighborsOfVertexToProcess = Graphs.neighborListOf(graph, vertexToProcess);
            for (Vertex eachVertex : neighborsOfVertexToProcess)
                if (verticesToProcess.contains(eachVertex))
                    distanceUpdate(vertexToProcess, eachVertex);

        }

        return predecessors;
    }

    private void init(Vertex source) {
        distances = new HashMap<Vertex, Double>();
        predecessors = new HashMap<Vertex, Vertex>();
        verticesToProcess = new ArrayList<Vertex>();

        for (Vertex eachVertex : graph.vertexSet()) {
            distances.put(eachVertex, Double.MAX_VALUE);
            predecessors.put(eachVertex, null);
            verticesToProcess.add(eachVertex);
        }

        distances.put(source, 0.0);
    }

    private void distanceUpdate(Vertex predessecor, Vertex target) {
        if (!graph.containsEdge(predessecor, target))
            return;

        double alternative = distances.get(predessecor) + graph.getEdgeWeight(graph.getEdge(predessecor, target));
        if (alternative < distances.get(target)) {
            distances.put(target, alternative);
            predecessors.put(target, predessecor);
        }
    }

    private class DistanceComparator implements Comparator<Vertex> {

        @Override
        public int compare(Vertex first, Vertex second) {
            return distances.get(first).compareTo(distances.get(second));
        }

    }

}
