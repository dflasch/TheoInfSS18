package lib.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jgrapht.Graph;
import org.jgrapht.io.DOTExporter;
import org.jgrapht.io.ExportException;
import org.jgrapht.io.StringComponentNameProvider;

import lib.graph.Edge;
import lib.graph.Vertex;

import org.jgrapht.io.ComponentNameProvider;

public class Exporter {
    private Graph<Vertex, Edge> graph;

    public Exporter(Graph<Vertex, Edge> graph) {
        this.graph = graph;
    }

    public Exporter printToConsole() throws ExportException {
        toDotFormat(graph).exportGraph(graph, System.out);
        return this;
    }

    public Exporter printToFile(String path, boolean withWeights) throws ExportException, IOException {
        File file = new File(path);

        if (!file.getParentFile().exists())
            file.getParentFile().mkdir();
        file.createNewFile();

        FileOutputStream out = new FileOutputStream(file);
        
        if(withWeights)
            toDotFormatWithWeight(graph).exportGraph(graph, out);
        else
            toDotFormat(graph).exportGraph(graph, out);
        
        return this;
    }

    private DOTExporter<Vertex, Edge> toDotFormat(Graph<Vertex, Edge> graph) {
        return new DOTExporter<Vertex, Edge>(new StringComponentNameProvider<Vertex>(), null, null);
    }
    private DOTExporter<Vertex, Edge> toDotFormatWithWeight(Graph<Vertex, Edge> graph) {
        return new DOTExporter<Vertex, Edge>(new StringComponentNameProvider<Vertex>(), null, new WeightProvider());
    }

    private class WeightProvider implements ComponentNameProvider<Edge> {

        @Override
        public String getName(Edge edge) {
            return "" + Double.valueOf(graph.getEdgeWeight(edge)).intValue();
        }

    }
}
