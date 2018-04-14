package lib.graph.build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lib.graph.Edge;
import lib.graph.Vertex;

public class GraphDescription {
    private Map<String, Vertex> verticesMap;
    private List<Edge> edges;
    private Map<Edge, Double> weights;
    
    public GraphDescription() {
        this.verticesMap = new HashMap<String, Vertex>();
        this.edges = new ArrayList<Edge>();
        this.weights = new HashMap<Edge, Double>();
    }
    
    public GraphDescription(Map<String, Vertex> verticesMap,List<Edge> edges, Map<Edge, Double> weights) {
        this.verticesMap = verticesMap;
        this.edges = edges;
        this.weights = weights;
    }
    
    public GraphDescription addVertex(Vertex vertexToAdd) {
        verticesMap.put(vertexToAdd.getName(),vertexToAdd);
        return this;
    }
    
    public GraphDescription addEdge(Edge edgeToAdd) {
        edges.add(edgeToAdd);
        return this;
    }
    
    public GraphDescription addWeight(Double weight, Edge edgeWithWeigt) {
        weights.put(edgeWithWeigt,weight);
        return this;
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
