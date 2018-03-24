package lib;

import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;

public class PrintingTraversalListener implements TraversalListener<Vertex, Edge> {
    @Override
    public void connectedComponentFinished(ConnectedComponentTraversalEvent arg0) {
    }

    @Override
    public void connectedComponentStarted(ConnectedComponentTraversalEvent arg0) {
    }

    @Override
    public void edgeTraversed(EdgeTraversalEvent<Edge> arg0) {
    }

    @Override
    public void vertexFinished(VertexTraversalEvent<Vertex> arg0) {
    }

    @Override
    public void vertexTraversed(VertexTraversalEvent<Vertex> arg0) {
        System.out.print(arg0.getVertex().getName() + " ");
    }
}
