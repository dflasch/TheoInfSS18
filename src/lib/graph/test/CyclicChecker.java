package lib.graph.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import org.jgrapht.Graph;

import lib.graph.Edge;
import lib.graph.Vertex;

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
        initialize();

        ArrayList<Vertex> allVertices = new ArrayList<Vertex>();
        allVertices.addAll(graph.vertexSet());

        if (allVertices.size() < 2)
            return false;

        if (allVertices.size() == 2 && isCyclic(allVertices.get(0), allVertices.get(1)))
            return true;
        
        Iterator<Vertex> verticeIterator = allVertices.iterator();
        while(verticeIterator.hasNext() && !isCyclic)
            dfs(verticeIterator.next());

        return isCyclic;
    }

    private void dfs(Vertex vertex) {
        if (finishedVertexes.contains(vertex))
            return;

        if (vistedVertexes.contains(vertex)) {
            isCyclic = true;
            return;
        }

        vistedVertexes.add(vertex);
        
        Set<Vertex> successorVertices = getSuccessorsFor(vertex);
        forgetCallingVertexIn(successorVertices);
        
        for (Vertex eachVertex : successorVertices) {
            rememberCallingVertex(vertex);
            dfs(eachVertex);
        }

        finishedVertexes.add(vertex);

    }

    private boolean isCyclic(Vertex firstVertex, Vertex secondVertex) {
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

    private Set<Vertex> getSuccessorsFor(Vertex vertex) {
        Set<Edge> allEdgesOfVertex = graph.edgesOf(vertex);
        Set<Vertex> vertices = new HashSet<Vertex>();

        for (Edge eachEdge : allEdgesOfVertex) {
            vertices.add(eachEdge.getTo());
            if (graph.getType().isUndirected())
                vertices.add(eachEdge.getFrom());
        }

        vertices.remove(vertex);

        return vertices;
    }

    private void rememberCallingVertex(Vertex vertex) {
        vertexStack.push(vertex);
    }

    private void forgetCallingVertexIn(Set<Vertex> vertexes) {
        if (!vertexStack.empty())
            vertexes.remove(vertexStack.pop());
    }

    private void initialize() {
        this.vistedVertexes = new HashSet<Vertex>();
        this.finishedVertexes = new HashSet<Vertex>();
        this.vertexStack = new Stack<Vertex>();
        this.isCyclic = false;
    }
}
