package lib.graph.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.jgrapht.Graph;
import org.junit.jupiter.api.Test;

import lib.graph.Edge;
import lib.graph.Vertex;
import lib.graph.algorithms.pathFinder.PathFinder;
import lib.graph.build.GraphBuilder;
import lib.graph.build.GraphDescription;

class PathFinderTest {

    @Test
    void handlesSelfReference() {
        Vertex A = new Vertex("A");
        Edge AtoA = new Edge(A, A);

        GraphDescription myDescription = new GraphDescription().addVertex(A).addEdge(AtoA);
        Graph<Vertex, Edge> myGraph = new GraphBuilder(myDescription).buildDefaultDirectedGraph();

        List<Vertex> path = new PathFinder(myGraph).findAnyPath(A, A);

        assertEquals(0, path.size());
    }

    @Test
    void handlesOneVertex() {
        Vertex A = new Vertex("A");

        GraphDescription myDescription = new GraphDescription().addVertex(A);
        Graph<Vertex, Edge> myGraph = new GraphBuilder(myDescription).buildDirectedGraph();

        List<Vertex> path = new PathFinder(myGraph).findAnyPath(A, A);

        assertEquals(0, path.size());
    }

    @Test
    void handlesComponets() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");

        GraphDescription myDescription = new GraphDescription().addVertex(A).addVertex(B);
        Graph<Vertex, Edge> myGraph = new GraphBuilder(myDescription).buildDirectedGraph();

        List<Vertex> path = new PathFinder(myGraph).findAnyPath(A, B);

        assertEquals(0, path.size());

    }

    @Test
    void findsPathInAnotherPath() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");

        Edge AtoB = new Edge(A, B);
        Edge BtoC = new Edge(B, C);

        GraphDescription myDescription = new GraphDescription().addVertex(A).addVertex(B).addVertex(C).addEdge(AtoB)
                .addEdge(BtoC);

        Graph<Vertex, Edge> myGraph = new GraphBuilder(myDescription).buildDirectedGraph();

        List<Vertex> path = new PathFinder(myGraph).findAnyPath(A, C);

        assertEquals(3, path.size());
        assertEquals(A, path.get(0));
        assertEquals(B, path.get(1));
        assertEquals(C, path.get(2));

        path = new PathFinder(myGraph).findAnyPath(A, B);

        assertEquals(2, path.size());
        assertEquals(A, path.get(0));
        assertEquals(B, path.get(1));

    }

    @Test
    void findsPathInComplexDirected() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");

        Edge AtoB = new Edge(A, B);
        Edge AtoC = new Edge(A, C);
        Edge CtoD = new Edge(C, D);

        GraphDescription myDescription = new GraphDescription().addVertex(A).addVertex(B).addVertex(C).addVertex(D)
                .addEdge(AtoB).addEdge(AtoC).addEdge(CtoD);

        Graph<Vertex, Edge> myGraph = new GraphBuilder(myDescription).buildDirectedGraph();

        List<Vertex> path = new PathFinder(myGraph).findAnyPath(A, D);

        assertEquals(3, path.size());
        assertEquals(A, path.get(0));
        assertEquals(C, path.get(1));
        assertEquals(D, path.get(2));

    }

    @Test
    void findsPathInComplexUndirected() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");

        Edge AtoB = new Edge(A, B);
        Edge AtoC = new Edge(A, C);
        Edge CtoD = new Edge(C, D);

        GraphDescription myDescription = new GraphDescription().addVertex(A).addVertex(B).addVertex(C).addVertex(D)
                .addEdge(AtoB).addEdge(AtoC).addEdge(CtoD);

        Graph<Vertex, Edge> myGraph = new GraphBuilder(myDescription).buildUndirectedGraph();

        List<Vertex> path = new PathFinder(myGraph).findAnyPath(A, D);

        assertEquals(3, path.size());
        assertEquals(A, path.get(0));
        assertEquals(C, path.get(1));
        assertEquals(D, path.get(2));

    }

    @Test
    void findsPathInUndirectedCircle() {
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");

        Edge AtoB = new Edge(A, B);
        Edge BtoC = new Edge(B, C);

        GraphDescription myDescription = new GraphDescription().addVertex(A).addVertex(B).addVertex(C).addEdge(AtoB)
                .addEdge(BtoC);

        Graph<Vertex, Edge> myGraph = new GraphBuilder(myDescription).buildUndirectedGraph();
        
        List<Vertex> path = new PathFinder(myGraph).findAnyPath(A, C);

        assertEquals(3, path.size());
        assertEquals(A, path.get(0));
        assertEquals(B, path.get(1));
        assertEquals(C, path.get(2));
    }

}
