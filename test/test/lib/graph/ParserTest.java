package test.lib.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.hasItem;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.junit.Before;
import org.junit.Test;

import lib.graph.Edge;
import lib.graph.Parser;
import lib.graph.Vertex;

public class ParserTest {

    private Parser testedParser;

    @Before
    public void setup() {
        this.testedParser = new Parser();
    }

    @Test
    public void loadsVertices() {

        Graph<Vertex, Edge> graph = testedParser
                .createDirectedWeightedGraph("" + "knoten ersterKnoten\n" + "knoten zweiterKnoten");

        assertThat(graph.vertexSet(), hasItem(new Vertex("ersterKnoten", null)));
        assertThat(graph.vertexSet(), hasItem(new Vertex("zweiterKnoten", null)));
    }

    @Test
    public void loadsSingleVertex() {
        Graph<Vertex, Edge> graph = testedParser.createDirectedWeightedGraph("" + "knoten ersterKnoten");

        assertThat(graph.vertexSet(), hasItem(new Vertex("ersterKnoten", null)));
    }

    @Test
    public void handlesMultipleWhitespaces() {
        Graph<Vertex, Edge> graph = testedParser
                .createDirectedWeightedGraph("" + "knoten                      ersterKnoten");

        assertThat(graph.vertexSet(), hasItem(new Vertex("ersterKnoten", null)));
    }

    @Test
    public void loadsVertexWithData() {
        Graph<Vertex, Edge> graph = testedParser.createDirectedWeightedGraph("" + "knoten ersterKnoten someData");

        assertThat(graph.vertexSet(), hasItem(new Vertex("ersterKnoten", "someData")));
    }

    @Test
    public void loadsVerticesWithData() {
        Graph<Vertex, Edge> graph = testedParser.createDirectedWeightedGraph(
                "" + "knoten nullKnoten\n" + "knoten ersterKnoten someData\n" + "knoten zweiterKnoten someData2");

        assertThat(graph.vertexSet(), hasItem(new Vertex("nullKnoten", null)));
        assertThat(graph.vertexSet(), hasItem(new Vertex("ersterKnoten", "someData")));
        assertThat(graph.vertexSet(), hasItem(new Vertex("zweiterKnoten", "someData2")));
    }

    @Test
    public void addsEdge() {
        Graph<Vertex, Edge> graph = testedParser.createDirectedWeightedGraph(
                "" + "knoten ersterKnoten\n" + "knoten zweiterKnoten\n" + "kante ersterKnoten zweiterKnoten");

        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        vertices.addAll(graph.vertexSet());
        Vertex firstVertex = vertices.get(0);
        Vertex secondVertex = vertices.get(1);

        assertNotNull(graph.getEdge(firstVertex, secondVertex));
    }

    @Test
    public void addsMultipleEdges() {
        Graph<Vertex, Edge> graph = testedParser.createDirectedWeightedGraph("" + "knoten ersterKnoten\n"
                + "knoten zweiterKnoten\n" + "kante ersterKnoten zweiterKnoten\n" + "kante zweiterKnoten ersterKnoten");

        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        vertices.addAll(graph.vertexSet());
        Vertex firstVertex = vertices.get(0);
        Vertex secondVertex = vertices.get(1);

        assertNotNull(graph.getEdge(firstVertex, secondVertex));
        assertNotNull(graph.getEdge(secondVertex, firstVertex));
    }

    @Test
    public void ingoresComments() {
        Graph<Vertex, Edge> graph = testedParser
                .createDirectedWeightedGraph("" + "knoten ersterKnoten\n" + "#\n" + "#\n" + "knoten zweiterKnoten");

        assertThat(graph.vertexSet(), hasItem(new Vertex("ersterKnoten", null)));
        assertThat(graph.vertexSet(), hasItem(new Vertex("zweiterKnoten", null)));
    }

    @Test
    public void ingoresEmptyLines() {
        Graph<Vertex, Edge> graph = testedParser
                .createDirectedWeightedGraph("" + "knoten ersterKnoten\n" + "\n" + "\n" + "knoten zweiterKnoten");

        assertThat(graph.vertexSet(), hasItem(new Vertex("ersterKnoten", null)));
        assertThat(graph.vertexSet(), hasItem(new Vertex("zweiterKnoten", null)));
    }

    @Test
    public void addsWeight() {
        DefaultDirectedWeightedGraph<Vertex, Edge> graph = testedParser
                .createDirectedWeightedGraph("" + "knoten ersterKnoten\n" + "knoten zweiterKnoten\n"
                        + "kante ersterKnoten zweiterKnoten 1\n" + "kante zweiterKnoten ersterKnoten 2");

        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        vertices.addAll(graph.vertexSet());
        Vertex firstVertex = vertices.get(0);
        Vertex secondVertex = vertices.get(1);

        Edge firstEdge = graph.getEdge(firstVertex, secondVertex);
        Edge secondEdge = graph.getEdge(secondVertex, firstVertex);

        assertEquals(graph.getEdgeWeight(firstEdge), 1, 0.1);
        assertEquals(graph.getEdgeWeight(secondEdge), 2, 0.1);

    }

    @Test
    public void createsUnweightedGraph() {
        DefaultDirectedGraph<Vertex, Edge> graph = testedParser
                .createDirectedGraph("" + "knoten ersterKnoten\n" + "knoten zweiterKnoten\n"
                        + "kante ersterKnoten zweiterKnoten\n" + "kante zweiterKnoten ersterKnoten 1");

        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        vertices.addAll(graph.vertexSet());
        Vertex firstVertex = vertices.get(0);
        Vertex secondVertex = vertices.get(1);

        assertNotNull(graph.getEdge(firstVertex, secondVertex));
        assertNotNull(graph.getEdge(secondVertex, firstVertex));
    }
}
