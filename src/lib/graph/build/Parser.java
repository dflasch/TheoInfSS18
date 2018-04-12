package lib.graph.build;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import lib.graph.Edge;
import lib.graph.Vertex;

public class Parser {
    private static final String VERTEX_STR = "knoten";
    private static final String EDGE_STR = "kante";
    private static final String NEWLINE = "\\r?\\n";
    private static final String BLANKS = "\\s+";
    
    public static GraphDescription parseDescription(String graphDescription) {
        HashMap<String, Vertex> verticesMap = new HashMap<String, Vertex>();
        ArrayList<Edge> edges = new ArrayList<Edge>();
        HashMap<Edge, Double> weights = new HashMap<Edge, Double>();

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
        
        return new GraphDescription(verticesMap, edges, weights);
    }

}
