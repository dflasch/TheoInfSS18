package lib.graph.build;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import lib.graph.Edge;
import lib.graph.Vertex;

public class GraphBuilder {

    private List<GraphDescription> graphDescriptions;

    public GraphBuilder() {
        graphDescriptions = new ArrayList<GraphDescription>();
    }
    
    public GraphBuilder(GraphDescription graphDescription) {
        this();
        graphDescriptions.add(graphDescription);
    }
    
    public DirectedWeightedMultigraph<Vertex,Edge> buildDirectedWeightedMultigraph(){
        DirectedWeightedMultigraph<Vertex, Edge> graph = new DirectedWeightedMultigraph<Vertex, Edge>(Edge.class);

        addVerticesEdgesAndWeightsTo(graph);

        return graph;
    }
    
    public DefaultDirectedGraph<Vertex,Edge> buildDefaultDirectedGraph(){
        DefaultDirectedGraph<Vertex, Edge> graph = new DefaultDirectedGraph<Vertex, Edge>(Edge.class);

        addVerticesAndEdgesTo(graph);

        return graph;
    }
    
    public SimpleGraph<Vertex, Edge> buildUndirectedGraph() {
        SimpleGraph<Vertex, Edge> graph = new SimpleGraph<Vertex, Edge>(Edge.class);

        addVerticesAndEdgesTo(graph);

        return graph;

    }

    public SimpleWeightedGraph<Vertex, Edge> buildUndirectedWeightedGraph() {
        SimpleWeightedGraph<Vertex, Edge> graph = new SimpleWeightedGraph<Vertex, Edge>(Edge.class);

        addVerticesEdgesAndWeightsTo(graph);

        return graph;

    }

    public SimpleDirectedGraph<Vertex, Edge> buildDirectedGraph() {
        SimpleDirectedGraph<Vertex, Edge> graph = new SimpleDirectedGraph<Vertex, Edge>(Edge.class);

        addVerticesAndEdgesTo(graph);

        return graph;

    }

    public SimpleDirectedWeightedGraph<Vertex, Edge> buildDirectedWeightedGraph() {

        SimpleDirectedWeightedGraph<Vertex, Edge> graph = new SimpleDirectedWeightedGraph<Vertex, Edge>(Edge.class);

        addVerticesEdgesAndWeightsTo(graph);

        return graph;

    }

    public GraphBuilder addDescription(GraphDescription graphDescription) {
        graphDescriptions.add(graphDescription);
        return this;
    }

    private void addVerticesEdgesAndWeightsTo(Graph<Vertex, Edge> graph) {
        addVerticesAndEdgesTo(graph);
        addWeightsTo(graph);
    }

    private void addVerticesAndEdgesTo(Graph<Vertex, Edge> graph) {
        addVerticesTo(graph);
        addEdgesTo(graph);
    }

    private void addVerticesTo(Graph<Vertex, Edge> graph) {
        for (GraphDescription eachGraphDescription : graphDescriptions)
            for (Vertex eachVertex : eachGraphDescription.getVerticesMap().values())
                graph.addVertex(eachVertex);
    }

    private void addEdgesTo(Graph<Vertex, Edge> graph) {
        for (GraphDescription eachGraphDescription : graphDescriptions)
            for (Edge eachEdge : eachGraphDescription.getEdges())
                graph.addEdge(eachEdge.getFrom(), eachEdge.getTo(), eachEdge);
    }

    private void addWeightsTo(Graph<Vertex, Edge> graph) {
        for (GraphDescription eachGraphDescription : graphDescriptions)
            for (Edge eachEdgeWithWeight : eachGraphDescription.getWeights().keySet())
                graph.setEdgeWeight(eachEdgeWithWeight, eachGraphDescription.getWeights().get(eachEdgeWithWeight));
    }

}
