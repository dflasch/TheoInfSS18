package lib.graph.build;

import java.util.List;
import java.util.Map;

import lib.graph.Edge;
import lib.graph.Vertex;

public class GraphDescription {
    private Map<String, Vertex> verticesMap;
    private List<Edge> edges;
    private Map<Edge, Double> weights;
    
    public GraphDescription(Map<String, Vertex> verticesMap,List<Edge> edges, Map<Edge, Double> weights) {
        this.verticesMap = verticesMap;
        this.edges = edges;
        this.weights = weights;
    }
    
    public Map<String, Vertex> getVerticesMap(){
        return this.verticesMap;
    }
    
    public List<Edge> getEdges(){
        return this.edges;
    }
    
    public Map<Edge, Double> getWeights(){
        return this.weights;
    }
    
}
