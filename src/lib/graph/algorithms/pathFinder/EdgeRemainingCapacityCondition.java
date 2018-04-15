package lib.graph.algorithms.pathFinder;

import java.util.Map;

import org.jgrapht.Graph;

import lib.graph.Edge;
import lib.graph.Vertex;

public class EdgeRemainingCapacityCondition implements EdgeCondition {
    
    private Graph<Vertex, Edge> graph;
    private Map<Edge, Double> currentFlow;

    public EdgeRemainingCapacityCondition(Graph<Vertex,Edge> graph, Map<Edge,Double> currentFlow ) {
        this.graph = graph;
        this.currentFlow = currentFlow;
    }
    
    @Override
    public boolean isEdgeValid(Edge edge) {
        if(!currentFlow.containsKey(edge))
            return false;
        else 
            if(getRemainingFlow(edge) > 0.0)
                return true;
            else
                return false;
                        
    }
    
    private double getRemainingFlow(Edge edge) {
        if (!currentFlow.containsKey(edge))
            return 0.0;

        return graph.getEdgeWeight(edge) - currentFlow.get(edge);

    }

}
