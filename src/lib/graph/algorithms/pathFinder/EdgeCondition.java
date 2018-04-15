package lib.graph.algorithms.pathFinder;

import lib.graph.Edge;

public interface EdgeCondition {
    
    public boolean isEdgeValid(Edge edge);
    
}
