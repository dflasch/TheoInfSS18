package lib;

public class Vertex {
    private String id;
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
                && (this.getData() == null ? otherVertex.getData() == null
                        : this.getData().equals(otherVertex.getData())))
            return true;

        return false;

    }

    @Override
    public String toString() {
        return this.id;
    }


    Vertex(String id) {
        this.setName(id);
        this.setData("");
    }
    
    Vertex(String id, String data) {
        this.setName(id);
        this.setData(data);
    }

    public String getName() {
        return id;
    }

    public void setName(String name) {
        this.id = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
