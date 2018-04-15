package lib.graph.algorithms.pathFinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;

import lib.graph.Edge;
import lib.graph.Vertex;

public class PathFinder {
    private Graph<Vertex, Edge> graph;
    private Stack<Vertex> verticesToProcess;
    private Map<Vertex, Vertex> predecessors;
    private Set<Vertex> visitedVertices;
    private List<VertexCondition> vertexConditions;
    private List<EdgeCondition> edgeConditions;
    private Vertex target;
    boolean foundTarget;

    public PathFinder(Graph<Vertex, Edge> graph) {
        this.graph = graph;
        this.vertexConditions = new ArrayList<VertexCondition>();
        this.edgeConditions = new ArrayList<EdgeCondition>();
        this.verticesToProcess = new Stack<Vertex>();
        this.predecessors = new HashMap<Vertex, Vertex>();
        this.visitedVertices = new HashSet<Vertex>();
        this.addVertexCondition(new VisitedVertexCondition(this.visitedVertices));
    }

    public List<Edge> findEdgesOfAnyPath(Vertex source, Vertex target) {
        List<Edge> pathListWithEdges;
        List<Vertex> pathWithVertices;

        pathListWithEdges = new ArrayList<Edge>();
        pathWithVertices = findAnyPath(source, target);

        if (pathWithVertices.size() > 1) {
            Vertex lastVertex = null;
            for (Vertex currentVertex : pathWithVertices) {
                if (lastVertex != null)
                    pathListWithEdges.add(graph.getEdge(lastVertex, currentVertex));
                lastVertex = currentVertex;
            }
        }

        return pathListWithEdges;

    }

    public List<Vertex> findAnyPath(Vertex source, Vertex target) {
        initializeWith(source, target);

        foundTarget = false;
        while (verticesToProcess.isEmpty() == false && foundTarget == false) {
            Vertex currentVertex = verticesToProcess.pop();
            for (Vertex someNeighbor : Graphs.neighborListOf(graph, currentVertex)) {
                if (isVertexValid(someNeighbor) && isEdgeValid(currentVertex, someNeighbor)) {
                    visitVertex(someNeighbor, currentVertex);
                    if (foundTarget(someNeighbor))
                        break;
                }
            }
        }

        return buildPath();

    }

    public void addVertexCondition(VertexCondition condition) {
        this.vertexConditions.add(condition);
    }

    public void addEdgeCondition(EdgeCondition condition) {
        this.edgeConditions.add(condition);
    }

    private List<Vertex> buildPath() {
        ArrayList<Vertex> result = new ArrayList<Vertex>();

        if (foundTarget) {
            result.add(target);
            Vertex predecessor = predecessors.get(target);
            while (predecessor != null) {
                result.add(predecessor);
                predecessor = predecessors.get(predecessor);
            }
        }

        Collections.reverse(result);

        return result;
    }

    private boolean foundTarget(Vertex currentVertex) {
        if (currentVertex.equals(this.target))
            this.foundTarget = true;

        return foundTarget;

    }

    private void initializeWith(Vertex source, Vertex target) {
        this.verticesToProcess.clear();
        this.predecessors.clear();
        this.visitedVertices.clear();
        this.foundTarget = false;
        this.target = target;

        visitVertex(source, null);
    }

    private void setPredeccessor(Vertex predecessor, Vertex vertex) {
        predecessors.put(predecessor, vertex);
    }

    private void visitVertex(Vertex vertexToVisit, Vertex predecessor) {
        if (predecessor != null)
            setPredeccessor(vertexToVisit, predecessor);

        verticesToProcess.push(vertexToVisit);
        visitedVertices.add(vertexToVisit);
    }

    private boolean isVertexValid(Vertex vertex) {
        for (VertexCondition eachCondition : this.vertexConditions)
            if (!eachCondition.isVertexValid(vertex))
                return false;

        return true;
    }

    private boolean isEdgeValid(Vertex source, Vertex target) {
        Edge edgeToCheck;
        edgeToCheck = graph.getEdge(source, target);

        if (edgeToCheck == null)
            return false;

        for (EdgeCondition eachCondition : this.edgeConditions)
            if (!eachCondition.isEdgeValid(edgeToCheck))
                return false;

        return true;
    }

}
