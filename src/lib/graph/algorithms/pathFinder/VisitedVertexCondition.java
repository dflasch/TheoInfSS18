package lib.graph.algorithms.pathFinder;

import java.util.Set;
import lib.graph.Vertex;

public class VisitedVertexCondition implements VertexCondition {

    private Set<Vertex> visitedVertices;

    public VisitedVertexCondition(Set<Vertex> visitedVertices) {
        this.visitedVertices = visitedVertices;
    }

    public boolean isVertexValid(Vertex vertex) {
        return !this.visitedVertices.contains(vertex);
    }
    
}
