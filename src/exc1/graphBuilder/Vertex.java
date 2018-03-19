package exc1.graphBuilder;

public class Vertex {
	private String name;
	private String data;

	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof Vertex))
			return false;

		Vertex otherVertex = (Vertex) other;

		if ((this.getName() == null ? otherVertex.getName() == null : this.getName().equals(otherVertex.getName()))
				&& (this.getData() == null ? otherVertex.getData() == null : this.getData().equals(otherVertex.getData())))
			return true;

		return false;

	}

	Vertex(String name, String data) {
		this.setName(name);
		this.setData(data);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
