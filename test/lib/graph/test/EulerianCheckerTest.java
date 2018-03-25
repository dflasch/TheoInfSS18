package lib.graph.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jgrapht.Graph;
import org.jgrapht.graph.AsUndirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.junit.Test;

import lib.graph.Edge;
import lib.graph.Vertex;
import lib.graph.test.EulerianChecker;

public class EulerianCheckerTest {

    @Test
    public void returnsNotEulerian() {
        Vertex first = new Vertex("1");
        Vertex second = new Vertex("2");
        Vertex third = new Vertex("3");
        
        Edge firstSecond = new Edge(first,second);
        Edge secondThird = new Edge(second,third);
        
        DefaultDirectedGraph<Vertex, Edge> directedGraph = new DefaultDirectedGraph<Vertex, Edge>(Edge.class);
        directedGraph.addVertex(first);
        directedGraph.addVertex(second);
        directedGraph.addVertex(third);
        
        directedGraph.addEdge(first, second, firstSecond);
        directedGraph.addEdge(second, third, secondThird);

        Graph<Vertex,Edge> undirectedGraph = new AsUndirectedGraph<Vertex, Edge>(directedGraph);
        
        assertFalse(EulerianChecker.isGraphEulerian(undirectedGraph));
        
    }
    
    @Test
    public void returnsEulerian() {
        Vertex first = new Vertex("1");
        Vertex second = new Vertex("2");
        Vertex third = new Vertex("3");
        
        Edge firstSecond = new Edge(first,second);
        Edge secondThird = new Edge(second,third);
        Edge thirdFirst = new Edge(third,first);

        DefaultDirectedGraph<Vertex, Edge> directedGraph = new DefaultDirectedGraph<Vertex, Edge>(Edge.class);
        
        directedGraph.addVertex(first);
        directedGraph.addVertex(second);
        directedGraph.addVertex(third);
        
        directedGraph.addEdge(first, second, firstSecond);
        directedGraph.addEdge(second, third, secondThird);
        directedGraph.addEdge(third, first, thirdFirst);

        Graph<Vertex,Edge> undirectedGraph = new AsUndirectedGraph<Vertex, Edge>(directedGraph);
        
        assertTrue(EulerianChecker.isGraphEulerian(undirectedGraph));
        
    }
    
    @Test
    public void returnsNoEulerianPathIfAcyclic() {
        Vertex first = new Vertex("1");
        Vertex second = new Vertex("2");
        Vertex third = new Vertex("3");
        
        Edge firstSecond = new Edge(first,second);
        Edge secondThird = new Edge(second,third);
        
        DefaultDirectedGraph<Vertex, Edge> directedGraph = new DefaultDirectedGraph<Vertex, Edge>(Edge.class);
        directedGraph.addVertex(first);
        directedGraph.addVertex(second);
        directedGraph.addVertex(third);
        
        directedGraph.addEdge(first, second, firstSecond);
        directedGraph.addEdge(second, third, secondThird);
        
        Graph<Vertex,Edge> undirectedGraph = new AsUndirectedGraph<Vertex, Edge>(directedGraph);
        
        assertTrue(EulerianChecker.hasGraphEulerianPath(undirectedGraph));
        
    }
    
    @Test
    public void returnsEulerianPathOnTwoOddVertex() {
        Vertex first = new Vertex("1");
        Vertex second = new Vertex("2");
        
        Edge firstSecond = new Edge(first,second);
        
        DefaultDirectedGraph<Vertex, Edge> directedGraph = new DefaultDirectedGraph<Vertex, Edge>(Edge.class);
        directedGraph.addVertex(first);
        directedGraph.addVertex(second);
        
        directedGraph.addEdge(first, second, firstSecond);
        
        Graph<Vertex,Edge> undirectedGraph = new AsUndirectedGraph<Vertex, Edge>(directedGraph);
        
        assertTrue(EulerianChecker.hasGraphEulerianPath(undirectedGraph));
        
    }
    
    @Test
    public void returnsEulerianPathOnNoOddVertex() {
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
        
        Graph<Vertex,Edge> undirectedGraph = new AsUndirectedGraph<Vertex, Edge>(directedGraph);
        
        assertTrue(EulerianChecker.hasGraphEulerianPath(undirectedGraph));
        
    }
    
    @Test
    public void returnsNoEulerianPath() {
        Vertex first = new Vertex("1");
        Vertex second = new Vertex("2");
        Vertex third = new Vertex("3");
        Vertex forth = new Vertex("4");
        
        DefaultDirectedGraph<Vertex, Edge> directedGraph = new DefaultDirectedGraph<Vertex, Edge>(Edge.class);
        
        directedGraph.addVertex(first);
        directedGraph.addVertex(second);
        directedGraph.addVertex(third);
        directedGraph.addVertex(forth);
        
        directedGraph.addEdge(first,second,new Edge(first,second));
        directedGraph.addEdge(second,third,new Edge(second,third));
        directedGraph.addEdge(third,forth,new Edge(third,forth));
        directedGraph.addEdge(forth,first,new Edge(forth,first));
        directedGraph.addEdge(first,third,new Edge(first,third));
        directedGraph.addEdge(second,forth,new Edge(second,forth));
        
        Graph<Vertex,Edge> undirectedGraph = new AsUndirectedGraph<Vertex, Edge>(directedGraph);
        
        assertFalse(EulerianChecker.hasGraphEulerianPath(undirectedGraph));
        
    }

}