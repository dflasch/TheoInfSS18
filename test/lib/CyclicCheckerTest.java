package lib;

import static org.junit.Assert.*;

import org.jgrapht.Graph;
import org.jgrapht.graph.AsUndirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.junit.Test;

public class CyclicCheckerTest {

    @Test
    public void returnsCyclicTwoVerticesDirected() {
        Vertex first = new Vertex("1");
        Vertex second = new Vertex("2");
        
        DefaultDirectedGraph<Vertex, Edge> directedGraph = new DefaultDirectedGraph<Vertex, Edge>(Edge.class);
        directedGraph.addVertex(first);
        directedGraph.addVertex(second);
        directedGraph.addEdge(first, second, new Edge(first,second));
        directedGraph.addEdge(second, first, new Edge(second,first));
        
        assertTrue(new CyclicChecker(directedGraph).isCyclic());
        
    }
    
    @Test
    public void returnsCyclicThreeVerticesDirected() {
        Vertex first = new Vertex("1");
        Vertex second = new Vertex("2");
        Vertex third = new Vertex("3");
        
        DefaultDirectedGraph<Vertex, Edge> directedGraph = new DefaultDirectedGraph<Vertex, Edge>(Edge.class);
        directedGraph.addVertex(first);
        directedGraph.addVertex(second);
        directedGraph.addVertex(third);
        directedGraph.addEdge(first, second, new Edge(first,second));
        directedGraph.addEdge(second, third, new Edge(second,third));
        directedGraph.addEdge(third, first, new Edge(third,first));
        
        assertTrue(new CyclicChecker(directedGraph).isCyclic());
        
    }
    
    @Test
    public void returnsAcyclicTwoVerticesDirected() {
        Vertex first = new Vertex("1");
        Vertex second = new Vertex("2");
        
        DefaultDirectedGraph<Vertex, Edge> directedGraph = new DefaultDirectedGraph<Vertex, Edge>(Edge.class);
        directedGraph.addVertex(first);
        directedGraph.addVertex(second);
        directedGraph.addEdge(first, second, new Edge(first,second));
        
        assertFalse(new CyclicChecker(directedGraph).isCyclic());
        
    }
    
    @Test
    public void returnsCyclicForTwoVerticesUndirected() {
        Vertex first = new Vertex("1");
        Vertex second = new Vertex("2");
        
        DefaultDirectedGraph<Vertex, Edge> directedGraph = new DefaultDirectedGraph<Vertex, Edge>(Edge.class);
        directedGraph.addVertex(first);
        directedGraph.addVertex(second);
        directedGraph.addEdge(first, second, new Edge(first,second));
        Graph<Vertex,Edge> undirectedGraph = new AsUndirectedGraph<Vertex, Edge>(directedGraph);
        
        assertTrue(new CyclicChecker(undirectedGraph).isCyclic());
        
    }
    
    
    @Test
    public void returnsCyclicForComplexDirected() {
        Vertex first = new Vertex("1");
        Vertex second = new Vertex("2");
        Vertex third = new Vertex("3");
        Vertex fourth = new Vertex("4");
        
        DefaultDirectedGraph<Vertex, Edge> directedGraph = new DefaultDirectedGraph<Vertex, Edge>(Edge.class);
        directedGraph.addVertex(first);
        directedGraph.addVertex(second);
        directedGraph.addVertex(third);
        directedGraph.addVertex(fourth);
        directedGraph.addEdge(first, second, new Edge(first,second));
        directedGraph.addEdge(second, third, new Edge(second,third));
        directedGraph.addEdge(third, first, new Edge(third,first));
        directedGraph.addEdge(second, fourth, new Edge(second,fourth));
        
        assertTrue(new CyclicChecker(directedGraph).isCyclic());
        
    }
    @Test
    
    public void returnsACyclicForComplexDirected() {
        Vertex first = new Vertex("1");
        Vertex second = new Vertex("2");
        Vertex third = new Vertex("3");
        Vertex fourth = new Vertex("4");
        
        DefaultDirectedGraph<Vertex, Edge> directedGraph = new DefaultDirectedGraph<Vertex, Edge>(Edge.class);
        directedGraph.addVertex(first);
        directedGraph.addVertex(second);
        directedGraph.addVertex(third);
        directedGraph.addVertex(fourth);
        directedGraph.addEdge(first, second, new Edge(first,second));
        directedGraph.addEdge(second, third, new Edge(second,third));
        directedGraph.addEdge(second, fourth, new Edge(second,fourth));
        
        assertFalse(new CyclicChecker(directedGraph).isCyclic());
        
    }
    
    
    @Test
    public void returnsCyclicForComplexUndirected() {
        Vertex first = new Vertex("1");
        Vertex second = new Vertex("2");
        Vertex third = new Vertex("3");
        Vertex fourth = new Vertex("4");
        
        DefaultDirectedGraph<Vertex, Edge> directedGraph = new DefaultDirectedGraph<Vertex, Edge>(Edge.class);
        directedGraph.addVertex(first);
        directedGraph.addVertex(second);
        directedGraph.addVertex(third);
        directedGraph.addVertex(fourth);
        directedGraph.addEdge(first, second, new Edge(first,second));
        directedGraph.addEdge(second, third, new Edge(second,third));
        directedGraph.addEdge(third, first, new Edge(third,first));
        directedGraph.addEdge(second, fourth, new Edge(second,fourth));

        Graph<Vertex,Edge> undirectedGraph = new AsUndirectedGraph<Vertex, Edge>(directedGraph);
        
        assertTrue(new CyclicChecker(undirectedGraph).isCyclic());
        
    }
    @Test
    
    public void returnsACyclicForComplexUndirected() {
        Vertex first = new Vertex("1");
        Vertex second = new Vertex("2");
        Vertex third = new Vertex("3");
        Vertex fourth = new Vertex("4");
        
        DefaultDirectedGraph<Vertex, Edge> directedGraph = new DefaultDirectedGraph<Vertex, Edge>(Edge.class);
        directedGraph.addVertex(first);
        directedGraph.addVertex(second);
        directedGraph.addVertex(third);
        directedGraph.addVertex(fourth);
        directedGraph.addEdge(first, second, new Edge(first,second));
        directedGraph.addEdge(second, third, new Edge(second,third));
        directedGraph.addEdge(second, fourth, new Edge(second,fourth));
        
        Graph<Vertex,Edge> undirectedGraph = new AsUndirectedGraph<Vertex, Edge>(directedGraph);
        
        assertFalse(new CyclicChecker(undirectedGraph).isCyclic());
        
    }

}
