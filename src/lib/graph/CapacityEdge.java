package lib.graph;

public class CapacityEdge extends Edge{
    private static final long serialVersionUID = -1033698373512923611L;
    private double capacity = 0.0;
    private double currentFlow = 0.0;
    
    public CapacityEdge(Vertex from, Vertex to) {
        super(from, to);
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getCurrentFlow() {
        return currentFlow;
    }

    public void setCurrentFlow(double currentFlow) {
        this.currentFlow = currentFlow;
    }
    

}
