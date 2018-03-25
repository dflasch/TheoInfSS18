package lib;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import org.jgrapht.Graph;

public class CyclicChecker {

    private Graph<Vertex, Edge> graph;
    private Set<Vertex> vistedVertexes;
    private Set<Vertex> finishedVertexes;
    private boolean isCyclic;
    private Stack<Vertex> vertexStack;

    public CyclicChecker(Graph<Vertex, Edge> graph) {
        this.graph = graph;
    }
    
    public boolean isCyclic() {
        init();
        
        Set<Vertex> allVertices = graph.vertexSet();
        
        if(graph.getType().isUndirected() && allVertices.size() == 2)
            return true;
        
        for(Vertex eachVertex : allVertices)
            dfs(eachVertex);
        
        return isCyclic;
    }
    
    public void dfs(Vertex vertex) {
        if(finishedVertexes.contains(vertex)) return;

        if(vistedVertexes.contains(vertex)) {
            isCyclic = true;
            return;
        }

        vistedVertexes.add(vertex);
        
        for(Vertex eachVertex : getSuccessorsFor(vertex)) {
            if(graph.vertexSet().size() != 2)
                vertexStack.push(vertex);
            dfs(eachVertex);
        }
            
        finishedVertexes.add(vertex);
                    
    }
    
    public Set<Vertex> getSuccessorsFor(Vertex vertex){
        Set<Edge> allEdgesOfVertex = graph.outgoingEdgesOf(vertex);
        Set<Vertex> vertexes = new HashSet<Vertex>();
        
        for(Edge eachEdge : allEdgesOfVertex)
            vertexes.add(eachEdge.getTo());
        
        if(!vertexStack.empty())
            vertexes.remove(vertexStack.pop());
        
        vertexes.remove(vertex);
        
        return vertexes;
    }
    
    public void init() {
        this.vistedVertexes = new HashSet<Vertex>();
        this.finishedVertexes = new HashSet<Vertex>();
        this.isCyclic = false;
        this.vertexStack = new Stack<Vertex>();
    }
}
