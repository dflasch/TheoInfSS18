package lib.graph.test;


import org.jgrapht.Graph;
import org.jgrapht.alg.ConnectivityInspector;

import lib.graph.Edge;
import lib.graph.Vertex;

public class EulerianChecker {
    
    public static boolean isGraphEulerian(Graph<Vertex,Edge> graph) {
        
        if(!isGraphConnected(graph))
            return false;
        
        for(Vertex eachVertex : graph.vertexSet())
            if(graph.degreeOf(eachVertex) % 2 != 0)
                return false;
        
        return true;
    }
    
    public static boolean hasGraphEulerianPath(Graph<Vertex,Edge> graph) {
        int numberOfOddDegrees = 0;
        
        if(!isGraphConnected(graph))
            return false;
            
        for(Vertex eachVertex : graph.vertexSet())
            if(graph.degreeOf(eachVertex) % 2 != 0)
                numberOfOddDegrees++;
        
        if(numberOfOddDegrees == 0 || numberOfOddDegrees == 2)
            return true;
        
        return false;
        
    }
    
    public static boolean isGraphConnected(Graph<Vertex,Edge> graph) {
        return new ConnectivityInspector<Vertex, Edge>(graph).isGraphConnected();
    }
    
}
