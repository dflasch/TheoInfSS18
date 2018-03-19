package exc1.graphBuilder;

import static org.junit.Assert.*;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.hasItem;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.Test;

public class ParserTest {

	@Test
	public void loadsVertices() {
		Parser testedParser = new Parser(""
				+ "knoten ersterKnoten\n"
				+ "knoten zweiterKnoten");
		
		Graph<Vertex, DefaultWeightedEdge> graph = testedParser.buildDirectedGraph();
		
		assertThat(graph.vertexSet(),hasItem(new Vertex("ersterKnoten",null)));
		assertThat(graph.vertexSet(),hasItem(new Vertex("zweiterKnoten",null)));
	}
	
	@Test
	public void loadsSingleVertex() {
		Parser testedParser = new Parser("knoten ersterKnoten");
		Graph<Vertex, DefaultWeightedEdge> graph = testedParser.buildDirectedGraph();
		
		assertThat(graph.vertexSet(),hasItem(new Vertex("ersterKnoten",null)));
	}
	
	@Test
	public void loadsVertexWithData() {
		Parser testedParser = new Parser("knoten ersterKnoten someData");
		Graph<Vertex, DefaultWeightedEdge> graph = testedParser.buildDirectedGraph();

		assertThat(graph.vertexSet(),hasItem(new Vertex("ersterKnoten","someData")));
	}
	
	@Test
	public void loadsVerticesWithData() {
		Parser testedParser = new Parser(""
				+ "knoten nullKnoten\n"
				+ "knoten ersterKnoten someData\n"
				+ "knoten zweiterKnoten someData2");
		
		Graph<Vertex, DefaultWeightedEdge> graph = testedParser.buildDirectedGraph();
		
		assertThat(graph.vertexSet(),hasItem(new Vertex("nullKnoten",null)));
		assertThat(graph.vertexSet(),hasItem(new Vertex("ersterKnoten","someData")));
		assertThat(graph.vertexSet(),hasItem(new Vertex("zweiterKnoten","someData2")));
	}
	
	@Test
	public void addsEdge() {
		Parser testedParser = new Parser(""
				+ "knoten ersterKnoten\n"
				+ "knoten zweiterKnoten\n"
				+ "kante ersterKnoten zweiterKnoten");
		
		Graph<Vertex, DefaultWeightedEdge> graph = testedParser.buildDirectedGraph();
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		vertices.addAll(graph.vertexSet());
		Vertex firstVertex = vertices.get(0);
		Vertex secondVertex = vertices.get(1);
		
		assertNotNull(graph.getEdge(firstVertex, secondVertex));
	}
	
	@Test
	public void addsMultipleEdges() {
		Parser testedParser = new Parser(""
				+ "knoten ersterKnoten\n"
				+ "knoten zweiterKnoten\n"
				+ "kante ersterKnoten zweiterKnoten\n"
				+ "kante zweiterKnoten ersterKnoten" );
		
		Graph<Vertex, DefaultWeightedEdge> graph = testedParser.buildDirectedGraph();
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		vertices.addAll(graph.vertexSet());
		Vertex firstVertex = vertices.get(0);
		Vertex secondVertex = vertices.get(1);
		
		assertNotNull(graph.getEdge(firstVertex, secondVertex));
		assertNotNull(graph.getEdge(secondVertex,firstVertex ));
	}
	
	@Test
	public void ingoresComments() {
		Parser testedParser = new Parser(""
				+ "knoten ersterKnoten\n"
				+ "#\n"
				+ "#\n"
				+ "knoten zweiterKnoten");
		
		Graph<Vertex, DefaultWeightedEdge> graph = testedParser.buildDirectedGraph();
		
		assertThat(graph.vertexSet(),hasItem(new Vertex("ersterKnoten",null)));
		assertThat(graph.vertexSet(),hasItem(new Vertex("zweiterKnoten",null)));
	}
	
	
	@Test
	public void ingoresEmptyLines() {
		Parser testedParser = new Parser(""
				+ "knoten ersterKnoten\n"
				+ "\n"
				+ "\n"
				+ "knoten zweiterKnoten");
		
		Graph<Vertex, DefaultWeightedEdge> graph = testedParser.buildDirectedGraph();
		
		assertThat(graph.vertexSet(),hasItem(new Vertex("ersterKnoten",null)));
		assertThat(graph.vertexSet(),hasItem(new Vertex("zweiterKnoten",null)));
	}
	
	@Test
	public void addsWeight() {
		Parser testedParser = new Parser(""
				+ "knoten ersterKnoten\n"
				+ "knoten zweiterKnoten\n"
				+ "kante ersterKnoten zweiterKnoten 1\n"
				+ "kante zweiterKnoten ersterKnoten 2");
		
		Graph<Vertex, DefaultWeightedEdge> graph = testedParser.buildDirectedGraph();
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		vertices.addAll(graph.vertexSet());
		Vertex firstVertex = vertices.get(0);
		Vertex secondVertex = vertices.get(1);
		
		DefaultWeightedEdge firstEdge = graph.getEdge(firstVertex, secondVertex);
		DefaultWeightedEdge secondEdge = graph.getEdge(secondVertex,firstVertex);
		
		assertEquals(graph.getEdgeWeight(firstEdge),1,0.1);
		assertEquals(graph.getEdgeWeight(secondEdge),2,0.1);
		
	}
}
