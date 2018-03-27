package lib.graph.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.jgrapht.Graph;

import lib.graph.Edge;
import lib.graph.Vertex;
import lib.graph.VisitListener;

public class CyclicChecker {

    private Graph<Vertex, Edge> graph;
    private Set<Vertex> vistedVertexes;
    private Set<Vertex> finishedVertexes;
    private boolean isCyclic;
    private VisitListener visitVertexListener;

    public CyclicChecker(Graph<Vertex, Edge> graph) {
        this.graph = graph;
    }
    
    public void setVisitVertexListener(VisitListener listener) {
        this.visitVertexListener = listener;
    }
    
    public boolean isCyclic() {
        initialize();

        ArrayList<Vertex> allVertices = new ArrayList<Vertex>();
        allVertices.addAll(graph.vertexSet());

        if (allVertices.size() < 2)
            return false;

        if (allVertices.size() == 2 && hasBidirectionalConnectionBetween(allVertices.get(0), allVertices.get(1)))
            return true;
        
        Iterator<Vertex> verticeIterator = allVertices.iterator();
        while (verticeIterator.hasNext() && !isCyclic)
            dfs(verticeIterator.next());

        return isCyclic;
    }
    
    private void dfs(Vertex vertex) {
        dfs(vertex, null);
    }
    
    private void dfs(Vertex currentVertex, Vertex lastVertex) {
        if (finishedVertexes.contains(currentVertex) || isCyclic)
            return;
        
        if(visitVertexListener != null)
            visitVertexListener.onVisit(currentVertex);
        
        if (vistedVertexes.contains(currentVertex)) {
            isCyclic = true;
            return;
        }
        
        vistedVertexes.add(currentVertex);
        
        for (Vertex eachVertex : getSuccessorsFor(currentVertex,lastVertex))
            dfs(eachVertex,currentVertex);
            
        finishedVertexes.add(currentVertex);

    }

    private boolean hasBidirectionalConnectionBetween(Vertex firstVertex, Vertex secondVertex) {
        if (graph.getType().isUndirected())
            return true;

        Set<Vertex> targetVerticesOfFirstVertex = getTargetVerticesFor(graph.edgesOf(firstVertex));
        Set<Vertex> targetVerticesOfSecondVertex = getTargetVerticesFor(graph.edgesOf(secondVertex));

        if (targetVerticesOfFirstVertex.contains(secondVertex) && targetVerticesOfSecondVertex.contains(firstVertex))
            return true;
        return false;
    }

    private Set<Vertex> getTargetVerticesFor(Set<Edge> edges) {
        Set<Vertex> vertices = new HashSet<Vertex>();

        for (Edge eachEdge : edges)
            vertices.add(eachEdge.getTo());

        return vertices;
    }

    private Set<Vertex> getSuccessorsFor(Vertex currentVertex, Vertex lastVertex) {
        Set<Edge> allEdgesOfVertex = graph.edgesOf(currentVertex);
        Set<Vertex> vertices = new HashSet<Vertex>();

        for (Edge eachEdge : allEdgesOfVertex) {
            vertices.add(eachEdge.getTo());
            if (graph.getType().isUndirected())
                vertices.add(eachEdge.getFrom());
        }

        vertices.remove(currentVertex);
        if(lastVertex != null)
            vertices.remove(lastVertex);
        
        return vertices;
    }

    private void initialize() {
        this.vistedVertexes = new HashSet<Vertex>();
        this.finishedVertexes = new HashSet<Vertex>();
        this.isCyclic = false;
    }
}
