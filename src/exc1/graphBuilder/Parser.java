package exc1.graphBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Parser {
	
	private final String VERTEX_STR = "knoten";
	private final String EDGE_STR = "kante";
	private final String NEWLINE = "\\r?\\n";
	
	private String graphDescription;

	public Parser(String graphDescription) {
		this.graphDescription = graphDescription;
	}

	public DefaultDirectedWeightedGraph<Vertex, DefaultWeightedEdge> buildDirectedGraph() {
		
		DefaultDirectedWeightedGraph<Vertex,DefaultWeightedEdge> graph = new DefaultDirectedWeightedGraph<Vertex, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);
		
		HashMap<String,Vertex> verticesMap = new HashMap<String,Vertex>();
		
		for (String eachLine : graphDescription.split(NEWLINE)) {
			String[] lineTokens = eachLine.split(" ");
			Queue<String> tokens = new LinkedList<String>(Arrays.asList(lineTokens));

			String rootToken = tokens.poll();
			if (rootToken.equals(VERTEX_STR)) {
				Vertex vertexToAdd = new Vertex(tokens.poll(), tokens.poll());
				verticesMap.put(vertexToAdd.getName(),vertexToAdd);
				graph.addVertex(vertexToAdd);
			}
						
			if (rootToken.equals(EDGE_STR)) {
				DefaultWeightedEdge edge = new DefaultWeightedEdge();
				graph.addEdge(verticesMap.get(tokens.poll()), verticesMap.get(tokens.poll()),edge);
				
				if(tokens.peek() != null)
					graph.setEdgeWeight(edge, Double.parseDouble(tokens.poll()));
			}
				
		}

		return graph;
	}

}
