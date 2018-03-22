package lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;

public class Parser {

    private final String VERTEX_STR = "knoten";
    private final String EDGE_STR = "kante";
    private final String NEWLINE = "\\r?\\n";
    private final String BLANKS = "\\s+";

    private HashMap<String, Vertex> verticesMap;
    private ArrayList<Edge> edges;
    private HashMap<Edge, Double> weights;

    public Parser() {
        this.verticesMap = new HashMap<String, Vertex>();
        this.edges = new ArrayList<Edge>();
        this.weights = new HashMap<Edge, Double>();
    }

    public DefaultDirectedGraph<Vertex, Edge> createDirectedGraph(String graphDescription) {
        DefaultDirectedGraph<Vertex, Edge> graph = new DefaultDirectedGraph<Vertex, Edge>(Edge.class);

        parse(graphDescription);

        for (Vertex eachVertex : verticesMap.values())
            graph.addVertex(eachVertex);

        for (Edge eachEdge : edges)
            graph.addEdge(eachEdge.getFrom(), eachEdge.getTo(), eachEdge);

        return graph;

    }

    public DefaultDirectedWeightedGraph<Vertex, Edge> createDirectedWeightedGraph(String graphDescription) {

        DefaultDirectedWeightedGraph<Vertex, Edge> graph = new DefaultDirectedWeightedGraph<Vertex, Edge>(Edge.class);

        parse(graphDescription);

        for (Vertex eachVertex : verticesMap.values())
            graph.addVertex(eachVertex);

        for (Edge eachEdge : edges)
            graph.addEdge(eachEdge.getFrom(), eachEdge.getTo(), eachEdge);

        for (Edge eachEdgeWithWeight : weights.keySet())
            graph.setEdgeWeight(eachEdgeWithWeight, weights.get(eachEdgeWithWeight));

        return graph;

    }

    public void parse(String graphDescription) {
        this.verticesMap.clear();
        this.edges.clear();
        this.weights.clear();

        for (String eachLine : graphDescription.split(NEWLINE)) {
            String[] lineTokens = eachLine.split(BLANKS);
            Queue<String> tokens = new LinkedList<String>(Arrays.asList(lineTokens));

            String rootToken = tokens.poll();
            if (rootToken.equals(VERTEX_STR))
                verticesMap.put(tokens.peek(), new Vertex(tokens.poll(), tokens.poll()));

            if (rootToken.equals(EDGE_STR)) {
                Edge eachEdge = new Edge(verticesMap.get(tokens.poll()), verticesMap.get(tokens.poll()));
                edges.add(eachEdge);

                if (tokens.peek() != null)
                    weights.put(eachEdge, Double.parseDouble(tokens.poll()));
            }

        }

    }

}
