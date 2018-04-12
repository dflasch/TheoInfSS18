package test.lib.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.hasItem;
import org.jgrapht.Graph;
import org.junit.Test;

import lib.graph.Edge;
import lib.graph.Vertex;
import lib.graph.build.GraphBuilder;
import lib.graph.build.GraphDescription;
import lib.graph.build.Parser;

public class ParserTest {

    @Test
    public void loadsVertices() {
        GraphDescription graphDescription = Parser
                .parseDescription("" + "knoten ersterKnoten\n" + "knoten zweiterKnoten");

        Graph<Vertex, Edge> graph = new GraphBuilder().addDescription(graphDescription)
                .buildDirectedWeightedGraph();

        assertThat(graph.vertexSet(), hasItem(new Vertex("ersterKnoten", null)));
        assertThat(graph.vertexSet(), hasItem(new Vertex("zweiterKnoten", null)));
    }

    @Test
    public void loadsSingleVertex() {
        GraphDescription graphDescription = Parser.parseDescription("" + "knoten ersterKnoten");

        Graph<Vertex, Edge> graph = new GraphBuilder().addDescription(graphDescription)
                .buildDirectedWeightedGraph();

        assertThat(graph.vertexSet(), hasItem(new Vertex("ersterKnoten", null)));
    }

    @Test
    public void handlesMultipleWhitespaces() {
        GraphDescription graphDescription = Parser.parseDescription("" + "knoten                      ersterKnoten");

        Graph<Vertex, Edge> graph = new GraphBuilder().addDescription(graphDescription)
                .buildDirectedWeightedGraph();

        assertThat(graph.vertexSet(), hasItem(new Vertex("ersterKnoten", null)));
    }

    @Test
    public void loadsVertexWithData() {
        GraphDescription graphDescription = Parser.parseDescription("" + "knoten ersterKnoten someData");
        
        Graph<Vertex, Edge> graph = new GraphBuilder().addDescription(graphDescription)
                .buildDirectedWeightedGraph();
        
        assertThat(graph.vertexSet(), hasItem(new Vertex("ersterKnoten", "someData")));
    }

    @Test
    public void loadsVerticesWithData() {
        GraphDescription graphDescription = Parser.parseDescription(
                "" + "knoten nullKnoten\n" + "knoten ersterKnoten someData\n" + "knoten zweiterKnoten someData2");
        
        Graph<Vertex, Edge> graph = new GraphBuilder().addDescription(graphDescription)
                .buildDirectedWeightedGraph();
        
        assertThat(graph.vertexSet(), hasItem(new Vertex("nullKnoten", null)));
        assertThat(graph.vertexSet(), hasItem(new Vertex("ersterKnoten", "someData")));
        assertThat(graph.vertexSet(), hasItem(new Vertex("zweiterKnoten", "someData2")));
    }

    @Test
    public void addsEdge() {
        GraphDescription graphDescription = Parser.parseDescription(
                "" + "knoten ersterKnoten\n" + "knoten zweiterKnoten\n" + "kante ersterKnoten zweiterKnoten");
        
        Graph<Vertex, Edge> graph = new GraphBuilder().addDescription(graphDescription)
                .buildDirectedWeightedGraph();

        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        vertices.addAll(graph.vertexSet());
        Vertex firstVertex = vertices.get(0);
        Vertex secondVertex = vertices.get(1);

        assertNotNull(graph.getEdge(firstVertex, secondVertex));
    }

    @Test
    public void addsMultipleEdges() {
        GraphDescription graphDescription = Parser.parseDescription("" + "knoten ersterKnoten\n"
                + "knoten zweiterKnoten\n" + "kante ersterKnoten zweiterKnoten\n" + "kante zweiterKnoten ersterKnoten");

        Graph<Vertex, Edge> graph = new GraphBuilder().addDescription(graphDescription)
                .buildDirectedWeightedGraph();
        
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        vertices.addAll(graph.vertexSet());
        Vertex firstVertex = vertices.get(0);
        Vertex secondVertex = vertices.get(1);

        assertNotNull(graph.getEdge(firstVertex, secondVertex));
        assertNotNull(graph.getEdge(secondVertex, firstVertex));
    }

    @Test
    public void ingoresComments() {
        GraphDescription graphDescription = Parser.parseDescription("" + "knoten ersterKnoten\n" + "#\n" + "#\n" + "knoten zweiterKnoten");

        Graph<Vertex, Edge> graph = new GraphBuilder().addDescription(graphDescription)
                .buildDirectedWeightedGraph();
        
        assertThat(graph.vertexSet(), hasItem(new Vertex("ersterKnoten", null)));
        assertThat(graph.vertexSet(), hasItem(new Vertex("zweiterKnoten", null)));
    }

    @Test
    public void ingoresEmptyLines() {
        GraphDescription graphDescription = Parser.parseDescription("" + "knoten ersterKnoten\n" + "\n" + "\n" + "knoten zweiterKnoten");

        Graph<Vertex, Edge> graph = new GraphBuilder().addDescription(graphDescription)
                .buildDirectedWeightedGraph();
        
        assertThat(graph.vertexSet(), hasItem(new Vertex("ersterKnoten", null)));
        assertThat(graph.vertexSet(), hasItem(new Vertex("zweiterKnoten", null)));
    }

    @Test
    public void addsWeight() {
        GraphDescription graphDescription = Parser.parseDescription("" + "knoten ersterKnoten\n" + "knoten zweiterKnoten\n"
                        + "kante ersterKnoten zweiterKnoten 1\n" + "kante zweiterKnoten ersterKnoten 2");

        Graph<Vertex, Edge> graph = new GraphBuilder().addDescription(graphDescription)
                .buildDirectedWeightedGraph();
        
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
        GraphDescription graphDescription = Parser.parseDescription("" + "knoten ersterKnoten\n" + "knoten zweiterKnoten\n"
                        + "kante ersterKnoten zweiterKnoten\n" + "kante zweiterKnoten ersterKnoten 1");

        Graph<Vertex, Edge> graph = new GraphBuilder().addDescription(graphDescription)
                .buildDirectedGraph();
        
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        vertices.addAll(graph.vertexSet());
        Vertex firstVertex = vertices.get(0);
        Vertex secondVertex = vertices.get(1);

        assertNotNull(graph.getEdge(firstVertex, secondVertex));
        assertNotNull(graph.getEdge(secondVertex, firstVertex));
    }
}
