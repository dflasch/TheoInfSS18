package lib.graph.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import lib.graph.Edge;
import lib.graph.Vertex;
import lib.graph.algorithms.pathFinder.EdgeRemainingCapacityCondition;
import lib.graph.algorithms.pathFinder.PathFinder;

public class FordFulkerson {

    private DirectedWeightedMultigraph<Vertex, Edge> graph;
    private Map<Edge, Double> currentFlow;
    private PathFinder pathFinder;
    private Graph<Vertex, Edge> originalGraph;

    public FordFulkerson(Graph<Vertex, Edge> graph) {
        this.originalGraph = graph;
    }

    public boolean solvesCirculationDemand() {
        double neededFlow;
        Vertex source, target;
        
        buildBasicGraph();
        
        source = new Vertex("S*");
        target = new Vertex("T*");
        neededFlow = addSourceAndTargetToGraph(source, target);

        initialize();

        if (neededFlow == doFordFulkerson(source, target))
            return true;

        return false;

    }

    public int calculateDisjointPathes(Vertex source, Vertex target) {
        buildGraphForDisjointPathes();

        initialize();

        return (int) doFordFulkerson(source, target);

    }

    public double calculateMaxFlow(Vertex source, Vertex target) {
        buildBasicGraph();

        initialize();

        return doFordFulkerson(source, target);

    }

    private void buildGraphForDisjointPathes() {
        buildBasicGraph();

        for (Edge eachEdge : this.graph.edgeSet())
            this.graph.setEdgeWeight(eachEdge, 1.0);
    }

    private void buildBasicGraph() {
        this.graph = new DirectedWeightedMultigraph<Vertex, Edge>(Edge.class);
        Graphs.addGraph(this.graph, originalGraph);
    }

    private double doFordFulkerson(Vertex source, Vertex target) {
        createPathFinderWith(new EdgeRemainingCapacityCondition(graph, currentFlow));
        List<Edge> path = pathFinder.findEdgesOfAnyPath(source, target);

        while (path.size() > 0) {
            double maxFlow = getMaximumFlowOf(path);
            for (Edge eachEdgeInPath : path) {
                increaseFlowTo(eachEdgeInPath, maxFlow);
                decreaseFlowTo(getEdgeBackwards(eachEdgeInPath), maxFlow);
            }

            path = pathFinder.findEdgesOfAnyPath(source, target);
        }

        return calcFlowOfIncomingEdgesOf(target);
    }

    private double calcFlowOfIncomingEdgesOf(Vertex target) {
        double maxFlow = 0.0;

        List<Vertex> allNeighbors = Graphs.predecessorListOf(graph, target);
        for (Vertex eachVertex : allNeighbors) {
            Edge eachEdge = graph.getEdge(eachVertex, target);
            maxFlow += this.currentFlow.get(eachEdge);
        }
        return maxFlow;
    }

    private void initialize() {
        setCurrentFlowInAllEdgesToZero();
        createBackwardEdgesAndSetCurrentFlowToWeight();
    }

    private void createPathFinderWith(EdgeRemainingCapacityCondition considerEdgeCapacity) {
        pathFinder = new PathFinder(graph);
        pathFinder.addEdgeCondition(considerEdgeCapacity);
    }

    private void createBackwardEdgesAndSetCurrentFlowToWeight() {
        Set<Edge> originalEdges = new HashSet<Edge>(graph.edgeSet());
        for (Edge eachEdge : originalEdges) {
            Edge newEdge = new Edge(graph.getEdgeTarget(eachEdge), graph.getEdgeSource(eachEdge));
            graph.addEdge(newEdge.getFrom(), newEdge.getTo(), newEdge);
            graph.setEdgeWeight(newEdge, graph.getEdgeWeight(eachEdge));
            currentFlow.put(newEdge, graph.getEdgeWeight(eachEdge));
        }
    }

    private double addSourceAndTargetToGraph(Vertex source, Vertex target) {
        Set<Vertex> verticesToProcess = new HashSet<Vertex>(graph.vertexSet());
        
        double neededFlow = 0.0;
        this.graph.addVertex(source);
        this.graph.addVertex(target);

        for (Vertex eachVertex : verticesToProcess) {
            double vertexValue = Double.parseDouble(eachVertex.getData());
            if (vertexValue < 0.0) {
                Edge newEdge = new Edge(source, eachVertex);
                this.graph.addEdge(source, eachVertex, newEdge);
                this.graph.setEdgeWeight(newEdge, Math.abs(vertexValue));
            } else if (vertexValue > 0.0) {
                Edge newEdge = new Edge(eachVertex, target);
                this.graph.addEdge(eachVertex, target, newEdge);
                this.graph.setEdgeWeight(newEdge, vertexValue);
                neededFlow += vertexValue;
            }
        }
        return neededFlow;
    }

    private void setCurrentFlowInAllEdgesToZero() {
        currentFlow = new HashMap<Edge, Double>();
        for (Edge eachEdge : graph.edgeSet()) {
            currentFlow.put(eachEdge, 0.0);
        }
    }

    private void increaseFlowTo(Edge edgeToIncreaseFlow, double flow) {
        currentFlow.put(edgeToIncreaseFlow, currentFlow.get(edgeToIncreaseFlow) + flow);
    }

    private void decreaseFlowTo(Edge edgeToDecreaseFlow, double flow) {
        currentFlow.put(edgeToDecreaseFlow, currentFlow.get(edgeToDecreaseFlow) - flow);
    }

    private Edge getEdgeBackwards(Edge edgeToGetBackward) {
        return graph.getEdge(graph.getEdgeTarget(edgeToGetBackward), graph.getEdgeSource(edgeToGetBackward));
    }

    private double getMaximumFlowOf(List<Edge> path) {
        double maxFlowInPath = 0.0;

        maxFlowInPath = Double.MAX_VALUE;
        for (Edge eachEdge : path)
            maxFlowInPath = maxFlowInPath > getRemainingFlow(eachEdge) ? getRemainingFlow(eachEdge) : maxFlowInPath;

        return maxFlowInPath;
    }

    private double getRemainingFlow(Edge edge) {
        if (!currentFlow.containsKey(edge))
            return 0.0;

        return graph.getEdgeWeight(edge) - currentFlow.get(edge);

    }

}
