package exc1.graphBuilder;

import org.jgrapht.graph.DefaultWeightedEdge;

public class Edge extends DefaultWeightedEdge{

	private static final long serialVersionUID = 1L;
	private Vertex from;
	private Vertex to;

	public Edge(Vertex from, Vertex to) {
		this.setFrom(from);
		this.setTo(to);
	}

	public Vertex getFrom() {
		return from;
	}

	public void setFrom(Vertex from) {
		this.from = from;
	}

	public Vertex getTo() {
		return to;
	}

	public void setTo(Vertex to) {
		this.to = to;
	}

}
